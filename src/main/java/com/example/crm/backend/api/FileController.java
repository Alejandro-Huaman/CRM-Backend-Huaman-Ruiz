package com.example.crm.backend.api;

import com.example.crm.backend.domain.salesAggregate.model.entity.File;
import com.example.crm.backend.domain.salesAggregate.service.FileService;
import com.example.crm.backend.domain.userAggregate.service.ImageService;
import com.example.crm.backend.mapping.FileMapper;
import com.example.crm.backend.resource.File.FileResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileMapper mapper;

    public static final String DIRECTORY = System.getProperty("user.dir");

    @ApiOperation(value = "Upload file for User and Sale",notes = "Esta consulta consiste en cargar un archivo para un usuario y una venta segun sus ids")
    @PostMapping("/upload/user/{userid}/sale/{saleid}")
    public ResponseEntity<FileResource> uploadFileforUserIdandSaleId(@RequestParam("files") MultipartFile multipartFile, @PathVariable("userid") Long userid,@PathVariable("saleid") Long saleid) throws IOException{

        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path fileStorage = Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();
        copy(multipartFile.getInputStream(),fileStorage,REPLACE_EXISTING);

        File file = new File();
        file.setFilename(filename);

        return ResponseEntity.ok().body(mapper.toResource(fileService.uploadFileforUserIdandSaleId(userid,saleid,file)));
    }

    @ApiOperation(value = "Download file",notes = "Esta consulta consiste en descargar un archivo")
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable String filename) throws IOException {

        Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);

        if(!Files.exists(filePath)){
            throw new FileNotFoundException(filename + " was not found on the server");
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name",filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);

    }

    @ApiOperation(value = "Get file by User Id and Sale Id",notes = "Esta consulta consiste en obtener un archivo segun el id del usuario y la venta")
    @GetMapping("/user/{userid}/sale/{saleid}")
    public ResponseEntity<Page<FileResource>> getFileByUserIdandSaleId(@PathVariable("userid") Long userid, @PathVariable("saleid") Long saleid, Pageable pageable){
        return ResponseEntity.ok(mapper.modelListToPage(fileService.getFileByUserIdandSaleId(userid,saleid),pageable));
    }

    @ApiOperation(value = "Delete file by Id",notes = "Esta consulta consiste en eliminar un archivo segun su id")
    @DeleteMapping("/{fileid}")
    public ResponseEntity<?> delete(@PathVariable("fileid") Long fileid){
        return fileService.deletefile(fileid);
    }

}

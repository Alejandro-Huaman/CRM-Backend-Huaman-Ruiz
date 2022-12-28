package com.example.crm.backend.api;


import com.example.crm.backend.domain.userAggregate.model.entity.Image;
import com.example.crm.backend.domain.userAggregate.service.CloudinaryService;
import com.example.crm.backend.domain.userAggregate.service.ImageService;
import com.example.crm.backend.mapping.ImageMapper;
import com.example.crm.backend.resource.Image.ImageResource;
import com.example.crm.shared.exception.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ImageUserController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageMapper mapper;

    @ApiOperation(value = "Create image for User",notes = "Esta consulta consiste en crear una imagen de perfil para un usuario segun su id")
    @PostMapping("/upload/users/{userId}/images")
    public ResponseEntity<ImageResource> createimageforuser(@PathVariable Long userId, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }

        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image();
        image.setImagenUrl((String)result.get("url"));
        image.setImagenId((String)result.get("public_id"));

        return ResponseEntity.ok(mapper.toResource(imageService.createforuser(userId,image)));
    }

    @ApiOperation(value = "Get image by User ID",notes = "Esta consulta consiste en obtener la imagen de un usuario segun su id")
    @GetMapping("/images/users/{userId}")
    public ResponseEntity<Page<ImageResource>> getImageByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(mapper.modelListToPage(imageService.getImageByUserId(userId), pageable));
    }

    @ApiOperation(value = "Delete image by ID",notes = "Esta consulta consiste en eliminar una imagen segun su id")
    @DeleteMapping("/images/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)throws IOException {
        if(!imageService.exists(id))
            return new ResponseEntity(new Message("The image no exists"), HttpStatus.NOT_FOUND);
        Image image = imageService.getById(id);
        Map result = cloudinaryService.delete(image.getImagenId());
        imageService.delete(id);
        return new ResponseEntity(new Message("image deleted"), HttpStatus.OK);
    }

}

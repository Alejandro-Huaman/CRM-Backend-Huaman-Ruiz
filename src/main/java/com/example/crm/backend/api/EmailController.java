package com.example.crm.backend.api;

import com.example.crm.backend.service.EmailSenderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailSenderService emailservice;

    @ApiOperation(value = "Send Email",notes = "Esta consulta consiste en enviar emails a los usuarios o clientes")
    @PostMapping("/send/{toemail}/content/{subject}/{body}")
    public ResponseEntity<?> sendEmail(@PathVariable("toemail") String toemail,@PathVariable("subject") String subject,@PathVariable("body") String body){
        emailservice.sendEmail(toemail,subject,body);
        return ResponseEntity.ok().build();
    }
}

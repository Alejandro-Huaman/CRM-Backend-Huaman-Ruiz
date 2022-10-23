package com.example.crm.shared.exception;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourcePerzonalized extends RuntimeException{


    public ResourcePerzonalized(String mensaje) {

        super(mensaje);


    }



}

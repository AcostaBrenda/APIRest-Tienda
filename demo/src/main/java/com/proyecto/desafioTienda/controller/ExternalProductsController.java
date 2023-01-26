package com.proyecto.desafioTienda.controller;

import com.proyecto.desafioTienda.dto.ExternalProductsDTO;
import com.proyecto.desafioTienda.service.ExternalProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/external")
public class ExternalProductsController {

    @Autowired
    private ExternalProductsService externalProductsService;

    @GetMapping("/")
    public ResponseEntity<ExternalProductsDTO> findAll(){
        ExternalProductsDTO externalProductsDTOS = externalProductsService.getExternalProducts();
        return ResponseEntity.ok(externalProductsDTOS);
    }
}

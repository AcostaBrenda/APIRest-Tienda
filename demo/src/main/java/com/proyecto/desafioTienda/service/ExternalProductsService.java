package com.proyecto.desafioTienda.service;

import com.proyecto.desafioTienda.dto.ExternalProductsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@Service
public class ExternalProductsService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ExternalProductsDTO> getExternalProducts(){
        List<ExternalProductsDTO>externalProductsDTO= this.restTemplate.getForObject("https://externalapi.free.beeceptor.com/externalproducts", List.class);
        return externalProductsDTO;
    }

}
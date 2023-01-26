package com.proyecto.desafioTienda.service;

import com.proyecto.desafioTienda.dto.ExternalProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExternalProductsService {

    @Autowired
    private RestTemplate restTemplate;

    public ExternalProductsDTO getExternalProducts(){
        ExternalProductsDTO externalProductsDTO= this.restTemplate.getForObject("https://aot9p65xum.api.quickmocker.com/api/externalproducts", ExternalProductsDTO.class);
        return externalProductsDTO;
    }
}

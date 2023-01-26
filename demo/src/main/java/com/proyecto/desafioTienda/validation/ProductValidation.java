package com.proyecto.desafioTienda.validation;

import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.dto.ProviderDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {

    public void validateName(String name){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
    }

    public void validateDescription(String description){
        if(StringUtils.isBlank(description)){
            throw new IllegalArgumentException("La descripcion del producto no puede ser nulo o vacío");
        }
    }
    public void validatePrice(float price){
        if(price <= 0.0){
            throw new IllegalArgumentException("El precio del producto no puede ser menor o igual a 0");
        }
    }
    public void validateStock(int stock){
        if(stock <= 0){
            throw new IllegalArgumentException("El stock del producto no puede ser menor o igual a 0");
        }
    }
    public void validateStockMinimum(int stockMinimum){
        if(stockMinimum <= 0){
            throw new IllegalArgumentException("El stock minimo del producto no puede ser menor o igual a 0");
        }
    }
    public void validateProvider(ProviderDTO providerDTO){
        if(providerDTO == null){
            throw new IllegalArgumentException("El proveedor del producto no puede ser nulo o vacío");
        }
    }
    public void validateAll(ProductDTO productDTO){
        if(productDTO == null){
            throw new IllegalArgumentException("El producto no puede ser nulo o vacio");
        }else{
            this.validateName(productDTO.getName());
            this.validateDescription(productDTO.getDescription());
            this.validatePrice(productDTO.getPrice());
            this.validateStock(productDTO.getStock());
            this.validateStockMinimum(productDTO.getStockMinimum());
            this.validateProvider(productDTO.getProvider());
        }
    }
}

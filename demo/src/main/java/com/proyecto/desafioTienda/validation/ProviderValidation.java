package com.proyecto.desafioTienda.validation;

import com.proyecto.desafioTienda.dto.ProviderDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ProviderValidation {

    public void validateName(String name){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("El nombre del proveedor no puede ser nulo o vacío");
        }
    }

    public void validateCuit(String cuit){
        if(StringUtils.isBlank(cuit)){
            throw new IllegalArgumentException("El cuit del proveedor no puede ser nulo o vacío");
        }
    }
    public void validatePhone(String phone){
        if(StringUtils.isBlank(phone)){
            throw new IllegalArgumentException("El telefono del proveedor no puede ser nulo o vacío");
        }
    }
    public void validateAddress(String address){
        if(StringUtils.isBlank(address)){
            throw new IllegalArgumentException("La direccion del proveedor no puede ser nulo o vacío");
        }
    }

    public void validateAll(ProviderDTO providerDTO){
        if(providerDTO == null){
            throw new IllegalArgumentException("El proveedor no puede ser nulo o vacio");
        }else{
            this.validateName(providerDTO.getName());
            this.validateCuit(providerDTO.getCuit());
            this.validatePhone(providerDTO.getPhone());
            this.validateAddress(providerDTO.getAddress());
        }

    }
}

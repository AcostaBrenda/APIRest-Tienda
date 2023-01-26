package com.proyecto.desafioTienda.validation;

import com.proyecto.desafioTienda.dto.CustomerDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    public void validateName(String name){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("El nombre del cliente no puede ser nulo o vacío");
        }
    }

    public void validateSurname(String surname){
        if(StringUtils.isBlank(surname)){
            throw new IllegalArgumentException("El apellido del cliente no puede ser nulo o vacío");
        }

    }
    public void validateDni(String dni){
        if(StringUtils.isBlank(dni)){
            throw new IllegalArgumentException("El dni del cliente no puede ser nulo o vacío");
        }
    }
    public void validatePhone(String phone){
        if(StringUtils.isBlank(phone)){
            throw new IllegalArgumentException("El telefono del cliente no puede ser nulo o vacío");
        }

    }
    public void validateAddress(String address){
        if(StringUtils.isBlank(address)){
            throw new IllegalArgumentException("La dirección del cliente no puede ser nulo o vacío");
        }

    }

    public void validateAll(CustomerDTO customerDTO){
        if(customerDTO == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo o vacío");
        }else {
            this.validateName(customerDTO.getName());
            this.validateSurname(customerDTO.getSurname());
            this.validateDni(customerDTO.getDni());
            this.validatePhone(customerDTO.getPhone());
            this.validateAddress(customerDTO.getAddress());
        }
    }

}

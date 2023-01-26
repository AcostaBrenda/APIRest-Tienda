package com.proyecto.desafioTienda.validation;

import com.proyecto.desafioTienda.dto.CustomerDTO;
import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.dto.SaleDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleValidation {
    public void validateTotalPrice(float totalPrice){
        if(totalPrice <= 0.0){
            throw new IllegalArgumentException("El precio total de la venta no puede ser menor o igual a 0");
        }
    }
    public void validateCustomer(CustomerDTO customerDTO){
        if(customerDTO == null){
            throw new IllegalArgumentException("El cliente de la venta no puede ser nulo o vacío");
        }
    }


    public void validateAll(SaleDTO saleDTO){
        if (saleDTO == null){
            throw new IllegalArgumentException("La venta no puede ser nula o vacia");
        }else{
            this.validateTotalPrice(saleDTO.getTotalPrice());
            this.validateCustomer(saleDTO.getCustomerDTO());
        }
    }
}

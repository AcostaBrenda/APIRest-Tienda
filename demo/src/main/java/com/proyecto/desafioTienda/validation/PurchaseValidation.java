package com.proyecto.desafioTienda.validation;

import com.proyecto.desafioTienda.dto.*;
import org.springframework.stereotype.Component;

@Component
public class PurchaseValidation {

    public void validateTotalPrice(float totalPrice){
        if(totalPrice <= 0.0){
            throw new IllegalArgumentException("El precio total de la compra no puede ser menor o igual a 0");
        }
    }
    public void validateProvider(ProviderDTO providerDTO){
        if(providerDTO == null){
            throw new IllegalArgumentException("El proveedor de la compra no puede ser nulo o vacío");
        }
    }

    public void validateAll(PurchaseDTO purchaseDTO){
        if (purchaseDTO == null){
            throw new IllegalArgumentException("La compra no puede ser nula o vacia");
        }else{
            this.validateTotalPrice(purchaseDTO.getTotalPrice());
            this.validateProvider(purchaseDTO.getProviderDTO());
        }
    }
}

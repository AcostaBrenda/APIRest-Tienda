package com.proyecto.desafioTienda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private float price;
    private int stock;

    private int stockMinimum;

    private ProviderDTO provider;
    private boolean active;

}

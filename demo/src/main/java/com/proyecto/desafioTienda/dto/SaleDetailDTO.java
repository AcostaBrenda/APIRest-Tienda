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
public class SaleDetailDTO implements Serializable {
    private Long id;

    private SaleDTO sale;

    private int quantity;

    private ProductDTO product;
}

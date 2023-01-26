package com.proyecto.desafioTienda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalProductsDTO {

    @JsonProperty("productName")
    private String name;
    @JsonProperty("productCode")
    private String sku;
    @JsonProperty("productQuantity")
    private int stock;
    @JsonProperty("productImg")
    private String imgUrl;

}

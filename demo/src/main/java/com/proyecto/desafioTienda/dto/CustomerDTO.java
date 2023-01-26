package com.proyecto.desafioTienda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable{

    private Long id;

    private String name;

    private String surname;

    private String dni;

    private String phone;

    private String address;

    private boolean active;

}

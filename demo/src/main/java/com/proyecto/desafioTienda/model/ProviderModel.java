package com.proyecto.desafioTienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
public class ProviderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String name;

    @Column(unique = true, length = 20, nullable = false)
    private String cuit;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    private boolean active;

    @PrePersist
    private void state(){
        this.active= true;
    }
}

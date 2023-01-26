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
@Table(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, length = 11, nullable = false)
    private String dni;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    private boolean active;

    @PrePersist
    private void state(){
        this.active= true;
    }
}

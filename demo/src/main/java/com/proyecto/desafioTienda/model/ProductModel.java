package com.proyecto.desafioTienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(length = 10, nullable = false)
    private float price;

    @Column(nullable = false)
    private int stock;

    @Column(name = "stock_minimum")
    private int stockMinimum;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable=false)
    private ProviderModel provider;

    private boolean active;

    @PrePersist
    private void state(){
        this.active= true;
    }

}


package com.proyecto.desafioTienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_detail")
public class SaleDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleModel sale;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;
}

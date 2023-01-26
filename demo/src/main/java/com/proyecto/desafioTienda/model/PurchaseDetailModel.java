package com.proyecto.desafioTienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_detail")
public class PurchaseDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private PurchaseModel purchase;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;
}

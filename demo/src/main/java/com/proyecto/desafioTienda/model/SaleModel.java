package com.proyecto.desafioTienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale")
public class SaleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @Column(name = "total_price")
    private float totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @OneToMany
    private List<SaleDetailModel> saleDetails;

    @PrePersist
    private void load() {
        this.date = LocalDateTime.now();
    }

}

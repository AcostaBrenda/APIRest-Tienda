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
@Table(name = "purchase")
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;


    @Column(name = "total_price")
    private float totalPrice;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private ProviderModel provider;

    @OneToMany
    private List<PurchaseDetailModel> purchaseDetails;

    @PrePersist
    private void load() {
        this.date = LocalDateTime.now();
    }

}

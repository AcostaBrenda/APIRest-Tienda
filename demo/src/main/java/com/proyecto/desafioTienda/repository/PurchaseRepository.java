package com.proyecto.desafioTienda.repository;

import com.proyecto.desafioTienda.model.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {

    @Query(value = "SELECT * \n" +
            "FROM purchase, purchase_detail\n" +
            "WHERE provider_id = ?1\n" +
            "AND purchase_id = purchase.id", nativeQuery = true)

    List<PurchaseModel> findByProvider(Long id);
}

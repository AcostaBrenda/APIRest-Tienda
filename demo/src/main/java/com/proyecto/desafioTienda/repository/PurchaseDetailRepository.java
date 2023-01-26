package com.proyecto.desafioTienda.repository;

import com.proyecto.desafioTienda.model.PurchaseDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository  extends JpaRepository<PurchaseDetailModel, Long> {

    @Query(value = "SELECT * FROM purchase_detail WHERE purchase_id = ?1", nativeQuery = true)
    List<PurchaseDetailModel> findByPurchaseId(Long id);
}

package com.proyecto.desafioTienda.repository;

import com.proyecto.desafioTienda.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByName(String name);

    @Query(value = "SELECT * FROM products p WHERE p.stock <= p.stockMinimum", nativeQuery = true)
    List<ProductModel> findByAllProductWithStockMinimum();
}
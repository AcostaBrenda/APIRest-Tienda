package com.proyecto.desafioTienda.repository;

import com.proyecto.desafioTienda.model.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {

    List<SaleModel> findByDate(LocalDate date);
}

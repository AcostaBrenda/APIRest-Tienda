package com.proyecto.desafioTienda.service;

import com.proyecto.desafioTienda.dto.SaleDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SaleService{

    SaleDTO findById(Long id) throws ResourceNotFoundException;

    SaleDTO save(SaleDTO saleDTO) throws ResourceNotFoundException;

    List<SaleDTO> findAll();

    List<SaleDTO> findByDate(LocalDate date);

}
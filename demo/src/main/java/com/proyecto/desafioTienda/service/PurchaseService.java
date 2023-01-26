package com.proyecto.desafioTienda.service;


import com.proyecto.desafioTienda.dto.PurchaseDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {

    PurchaseDTO findById(Long id) throws ResourceNotFoundException;

    PurchaseDTO save(PurchaseDTO purchaseDTO ) throws ResourceNotFoundException;

    List<PurchaseDTO> findAll();

    List<PurchaseDTO> findByProvider(Long id) throws ResourceNotFoundException;
}

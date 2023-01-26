package com.proyecto.desafioTienda.service;

import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService{

    ProductDTO findById(Long id) throws ResourceNotFoundException;
    ProductDTO save(ProductDTO productDTO) throws ResourceAlreadyExistException;
    List<ProductDTO> findAll();
    ProductDTO edit(ProductDTO productDTO,Long id) throws ResourceNotFoundException;
    Boolean delete(Long id) throws ResourceNotFoundException;

    List<ProductDTO> findByAllProductWithStockMinimum();
}

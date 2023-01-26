package com.proyecto.desafioTienda.service;


import com.proyecto.desafioTienda.dto.CustomerDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService{

    CustomerDTO findById(Long id) throws ResourceNotFoundException;
    CustomerDTO save(CustomerDTO customerDTO) throws ResourceAlreadyExistException;
    List<CustomerDTO> findAll();
    CustomerDTO edit(CustomerDTO customerDTO,Long id) throws ResourceNotFoundException;
    Boolean delete(Long id) throws ResourceNotFoundException;
}

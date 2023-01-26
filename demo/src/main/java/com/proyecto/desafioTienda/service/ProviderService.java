package com.proyecto.desafioTienda.service;

import com.proyecto.desafioTienda.dto.ProviderDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProviderService{

    ProviderDTO findById(Long id) throws ResourceNotFoundException;
    ProviderDTO save(ProviderDTO providerDTO) throws ResourceAlreadyExistException;
    List<ProviderDTO> findAll();
    ProviderDTO edit(ProviderDTO providerDTO,Long id) throws ResourceNotFoundException;
    Boolean delete(Long id) throws ResourceNotFoundException;
}

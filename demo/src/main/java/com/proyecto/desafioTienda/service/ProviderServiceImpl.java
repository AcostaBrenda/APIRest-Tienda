package com.proyecto.desafioTienda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.desafioTienda.dto.ProviderDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.model.ProviderModel;
import com.proyecto.desafioTienda.repository.ProviderRepository;
import com.proyecto.desafioTienda.validation.ProviderValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
@Slf4j
@Transactional
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderValidation providerValidation;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public ProviderDTO findById(Long id) throws ResourceNotFoundException {

        log.info("El id del proveedor recibido desde el frontend es : " + id);

        if (id <= 0) {
            throw new IllegalArgumentException("El id del proveedor no puede ser menor o igual a 0");
        }
        Optional<ProviderModel> providerOp = providerRepository.findById(id);

        log.info("Se encontro el siguiente id del proveedor : " + providerOp);

        if (providerOp.isEmpty()) {
            throw new ResourceNotFoundException("El proveedor  con el id " + id + " no existe");
        }
        ProviderModel provider = providerOp.get();
        return mapper.convertValue(provider, ProviderDTO.class);
    }

    @Override
    public ProviderDTO save(ProviderDTO providerDTO) throws ResourceAlreadyExistException {

        log.info("El proveedor recibido desde el frontend es : " + providerDTO);

        providerValidation.validateAll(providerDTO);
        ProviderModel providerDB = providerRepository.findByCuit(providerDTO.getCuit());

        log.info("CUIT del proveedor en la DB : " + providerDB);

        if (providerDB == null) {
            providerDB = mapper.convertValue(providerDTO, ProviderModel.class);
            providerRepository.save(providerDB);
        } else {
            throw new ResourceAlreadyExistException("El proveedor ya se encuentra en la DB");
        }

        return mapper.convertValue(providerDB, ProviderDTO.class);
    }

    @Override
    public List<ProviderDTO> findAll() {
        List<ProviderDTO> providerDTOList = new ArrayList<>();
        List<ProviderModel> providerList = providerRepository.findAll();

        log.info("La lista de proveedores guardados en la DB es : " + providerList);

        for (ProviderModel provider : providerList) {

            ProviderDTO providerDTO = mapper.convertValue(provider, ProviderDTO.class);
            providerDTOList.add(providerDTO);
        }
        return providerDTOList;
    }

    @Override
    public ProviderDTO edit(ProviderDTO providerDTO, Long id) throws ResourceNotFoundException {

        log.info("El proveedor recibido desde el frontend es : " + providerDTO);

        Optional<ProviderModel> providerOp = providerRepository.findById(id);

        log.info("Id del proveedor extraido de la DB : " + providerOp);

        if (providerOp.isEmpty()) {
            throw new ResourceNotFoundException("El proveedor que desea editar no existe en la DB");
        } else {
            providerValidation.validateAll(providerDTO);
            ProviderModel provider = providerOp.get();
            provider.setName(providerDTO.getName());
            provider.setCuit(providerDTO.getCuit());
            provider.setPhone(providerDTO.getPhone());
            provider.setAddress(providerDTO.getAddress());

            providerRepository.save(provider);
            return mapper.convertValue(provider, ProviderDTO.class);
        }
    }

    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {

        log.info("El id del proveedor recibido desde el frontend es : " + id);

        Optional<ProviderModel> providerDB = providerRepository.findById(id);

        log.info("Proveedor en la DB : " + providerDB);

        if (providerDB.isEmpty()) {
            throw new ResourceNotFoundException("El proveedor  que quiere eliminar no se encuentra guardado en la DB");
        } else {
            ProviderModel provider = providerDB.get();
            provider.setActive(false);
            provider = providerRepository.save(provider);
            return !provider.isActive();
        }
    }

}

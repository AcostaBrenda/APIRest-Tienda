package com.proyecto.desafioTienda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.desafioTienda.dto.CustomerDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.model.CustomerModel;
import com.proyecto.desafioTienda.repository.CustomerRepository;
import com.proyecto.desafioTienda.validation.CustomerValidation;
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
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerValidation customerValidation;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public CustomerDTO findById(Long id) throws ResourceNotFoundException {

        log.info("El id del cliente recibido desde el frontend es : " + id);

        if (id <= 0) {
            throw new IllegalArgumentException("El id del cliente no puede ser menor o igual a 0");
        }
        Optional<CustomerModel> customerOp = customerRepository.findById(id);

        log.info("Se encontro el siguiente id del cliente: " + customerOp);

        if (customerOp.isEmpty()) {
            throw new ResourceNotFoundException("El cliente con el id " + id + " no existe");
        }
        CustomerModel customer = customerOp.get();
        return mapper.convertValue(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) throws ResourceAlreadyExistException {

        log.info("El cliente recibido desde el frontend es : " + customerDTO);

        customerValidation.validateAll(customerDTO);
        CustomerModel customerDB = customerRepository.findByDni(customerDTO.getDni());

        log.info("DNI del cliente en la DB : " + customerDB);

        if (customerDB == null) {
            customerDB = mapper.convertValue(customerDTO, CustomerModel.class);
            customerRepository.save(customerDB);
        } else {
            throw new ResourceAlreadyExistException("El cliente ya se encuentra en la DB");
        }

        return mapper.convertValue(customerDB, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<CustomerModel> customerList = customerRepository.findAll();

        log.info("La lista de cliente guardados en la DB es : " + customerList);

        for (CustomerModel customer : customerList) {

            CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public CustomerDTO edit(CustomerDTO customerDTO, Long id) throws ResourceNotFoundException {

        log.info("El cliente recibido desde el frontend es : " + customerDTO);

        Optional<CustomerModel> customerOp = customerRepository.findById(id);

        log.info("Id del cliente extraido de la DB : " + customerOp);

        if (customerOp.isEmpty()) {
            throw new ResourceNotFoundException("El cliente que desea editar no existe en la DB");
        } else {
            customerValidation.validateAll(customerDTO);
            CustomerModel customer = customerOp.get();
            customer.setName(customerDTO.getName());
            customer.setSurname(customerDTO.getSurname());
            customer.setDni(customer.getDni());
            customer.setPhone(customerDTO.getPhone());
            customer.setAddress(customerDTO.getAddress());
            customerRepository.save(customer);
            return mapper.convertValue(customer, CustomerDTO.class);
        }
    }

    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {

        log.info("El id del cliente recibido desde el frontend es : " + id);

        Optional<CustomerModel> customerDB = customerRepository.findById(id);

        log.info("Cliente en la DB : " + customerDB);

        if (customerDB.isEmpty()) {
            throw new ResourceNotFoundException("El cliente que quiere eliminar no se encuentra guardado en la DB");
        } else {
            CustomerModel customer = customerDB.get();
            customer.setActive(false);
            customer = customerRepository.save(customer);
            return !customer.isActive();
        }
    }
}

package com.proyecto.desafioTienda.controller;

import com.proyecto.desafioTienda.dto.CustomerDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity <CustomerDTO> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        CustomerDTO customerDTO = customerService.findById(id);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) throws ResourceAlreadyExistException {
        CustomerDTO customerDTONew = customerService.save(customerDTO);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List <CustomerDTO> customerDTO = customerService.findAll();
        return ResponseEntity.ok(customerDTO);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity <CustomerDTO> edit(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(customerService.edit(customerDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(customerService.delete(id));
    }
}

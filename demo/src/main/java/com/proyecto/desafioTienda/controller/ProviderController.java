package com.proyecto.desafioTienda.controller;


import com.proyecto.desafioTienda.dto.ProviderDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/provider")
public class ProviderController {


    @Autowired
    private ProviderService providerService;

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ProviderDTO providerDTO = providerService.findById(id);
        return ResponseEntity.ok(providerDTO);
    }

    @PostMapping()
    public ResponseEntity<ProviderDTO> save(@RequestBody ProviderDTO providerDTO) throws ResourceAlreadyExistException {
        ProviderDTO providerDTONew = providerService.save(providerDTO);
        return ResponseEntity.ok(providerDTONew);
    }
    @GetMapping()
    public ResponseEntity<List<ProviderDTO>> findAll(){
        List <ProviderDTO> providerDTOS = providerService.findAll();
        return ResponseEntity.ok(providerDTOS);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity <ProviderDTO> edit(@RequestBody ProviderDTO providerDTO, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(providerService.edit(providerDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(providerService.delete(id));
    }
}

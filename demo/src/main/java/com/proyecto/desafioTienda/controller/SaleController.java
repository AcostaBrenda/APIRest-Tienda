package com.proyecto.desafioTienda.controller;


import com.proyecto.desafioTienda.dto.SaleDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        SaleDTO saleDTO = saleService.findById(id);
        return ResponseEntity.ok(saleDTO);
    }

    @PostMapping()
    public ResponseEntity<SaleDTO> save(@RequestBody SaleDTO saleDTO) throws ResourceNotFoundException {
        SaleDTO saleDTONew = saleService.save(saleDTO);
        return ResponseEntity.ok(saleDTONew);
    }
    @GetMapping()
    public ResponseEntity<List<SaleDTO>> findAll(){
        List <SaleDTO> saleDTOS = saleService.findAll();
        return ResponseEntity.ok(saleDTOS);
    }

    @GetMapping("/bydate/{date}")
    public ResponseEntity<List<SaleDTO>> findByDate(@PathVariable LocalDate date){
        List <SaleDTO> saleDTOSList = saleService.findByDate(date);
        return ResponseEntity.ok(saleDTOSList);
    }
}

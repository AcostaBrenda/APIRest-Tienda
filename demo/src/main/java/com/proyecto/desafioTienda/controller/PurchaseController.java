package com.proyecto.desafioTienda.controller;

import com.proyecto.desafioTienda.dto.PurchaseDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        PurchaseDTO purchaseDTO = purchaseService.findById(id);
        return ResponseEntity.ok(purchaseDTO);
    }

    @PostMapping()
    public ResponseEntity<PurchaseDTO> save(@RequestBody PurchaseDTO purchaseDTO) throws ResourceNotFoundException {
        PurchaseDTO purchaseDTONew = purchaseService.save(purchaseDTO);
        return ResponseEntity.ok(purchaseDTONew);
    }
    @GetMapping()
    public ResponseEntity<List<PurchaseDTO>> findAll(){
        List <PurchaseDTO> purchaseDTOS = purchaseService.findAll();
        return ResponseEntity.ok(purchaseDTOS);
    }

    @GetMapping("/byprovider/{id}")
    public ResponseEntity<List<PurchaseDTO>>findByProvider(@PathVariable ("id") Long id) throws ResourceNotFoundException {
        List <PurchaseDTO> purchaseDTOList = purchaseService.findByProvider(id);
        return ResponseEntity.ok(purchaseDTOList);
    }
}

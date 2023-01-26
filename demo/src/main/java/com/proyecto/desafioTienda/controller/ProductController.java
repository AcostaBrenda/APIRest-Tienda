package com.proyecto.desafioTienda.controller;


import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) throws ResourceAlreadyExistException {
        ProductDTO productDTONew = productService.save(productDTO);
        return ResponseEntity.ok(productDTONew);
    }
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll(){
        List <ProductDTO> productDTOList = productService.findAll();
        return ResponseEntity.ok(productDTOList);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity <ProductDTO> edit(@RequestBody ProductDTO productDTO, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.edit(productDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.delete(id));
    }

    @GetMapping("/stockminimum")
    public ResponseEntity<List<ProductDTO>> findByAllProductWithStockMinimum(){
        List <ProductDTO> productDTOListStockMinimum = productService.findByAllProductWithStockMinimum();
        return ResponseEntity.ok(productDTOListStockMinimum);
    }
}

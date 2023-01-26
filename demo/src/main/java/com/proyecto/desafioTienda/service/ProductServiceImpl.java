package com.proyecto.desafioTienda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.exception.ResourceAlreadyExistException;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.model.ProductModel;
import com.proyecto.desafioTienda.model.ProviderModel;
import com.proyecto.desafioTienda.repository.ProductRepository;
import com.proyecto.desafioTienda.validation.ProductValidation;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public ProductDTO findById(Long id) throws ResourceNotFoundException {

        log.info("El id del producto recibido desde el frontend es : " + id);

        if (id <= 0) {
            throw new IllegalArgumentException("El id del producto no puede ser menor o igual a 0");
        }
        Optional<ProductModel> productOp = productRepository.findById(id);

        log.info("Se encontro el siguiente id del producto: " + productOp);

        if (productOp.isEmpty()) {
            throw new ResourceNotFoundException("El producto con el id " + id + " no existe");
        }
        ProductModel product = productOp.get();
        return mapper.convertValue(product, ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) throws ResourceAlreadyExistException {

        log.info("El producto recibido desde el frontend es : " + productDTO);

        productValidation.validateAll(productDTO);
        ProductModel productDB = productRepository.findByName(productDTO.getName());

        log.info("Nombre del producto en la DB : " + productDB);

        if (productDB == null) {
            productDB = mapper.convertValue(productDTO, ProductModel.class);
            productRepository.save(productDB);
        } else {
            throw new ResourceAlreadyExistException("El producto ya se encuentra en la DB");
        }

        return mapper.convertValue(productDB, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<ProductModel> productList = productRepository.findAll();

        log.info("La lista de productos guardados en la DB es : " + productList);

        for (ProductModel product : productList) {

            ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public ProductDTO edit(ProductDTO productDTO, Long id) throws ResourceNotFoundException {

        log.info("El producto recibido desde el frontend es : " + productDTO);

        Optional<ProductModel> productOp = productRepository.findById(id);

        log.info("Id del producto extraido de la DB : " + productOp);

        if (productOp.isEmpty()) {
            throw new ResourceNotFoundException("El producto que desea editar no existe en la DB");
        } else {
            productValidation.validateAll(productDTO);
            ProductModel product = productOp.get();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setStockMinimum(productDTO.getStockMinimum());

            ProviderModel provider = mapper.convertValue(productDTO.getProvider(), ProviderModel.class);
            product.setProvider(provider);

            productRepository.save(product);
            return mapper.convertValue(product, ProductDTO.class);
        }
    }

    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {

        log.info("El id del producto recibido desde el frontend es : " + id);

        Optional<ProductModel> productDB = productRepository.findById(id);

        log.info("Producto en la DB : " + productDB);

        if (productDB.isEmpty()) {
            throw new ResourceNotFoundException("El producto que quiere eliminar no se encuentra guardado en la DB");
        } else {
            ProductModel product = productDB.get();
            product.setActive(false);
            product = productRepository.save(product);
            return !product.isActive();
        }
    }

    @Override
    public List<ProductDTO> findByAllProductWithStockMinimum() {
        List<ProductDTO> productDTOListStockMinimum = new ArrayList<>();
        List<ProductModel> productList = productRepository.findByAllProductWithStockMinimum();

        log.info("La lista de productos con stock minimo en la DB es : " + productList);

        for (ProductModel product : productList) {

            ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);
            productDTOListStockMinimum.add(productDTO);
        }
        return productDTOListStockMinimum;
    }
}
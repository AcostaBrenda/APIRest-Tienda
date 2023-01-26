package com.proyecto.desafioTienda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.desafioTienda.dto.ProductDTO;
import com.proyecto.desafioTienda.dto.SaleDTO;
import com.proyecto.desafioTienda.dto.SaleDetailDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.model.CustomerModel;
import com.proyecto.desafioTienda.model.ProductModel;
import com.proyecto.desafioTienda.model.SaleModel;
import com.proyecto.desafioTienda.repository.CustomerRepository;
import com.proyecto.desafioTienda.repository.ProductRepository;
import com.proyecto.desafioTienda.repository.SaleRepository;
import com.proyecto.desafioTienda.validation.SaleValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Transactional
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleValidation saleValidation;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public SaleDTO findById(Long id) throws ResourceNotFoundException {

        log.info("El id de la venta recibida desde el frontend es : " + id);

        if (id <= 0) {
            throw new IllegalArgumentException("El id de la venta  no puede ser menor o igual a 0");
        }
        Optional<SaleModel> saleOp = saleRepository.findById(id);

        log.info("Se encontro el siguiente id de la venta : " + saleOp);

        if (saleOp.isEmpty()) {
            throw new ResourceNotFoundException("La venta con el id " + id + " no existe");
        }
        SaleModel sale = saleOp.get();
        return mapper.convertValue(sale, SaleDTO.class);
    }

    @Override
    public SaleDTO save(SaleDTO saleDTO) throws ResourceNotFoundException {

        log.info("La venta recibida desde el frontend es : " + saleDTO);

        saleValidation.validateAll(saleDTO);

        CustomerModel customerModel = customerRepository.findByDni(saleDTO.getCustomerDTO().getDni());
        if (customerModel == null) {
            throw new IllegalArgumentException("El cliente no se encuenta en la DB, debe agregarlo");
        }

         List<SaleDetailDTO> detailDTOS = saleDTO.getSaleDetails();

        for (SaleDetailDTO item : detailDTOS) {
            ProductDTO productDTO = item.getProduct();
            ProductModel product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
            if (productDTO.getStock() > product.getStock()) {
                throw new IllegalArgumentException("Producto sin stock suficiente [" + product.getName() + " ] - stock actual: " + product.getStock());
            }
            productDTO.setStock(product.getStock() - productDTO.getStock());
        }

        SaleModel saleNew = mapper.convertValue(saleDTO, SaleModel.class);
        saleRepository.saveAndFlush(saleNew);

        return mapper.convertValue(saleNew, SaleDTO.class);
    }

    @Override
    public List<SaleDTO> findAll() {
        List<SaleDTO> saleDTOList = new ArrayList<>();
        List<SaleModel> saleList = saleRepository.findAll();

        log.info("La lista de ventas guardadas en la DB es : " + saleList);

        for (SaleModel sale : saleList) {

            SaleDTO saleDTO = mapper.convertValue(sale, SaleDTO.class);
            saleDTOList.add(saleDTO);
        }
        return saleDTOList;
    }

    @Override
    public List<SaleDTO> findByDate(LocalDate date) {
        List<SaleDTO> saleDTOByDateList = new ArrayList<>();
        List<SaleModel> saleList = saleRepository.findByDate(date);

        log.info("La lista de ventas del dia" + date + " guardadas en la DB es : " + saleList);

        for (SaleModel sale : saleList) {

            SaleDTO saleDTO = mapper.convertValue(sale, SaleDTO.class);
            saleDTOByDateList.add(saleDTO);
        }
        return saleDTOByDateList;
    }

}

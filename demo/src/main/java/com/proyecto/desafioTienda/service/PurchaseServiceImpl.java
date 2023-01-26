package com.proyecto.desafioTienda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.desafioTienda.dto.PurchaseDTO;
import com.proyecto.desafioTienda.dto.PurchaseDetailDTO;
import com.proyecto.desafioTienda.exception.ResourceNotFoundException;
import com.proyecto.desafioTienda.model.*;
import com.proyecto.desafioTienda.repository.ProductRepository;
import com.proyecto.desafioTienda.repository.ProviderRepository;
import com.proyecto.desafioTienda.repository.PurchaseDetailRepository;
import com.proyecto.desafioTienda.repository.PurchaseRepository;
import com.proyecto.desafioTienda.validation.PurchaseValidation;
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
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private PurchaseValidation purchaseValidation;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public PurchaseDTO findById(Long id) throws ResourceNotFoundException {

        log.info("El id de la compra recibida desde el frontend es : " + id);

        if (id <= 0) {
            throw new IllegalArgumentException("El id de la compra no puede ser menor o igual a 0");
        }
        Optional<PurchaseModel> purchaseOp = purchaseRepository.findById(id);

        log.info("Se encontro el siguiente id de la compra : " + purchaseOp);

        if (purchaseOp.isEmpty()) {
            throw new ResourceNotFoundException("La compra con el id " + id + " no existe");
        }
        PurchaseModel purchase = purchaseOp.get();
        return mapper.convertValue(purchase, PurchaseDTO.class);
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) throws ResourceNotFoundException {

        log.info("La compra recibida desde el frontend es : " + purchaseDTO);

        purchaseValidation.validateAll(purchaseDTO);

        ProviderModel provider = providerRepository.findByCuit(purchaseDTO.getProviderDTO().getCuit());
        if (provider == null) {
            throw new IllegalArgumentException("El proveedor no se encuenta en la DB");
        }

        PurchaseModel purchaseNew = mapper.convertValue(purchaseDTO, PurchaseModel.class);
        purchaseRepository.save(purchaseNew);

        return mapper.convertValue(purchaseNew, PurchaseDTO.class);
    }


    @Override
    public List<PurchaseDTO> findAll() {
        List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
        List<PurchaseModel> purchaseList = purchaseRepository.findAll();

        log.info("La lista de compras guardadas en la DB es : " + purchaseList);

        for (PurchaseModel purchase : purchaseList) {

            PurchaseDTO purchaseDTO = mapper.convertValue(purchase, PurchaseDTO.class);
            purchaseDTOList.add(purchaseDTO);
        }
        return purchaseDTOList;
    }

    @Override
    public List<PurchaseDTO> findByProvider(Long id) throws ResourceNotFoundException {

        if(id <= 0){
            throw new IllegalArgumentException("El id del proveedor es invalido");
        }
        ProviderModel provider= providerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe ningun proveedor con ese id"));

        log.info("Proveedor encontrado: " + provider);
        List<PurchaseDTO> purchaseDTObyProviderList = new ArrayList<>();
        List<PurchaseModel> purchase = purchaseRepository.findByProvider(id);

        log.info("Compras de este proveedor " + purchase);

        for (PurchaseModel purchaseModel : purchase) {

            log.info("compra id : " + purchaseModel.getId());

            List<PurchaseDetailModel> purchaseDetailModelList = purchaseDetailRepository.findByPurchaseId(purchaseModel.getId());

            List<PurchaseDetailDTO> detailDTOS= new ArrayList<>();
            for (PurchaseDetailModel purchaseDetailModel : purchaseDetailModelList){
            PurchaseDetailDTO purchaseDetailDTO= mapper.convertValue(purchaseDetailModel, PurchaseDetailDTO.class);
            detailDTOS.add(purchaseDetailDTO);
            }

            PurchaseDTO purchaseDTO = mapper.convertValue(purchase, PurchaseDTO.class);
            purchaseDTO.setPurchaseDetails(detailDTOS);
            purchaseDTObyProviderList.add(purchaseDTO);
        }
        return purchaseDTObyProviderList ;
    }

}

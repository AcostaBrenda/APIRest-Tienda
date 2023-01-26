package com.proyecto.desafioTienda.repository;


import com.proyecto.desafioTienda.model.ProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderModel, Long> {

    ProviderModel findByCuit(String cuit);
}

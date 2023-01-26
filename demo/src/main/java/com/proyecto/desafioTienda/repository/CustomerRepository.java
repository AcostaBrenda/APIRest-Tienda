package com.proyecto.desafioTienda.repository;

import com.proyecto.desafioTienda.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    CustomerModel findByDni(String dni);

}

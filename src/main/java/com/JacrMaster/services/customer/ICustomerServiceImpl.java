package com.JacrMaster.services.customer;

import com.JacrMaster.presentation.dto.CustomerDTO;


import java.util.List;

public interface ICustomerServiceImpl {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    String deleteCustomer(Long id);
    List<CustomerDTO> findAll();
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
}

package com.JacrMaster.services.customer;

import com.JacrMaster.exception.exception.ResourceNotFoundException;
import com.JacrMaster.models.Customer;
import com.JacrMaster.presentation.dto.CustomerDTO;
import com.JacrMaster.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService implements ICustomerServiceImpl{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer customerSaved = customerRepository.save(customer);
        return this.modelMapper.map(customerSaved, CustomerDTO.class);

    }

    @Override
    public String deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe cliente para eliminar"));

        customerRepository.deleteById(id);
        return "Eliminado con exito";
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = (List<Customer>) this.customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no existe"));
        customer.setId(id);
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());

        Customer customerUpdate = customerRepository.save(customer);
        return this.modelMapper.map(customerUpdate, CustomerDTO.class);
    }
}

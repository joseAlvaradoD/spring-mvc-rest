package jjad.springframework.services;

import jjad.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO patchCustomer(CustomerDTO customerDTO);
    void deleteCustomerById(Long id);

}

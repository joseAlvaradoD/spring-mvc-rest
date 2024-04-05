package jjad.springframework.services;

import jjad.springframework.api.v1.mapper.CustomerMapper;
import jjad.springframework.api.v1.model.CustomerDTO;
import jjad.springframework.domain.Customer;
import jjad.springframework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new); //todo implement better exception handling;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer savedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));

        return customerMapper.customerToCustomerDTO(savedCustomer);

    }

    @Override
    public CustomerDTO patchCustomer(CustomerDTO customerDTO) {

            return customerRepository.findById(customerDTO.getId()).map(customer -> {
                if(customerDTO.getFirstname() != null){
                    customer.setFirstname(customerDTO.getFirstname());
                }

                if(customerDTO.getLastname() != null){
                    customer.setLastname(customerDTO.getLastname());
                }

                return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            }).orElseThrow(RuntimeException::new); //todo implement better exception handling;

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}

package jjad.springframework.api.v1.mapper;


import jjad.springframework.api.v1.model.CustomerDTO;
import jjad.springframework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerURL", expression = "java(\"/api/v1/customers/\"+customer.getId())")
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}

package jjad.springframework.api.v1.mapper;

import jjad.springframework.api.v1.model.CustomerDTO;
import jjad.springframework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String FIRSTNAME = "Jose";
    public static final String LASTNAME = "Alvarado";
    public static final Long ID = 1L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());

    }

    @Test
    void customerDTOToCustomer() {

        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(ID, customerDTO.getId());
        assertEquals("/api/v1/customers/1", customerDTO.getCustomerURL());
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());

    }
}
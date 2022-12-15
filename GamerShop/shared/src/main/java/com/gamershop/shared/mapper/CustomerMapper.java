package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerMapper {
    private final AddressMapper addressMapper;

    public CustomerMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public CustomerDTO toDTO(CustomerEntity customerEntity){
        Integer id = customerEntity.getCustomerId();
        String customerName = customerEntity.getCustomerName();
        String customerSurname = customerEntity.getCustomerSurname();
        String customerPhoneNumber = customerEntity.getCustomerPhoneNumber();
        Date customerBirthday = customerEntity.getCustomerBirthday();
        Integer customerUserId = customerEntity.getCustomerUserId();
        List<AddressDTO> customerAddresses = customerEntity.getCustomerAddresses().stream().map(addressMapper::toDTO).toList();
        return new CustomerDTO(id, customerName, customerSurname, customerPhoneNumber, customerBirthday, customerUserId, customerAddresses);
    }

    public CustomerEntity toCustomer(CustomerDTO customerDTO){
        return new CustomerEntity(customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerSurname(),
                customerDTO.getCustomerPhoneNumber(),
                customerDTO.getCustomerBirthday(),
                customerDTO.getCustomerUserId(),
                new ArrayList<>());
    }
}

package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerMapper {

    public CustomerDTO toDTO(CustomerEntity customerEntity){
        Integer id = customerEntity.getCustomerId();
        String customerName = customerEntity.getCustomerName();
        String customerSurname = customerEntity.getCustomerSurname();
        String customerPhoneNumber = customerEntity.getCustomerPhoneNumber();
        LocalDate customerBirthday = customerEntity.getCustomerBirthday();
        Integer customerUserId = customerEntity.getCustomerUserId();
        return new CustomerDTO(id, customerName, customerSurname, customerPhoneNumber, customerBirthday, customerUserId);
    }

    public CustomerEntity toCustomer(CustomerDTO customerDTO){
        return new CustomerEntity(customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerSurname(),
                customerDTO.getCustomerPhoneNumber(),
                customerDTO.getCustomerBirthday());
    }
}

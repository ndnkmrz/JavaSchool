package com.gamershop.customer.customer.interfaces;

import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.UserDTO;

public interface ICustomerService {
    void saveUser(UserDTO userDTO);
    UserDTO getUserByEmail(String email);
    CustomerDTO getCustomerByUserId(Integer id);
    void saveCustomer(CustomerDTO customerDTO);
    void saveAddress(AddressDTO addressDTO);
    AddressDTO getAddressById(Integer id);
}

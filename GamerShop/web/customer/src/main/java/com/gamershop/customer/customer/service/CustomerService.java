package com.gamershop.customer.customer.service;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.customer.customer.interfaces.IRoleService;
import com.gamershop.customer.customer.repo.AddressRepository;
import com.gamershop.customer.customer.repo.CustomerRepository;
import com.gamershop.customer.customer.repo.UserRepository;
import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.AddressEntity;
import com.gamershop.shared.entity.CustomerEntity;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import com.gamershop.shared.mapper.AddressMapper;
import com.gamershop.shared.mapper.CustomerMapper;
import com.gamershop.shared.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepo,
                           UserRepository userRepo,
                           AddressRepository addressRepo, UserMapper userMapper,
                           CustomerMapper customerMapper, AddressMapper addressMapper, IRoleService roleService, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
        this.addressMapper = addressMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO){
        if (userDTO.getId() != null){
            if(userDTO.getPassword() == null) {
                UserEntity user = getUserById(userDTO.getId());
                userDTO.setPassword(user.getPasswordHash());
            }
            else{
                encodePassword(userDTO);
            }
        }
        else{
            encodePassword(userDTO);
        }
        UserEntity userEntity = userMapper.toUser(userDTO);
        RoleEntity customerRole = roleService.getOrCreateRole("Customer");
        userEntity.addRole(customerRole);
        UserEntity user = userRepo.save(userEntity);
        try{
            getCustomerByUserId(user.getUserId());
        }
        catch (Exception ex){
            CustomerEntity customerEntity = new CustomerEntity(user.getUserId());
            customerRepo.save(customerEntity);
        }
    }

    public void saveCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = customerMapper.toCustomer(customerDTO);
        customerRepo.save(customerEntity);
    }
    private void encodePassword(UserDTO user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public UserDTO getUserByEmail(String email){
        UserEntity userEntity = userRepo.getUserEntityByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with email: " + email));
        return userMapper.toDTO(userEntity);
    }
    public UserEntity getUserById(Integer id){
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
    }
    public CustomerDTO getCustomerByUserId(Integer id){
        CustomerEntity customerEntity = customerRepo.findCustomerEntityByCustomerUserId(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
        return customerMapper.toDTO(customerEntity);
    }

    public CustomerEntity getCustomerById(Integer id){
        return customerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
    }

    public void saveAddress(AddressDTO addressDTO){
        AddressEntity address = addressMapper.toAddress(addressDTO);
        address.setAddressCustomer(getCustomerById(addressDTO.getAddressCustomer()));
        addressRepo.save(address);
    }

    public AddressDTO getAddressById(Integer id){
        AddressEntity address = addressRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find address with id: " + id));
        return addressMapper.toDTO(address);
    }


}

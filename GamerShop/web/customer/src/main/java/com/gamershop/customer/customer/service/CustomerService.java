package com.gamershop.customer.customer.service;

import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.customer.customer.interfaces.IRoleService;
import com.gamershop.customer.customer.repo.CustomerRepository;
import com.gamershop.customer.customer.repo.UserRepository;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.CustomerEntity;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import com.gamershop.shared.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepo,
                           UserRepository userRepo,
                           UserMapper userMapper,
                           IRoleService roleService, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveCustomer(UserDTO userDTO){
        encodePassword(userDTO);
        UserEntity userEntity = userMapper.toUser(userDTO);
        RoleEntity customerRole = roleService.getOrCreateRole("Customer");
        userEntity.addRole(customerRole);
        UserEntity user = userRepo.save(userEntity);
        CustomerEntity customerEntity = new CustomerEntity(user.getUserId());
        customerRepo.save(customerEntity);
    }
    private void encodePassword(UserDTO user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}

package com.gamershop.customer.customer.service;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.customer.customer.interfaces.IRoleService;
import com.gamershop.customer.customer.repo.AddressRepository;
import com.gamershop.customer.customer.repo.CustomerRepository;
import com.gamershop.customer.customer.repo.UserRepository;
import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.AddressEntity;
import com.gamershop.shared.entity.CustomerEntity;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import com.gamershop.shared.mapper.AddressMapper;
import com.gamershop.shared.mapper.CustomerMapper;
import com.gamershop.shared.mapper.OrderMapper;
import com.gamershop.shared.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService implements ICustomerService{
    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final AddressRepository addressRepo;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final OrderMapper orderMapper;

    public CustomerService(CustomerRepository customerRepo,
                           UserRepository userRepo,
                           AddressRepository addressRepo, UserMapper userMapper,
                           CustomerMapper customerMapper, AddressMapper addressMapper, IRoleService roleService, PasswordEncoder passwordEncoder, OrderMapper orderMapper) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
        this.addressMapper = addressMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.orderMapper = orderMapper;
    }
    @Transactional
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
            CustomerEntity customerEntity = new CustomerEntity(user);
            customerRepo.save(customerEntity);
        }
    }
    @Transactional
    public void saveCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = customerMapper.toCustomer(customerDTO);
        UserEntity user = userRepo.findById(customerDTO.getCustomerUserId()).orElseThrow(
                () -> new UserNotFoundException("Can`t find user with id: " + customerDTO.getCustomerUserId())
        );
        customerEntity.setUser(user);
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
        CustomerEntity customerEntity = customerRepo.findCustomerEntityByUser_UserId(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
        return customerMapper.toDTO(customerEntity);
    }

    public CustomerEntity getCustomerEntityByUserId(Integer id){
        return customerRepo.findCustomerEntityByUser_UserId(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
    }
    public List<OrderDTO> getOrdersByUserEmail(String email){
        UserDTO user = getUserByEmail(email);
        CustomerEntity customer = getCustomerEntityByUserId(user.getId());
        return customer.getCustomerOrders().stream().map(orderMapper::toDTO).toList();
    }

    public CustomerEntity getCustomerById(Integer id){
        return customerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find user with id: " + id));
    }
    @Transactional
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

    public AddressEntity getAddressEntityById(Integer id){
        return addressRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can`t find address with id: " + id));
    }


}

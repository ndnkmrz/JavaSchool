package com.gamershop.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Customer")
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String customerSurname;
    private String customerPhoneNumber;
    private Date customerBirthday;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;

    @OneToMany(mappedBy = "addressCustomer")
    private List<AddressEntity> customerAddresses;

    @OneToMany(mappedBy = "orderCustomer")
    private List<OrderEntity> customerOrders;

    public CustomerEntity(Integer customerId,
                          String customerName,
                          String customerSurname,
                          String customerPhoneNumber,
                          Date customerBirthday,
                          List<AddressEntity> addresses,
                          List<OrderEntity> orders) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerBirthday = customerBirthday;
        this.customerAddresses = addresses;
        this.customerOrders = orders;
    }

    public CustomerEntity(UserEntity user) {
        this.user = user;
    }

    public CustomerEntity() {

    }
}

package com.gamershop.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Integer customerUserId;

    @OneToMany(mappedBy = "addressCustomer")
    private List<AddressEntity> customerAddresses;

    public CustomerEntity(Integer customerId,
                          String customerName,
                          String customerSurname,
                          String customerPhoneNumber,
                          Date customerBirthday) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerBirthday = customerBirthday;
    }

    public CustomerEntity(Integer customerUserId) {
        this.customerUserId = customerUserId;
    }

    public CustomerEntity() {

    }
}

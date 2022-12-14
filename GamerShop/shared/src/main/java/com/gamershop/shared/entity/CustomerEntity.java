package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String customerSurname;

    private String customerPhoneNumber;
    private LocalDate customerBirthday;

    private Integer customerUserId;


    public CustomerEntity(Integer customerId,
                          String customerName,
                          String customerSurname,
                          String customerPhoneNumber,
                          LocalDate customerBirthday) {
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

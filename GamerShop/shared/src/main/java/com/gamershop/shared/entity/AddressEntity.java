package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
@Table(name="Address")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String addressCountry;
    private String addressCity;
    private Integer addressPostCode;
    private String addressStreet;
    private Integer addressHouseNum;
    private String addressApartNum;
    private boolean enable;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private CustomerEntity addressCustomer;

    @OneToMany(mappedBy = "orderAddress")
    private List<OrderEntity> addressOrders;

    public AddressEntity() {
    }

    public AddressEntity(Integer addressId,
                         String addressCountry,
                         String addressCity,
                         Integer addressPostCode,
                         String addressStreet,
                         Integer addressHouseNum,
                         String addressApartNum,
                         boolean enable) {
        this.addressId = addressId;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressPostCode = addressPostCode;
        this.addressStreet = addressStreet;
        this.addressHouseNum = addressHouseNum;
        this.addressApartNum = addressApartNum;
        this.enable = enable;
    }
}

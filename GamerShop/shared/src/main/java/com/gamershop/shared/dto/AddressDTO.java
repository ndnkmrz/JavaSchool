package com.gamershop.shared.dto;

import com.gamershop.shared.entity.CustomerEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Integer addressId;
    private String addressCountry;
    private String addressCity;
    private Integer addressPostCode;
    private String addressStreet;
    private Integer addressHouseNum;
    private String addressApartNum;
    private boolean enable = true;
    private Integer addressCustomer;
    private List<OrderDTO> orderAddresses;
    @Override
    public String toString(){
        return this.addressCountry + ", " + this.addressCity + "\n" + this.addressStreet + ", " + this.addressHouseNum
                + ", " + this.addressApartNum +  "\n" + this.addressPostCode;
    }
}

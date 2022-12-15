package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.entity.AddressEntity;
import com.gamershop.shared.entity.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public AddressDTO toDTO(AddressEntity addressEntity){
        Integer addressId = addressEntity.getAddressId();
        String addressCountry = addressEntity.getAddressCountry();
        String addressCity = addressEntity.getAddressCity();
        Integer addressPostCode = addressEntity.getAddressPostCode();
        String addressStreet = addressEntity.getAddressStreet();
        Integer addressHouseNum = addressEntity.getAddressHouseNum();
        String addressApartNum = addressEntity.getAddressApartNum();
        boolean enable = addressEntity.isEnable();
        Integer addressCustomer = addressEntity.getAddressCustomer().getCustomerId();
        return new AddressDTO(addressId,
                addressCountry,
                addressCity,
                addressPostCode,
                addressStreet,
                addressHouseNum,
                addressApartNum,
                enable,
                addressCustomer);
    }

    public AddressEntity toAddress(AddressDTO addressDTO){
        return new AddressEntity(addressDTO.getAddressId(),
                addressDTO.getAddressCountry(),
                addressDTO.getAddressCity(),
                addressDTO.getAddressPostCode(),
                addressDTO.getAddressStreet(),
                addressDTO.getAddressHouseNum(),
                addressDTO.getAddressApartNum(),
                addressDTO.isEnable());
    }
}

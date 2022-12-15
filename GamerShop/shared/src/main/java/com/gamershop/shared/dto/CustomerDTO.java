package com.gamershop.shared.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Integer customerId;
    private String customerName;
    private String customerSurname;
    private String customerPhoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date customerBirthday;
    private Integer customerUserId;
    private List<AddressDTO> customerAddresses;

}

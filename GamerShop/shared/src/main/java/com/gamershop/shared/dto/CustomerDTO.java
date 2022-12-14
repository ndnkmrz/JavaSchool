package com.gamershop.shared.dto;

import lombok.*;

import java.time.LocalDate;

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
    private LocalDate customerBirthday;
    private Integer customerUserId;

}

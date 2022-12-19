package com.gamershop.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReportDTO {
    private String productName;
    private Date orderDate;
    private Double totalSum;
    private String customerEmail;
}

package com.gamershop.admin.report.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ReportRange implements Serializable {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxDate;
    public ReportRange(){
        this.minDate = Date.from(LocalDate.now().minusMonths(1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.maxDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

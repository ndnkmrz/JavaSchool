package com.gamershop.admin.report.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gamershop.admin.report.utils.ReportRange;
import com.gamershop.shared.dto.ReportDTO;
import com.gamershop.shared.entity.OrderEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IReportService {
    List<String> getRawData(ReportRange range) throws ParseException, JsonProcessingException;
}

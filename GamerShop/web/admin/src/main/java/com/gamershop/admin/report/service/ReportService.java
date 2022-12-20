package com.gamershop.admin.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamershop.admin.order.repo.OrderProductRepository;
import com.gamershop.admin.report.interfaces.IReportService;
import com.gamershop.admin.report.utils.ReportRange;
import com.gamershop.shared.dto.ReportDTO;
import com.gamershop.shared.entity.OrderProductEntity;
import com.gamershop.shared.mapper.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReportService implements IReportService {
    private final OrderProductRepository orderProductRepository;
    private final ReportMapper reportMapper;

    public ReportService(OrderProductRepository orderProductRepository, ReportMapper reportMapper) {
        this.orderProductRepository = orderProductRepository;
        this.reportMapper = reportMapper;
    }

    public List<String> getRawData(ReportRange range) throws ParseException, JsonProcessingException {
        List<OrderProductEntity> entity = orderProductRepository
                .findAllByOrderProductOrder_OrderDateBetween(range.getMinDate(), range.getMaxDate());
        var result = entity.stream().map(reportMapper::toDTO).toList();
        var bestProducts = result.stream()
                .collect(Collectors.groupingBy(ReportDTO::getProductName, Collectors.summingDouble(ReportDTO::getTotalSum)));
        Map<String, Double> sortedProducts = bestProducts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        var bestCustomers = result.stream()
                .collect(Collectors.groupingBy(ReportDTO::getCustomerEmail, Collectors.summingDouble(ReportDTO::getTotalSum)));
        Map<String, Double> sortedCustomers = bestCustomers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        var bestDays = result.stream()
                .collect(Collectors.groupingBy(f -> f.getOrderDate().toString(), Collectors.summingDouble(ReportDTO::getTotalSum)));
        Map<String, Double> sortedDays = bestDays.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Double totalSum = result.stream().mapToDouble(ReportDTO::getTotalSum).sum();
        return new ArrayList<>(
                Arrays.asList(toJson(sortedCustomers), toJson(sortedProducts), toJson(sortedDays), String.valueOf(totalSum)));
    }
    private String toJson(Map<String, Double> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }
}

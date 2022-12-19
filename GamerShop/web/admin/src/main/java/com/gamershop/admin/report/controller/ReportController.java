package com.gamershop.admin.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gamershop.admin.report.interfaces.IReportService;
import com.gamershop.admin.report.utils.ReportRange;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public String getReport(@Param("start") Date start,
                            @Param("end") Date end,
                            Model model,
                            HttpSession session) throws ParseException, JsonProcessingException {
        if(session.getAttribute("range") == null) {
            session.setAttribute("range", new ReportRange());
        }
        ReportRange range = (ReportRange) session.getAttribute("range");
        var data = reportService.getRawData(range);
        String customers = data.get(0);
        String products = data.get(1);
        String days =  data.get(2);
        String total = data.get(3);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("days", days);
        model.addAttribute("total", total);
        model.addAttribute("range", range);
        return "report";
    }

    @PostMapping("/report/change")
    public String changeReport(ReportRange reportRange, HttpSession session) throws ParseException {
        session.setAttribute("range", reportRange);
        return "redirect:/report";
    }
}

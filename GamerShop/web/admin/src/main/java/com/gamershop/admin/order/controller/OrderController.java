package com.gamershop.admin.order.controller;

import com.gamershop.admin.order.interfaces.IOrderService;
import com.gamershop.shared.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String listFirstPage(Model model){
        model.addAttribute("pageTitle", "Orders");
        return listByPage(1, model, null);
    }

    @GetMapping("/orders/page/{pageNum}")
    public String listByPage(@PathVariable(name = "pageNum") int pageNum,
                             Model model,
                             @Param("orderId") Integer orderId){
        Page<OrderDTO> page = orderService.listByPage(pageNum, orderId);
        List<OrderDTO> listOrders = page.getContent();
        model.addAttribute("totalOrders", "Total orders: " + page.getTotalElements());
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("orderId", orderId);
        model.addAttribute("pageTitle", "Orders");
        return "orders";
    }

    @PostMapping("/orders/save")
    public String saveOrder(OrderDTO order, RedirectAttributes redirectAttributes){
        orderService.saveOrder(order);
        redirectAttributes.addFlashAttribute("message", "The status has been saved successfully");
        return "redirect:/orders/edit/"+order.getOrderId();
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable(name = "id") Integer id, Model model){
        OrderDTO order = orderService.getOrderById(id);
        List<String> status = orderService.getOrderStatus();
        model.addAttribute("order", order);
        model.addAttribute("status", status);
        return "order_form";
    }
}

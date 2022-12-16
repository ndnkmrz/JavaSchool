package com.gamershop.customer.order.controller;

import com.gamershop.admin.user.service.UserService;
import com.gamershop.customer.product.interfaces.IProductService;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;


@Controller
public class OrderController {
    private final IProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    public OrderController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/order/add")
    public String addToOrder(OrderProductDTO orderProductDTO,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("productId") Integer productId,
                             HttpSession session){
        ProductDTO productDTO = productService.getProductById(productId);
        orderProductDTO.setOrderProductProduct(productDTO);
        if(session.getAttribute("cart") == null){
            OrderDTO order = new OrderDTO();
            order.setOrderProductsOrders(new ArrayList<>(Arrays.asList(orderProductDTO)));
            session.setAttribute("cart", order);
        }
        else{
            OrderDTO order = (OrderDTO) session.getAttribute("cart");
            var products = order.getOrderProductsOrders();
            boolean isAdded = false;
            for(OrderProductDTO product : products){
                if(product.getOrderProductProduct().getProductId() == orderProductDTO.getOrderProductProduct().getProductId()){
                    product.setOrderProductQuantity(product.getOrderProductQuantity() + orderProductDTO.getOrderProductQuantity());
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded){
                order.addProduct(orderProductDTO);
            }
            session.setAttribute("cart", order);
        }
        return "redirect:/product/" + orderProductDTO.getOrderProductProduct().getProductId();
    }

    @GetMapping("/order")
    public String checkOrder(Model model, HttpSession session){
        OrderDTO order = session.getAttribute("cart") == null ? null : (OrderDTO) session.getAttribute("cart");
        model.addAttribute("order", order);
        return "cart";
    }


}

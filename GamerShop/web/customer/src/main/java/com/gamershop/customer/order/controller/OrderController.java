package com.gamershop.customer.order.controller;

import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.customer.order.interfaces.IOrderService;
import com.gamershop.customer.order.service.OrderService;
import com.gamershop.customer.product.interfaces.IProductService;
import com.gamershop.customer.security.GamershopUserDetails;
import com.gamershop.shared.dto.*;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class OrderController {
    private final IProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final ICustomerService customerService;

    private final IOrderService orderService;
    public OrderController(IProductService productService, ICustomerService customerService, IOrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
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
            var products = new ArrayList<>(order.getOrderProductsOrders());
            boolean isAdded = false;
            for(OrderProductDTO product : products){
                if(product.getOrderProductProduct().getProductId() == orderProductDTO.getOrderProductProduct().getProductId()){
                    product.setOrderProductQuantity(product.getOrderProductQuantity() + orderProductDTO.getOrderProductQuantity());
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded){
                products.add(orderProductDTO);
                order.setOrderProductsOrders(products);
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

    @GetMapping("/order/details")
    public String finishOrder(Model model,
                              HttpSession session,
                              @AuthenticationPrincipal GamershopUserDetails loggedUser){
        String email = loggedUser.getUsername();
        UserDTO user = customerService.getUserByEmail(email);
        CustomerDTO customer = customerService.getCustomerByUserId(user.getId());
        OrderDTO order = (OrderDTO) session.getAttribute("cart");
        order.setOrderCustomer(customer.getCustomerId());
        List<String> paymentMethods = orderService.listPaymentMethods();
        List<String> deliveryMethods = orderService.listDeliveryMethods();
        model.addAttribute("order", order);
        model.addAttribute("listPaymentMethods", paymentMethods);
        model.addAttribute("listDeliveryMethods", deliveryMethods);
        model.addAttribute("customer", customer);
        model.addAttribute("cardTitle", "Create an order");
        model.addAttribute("pageTitle", "Create an order");
        return "order_form";
    }

    @PostMapping("/order/save")
    public String saveOrder(HttpSession session,
                            OrderDTO orderDTO,
                            RedirectAttributes redirectAttributes){
        OrderDTO order = (OrderDTO) session.getAttribute("cart");
        orderDTO.setOrderProductsOrders(order.getOrderProductsOrders());
        orderService.saveOrder(orderDTO);
        session.removeAttribute("cart");
        redirectAttributes.addFlashAttribute("message", "Order was saved successfully");
        return "redirect:/";
    }

    @GetMapping("/order/history")
    public String viewOrderHistory(Model model,
                                   @AuthenticationPrincipal GamershopUserDetails loggedUser){
        String email = loggedUser.getUsername();
        List<OrderDTO> orders = customerService.getOrdersByUserEmail(email);
        model.addAttribute("orders", orders);
        return "order_history";
    }

    @GetMapping("/order/details/{orderId}")
    public String viewOrderDetails(Model model, @PathVariable(name="orderId") Integer orderId){
        OrderDTO order = orderService.getOrderById(orderId);
        AddressDTO address = customerService.getAddressById(order.getOrderAddress());
        model.addAttribute("order", order);
        model.addAttribute("address", address);
        return "order_details";
    }

    @GetMapping("/reorder/{orderId}")
    public String reorder(@PathVariable(name="orderId") Integer orderId,
                          Model model,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        OrderDTO order = orderService.reorder(orderId);
        if (order != null)
        {
            session.setAttribute("cart", order);
            model.addAttribute("order", order);
            return "cart";
        }
        else{
            redirectAttributes.addFlashAttribute("errormessage", "Not enough goods on the stock :(");
            return "redirect:/order/details/" + orderId;
        }
    }
}

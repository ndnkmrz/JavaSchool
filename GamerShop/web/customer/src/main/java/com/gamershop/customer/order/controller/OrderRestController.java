package com.gamershop.customer.order.controller;

import com.gamershop.shared.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class OrderRestController {
    @RequestMapping("/DeleteProduct/{productId}")
    public String changeOrder(HttpSession session,
                              @PathVariable(name="productId") Integer productId){
        OrderDTO order = (OrderDTO) session.getAttribute("cart");
        for(var product : order.getOrderProductsOrders()){
            if(product.getOrderProductProduct().getProductId() == productId){
                order.getOrderProductsOrders().remove(product);
                break;
            }
        }
        session.setAttribute("cart", order);
        return "cart";
    }

    @RequestMapping("ChangeProductQuantity/{productId}/{increase}")
    public String changeAmount(HttpSession session,
                               @PathVariable(name="productId") Integer productId,
                               @PathVariable(name="increase") boolean increase){
        OrderDTO order = (OrderDTO) session.getAttribute("cart");
        for(var product : order.getOrderProductsOrders()){
            if(product.getOrderProductProduct().getProductId() == productId){
                if(increase){
                    if (product.getOrderProductProduct().getProductQuantity() - product.getOrderProductQuantity() == 0){
                        return "ERROR";
                    }
                    else{
                        product.setOrderProductQuantity(product.getOrderProductQuantity() + 1);
                    }
                }
                else{
                    if(product.getOrderProductQuantity() - 1 == 0){
                        order.getOrderProductsOrders().remove(product);
                        break;
                    }
                    else{
                        product.setOrderProductQuantity(product.getOrderProductQuantity() - 1);
                    }
                }
            }
        }
        return "OK";
    }

}

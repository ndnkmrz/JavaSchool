package com.gamershop.customer.order.service;

import com.gamershop.customer.customer.service.CustomerService;
import com.gamershop.customer.exception.OrderNotFoundException;
import com.gamershop.customer.order.interfaces.IOrderService;
import com.gamershop.customer.order.repo.*;
import com.gamershop.customer.product.repo.ProductRepository;
import com.gamershop.customer.product.service.ProductService;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.*;
import com.gamershop.shared.mapper.OrderMapper;
import com.gamershop.shared.mapper.OrderProductMapper;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepo;
    private final PaymentStatusRepository paymentStatusRepo;
    private final OrderStatusRepository orderStatusRepo;
    private final PaymentMethodRepository paymentMethodRepo;
    private final DeliveryMethodRepository deliveryMethodRepo;
    private final CustomerService customerService;
    private final OrderProductMapper orderProductMapper;
    private final ProductService productService;
    private final OrderProductRepository orderProductRepo;
    private final ProductRepository productRepo;


    public OrderService(OrderMapper orderMapper,
                        OrderRepository orderRepo,
                        PaymentStatusRepository paymentStatusRepo,
                        OrderStatusRepository orderStatusRepo,
                        PaymentMethodRepository paymentMethodRepo,
                        DeliveryMethodRepository deliveryMethodRepo, CustomerService customerService, OrderProductMapper orderProductMapper, ProductService productService, OrderProductRepository orderProductRepo, ProductRepository productRepo) {
        this.orderMapper = orderMapper;
        this.orderRepo = orderRepo;
        this.paymentStatusRepo = paymentStatusRepo;
        this.orderStatusRepo = orderStatusRepo;
        this.paymentMethodRepo = paymentMethodRepo;
        this.deliveryMethodRepo = deliveryMethodRepo;
        this.customerService = customerService;
        this.orderProductMapper = orderProductMapper;
        this.productService = productService;
        this.orderProductRepo = orderProductRepo;
        this.productRepo = productRepo;
    }

    public void saveOrder(OrderDTO orderDTO){
        OrderEntity orderEntity = orderMapper.toOrder(orderDTO);
        orderEntity.setOrderCustomer(customerService.getCustomerById(orderDTO.getOrderCustomer()));
        orderEntity.setOrderAddress(customerService.getAddressEntityById(orderDTO.getOrderAddress()));
        orderEntity.setOrderDeliveryMethod(getOrCreateDeliveryMethod(orderDTO.getOrderDeliveryMethod()));
        orderEntity.setOrderPaymentMethod(getOrCreatePaymentMethod(orderDTO.getOrderPaymentMethod()));
        orderEntity.setOrderOrderStatus(getOrCreateOrderStatus("Created"));
        orderEntity.setOrderPaymentStatus(getOrCreatePaymentStatus("Paid"));
        orderEntity.setOrderDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        OrderEntity savedOrder = orderRepo.save(orderEntity);
        for(var product : orderDTO.getOrderProductsOrders()){
            product.setOrderProductId(null);
            createOrderProduct(product, savedOrder);
        }
    }
    private void createOrderProduct(OrderProductDTO orderProductDTO, OrderEntity order){
        OrderProductEntity orderProduct = orderProductMapper.toOrderProduct(orderProductDTO);
        var product = productService.getProductEntityById(orderProductDTO.getOrderProductProduct().getProductId());
        orderProduct.setOrderProductProduct(product);
        orderProduct.setOrderProductOrder(order);
        orderProductRepo.save(orderProduct);
        product.setProductQuantity(product.getProductQuantity() - orderProduct.getOrderProductQuantity());
        productRepo.save(product);
    }
    public PaymentStatusEntity getOrCreatePaymentStatus(String paymentStatusName){
        return paymentStatusRepo.findByPaymentStatusName(paymentStatusName)
                .orElseGet(()-> paymentStatusRepo.save(new PaymentStatusEntity(paymentStatusName)));
    }

    public OrderStatusEntity getOrCreateOrderStatus(String orderStatusName){
        return orderStatusRepo.findByOrderStatusName(orderStatusName)
                .orElseGet(()-> orderStatusRepo.save(new OrderStatusEntity(orderStatusName)));
    }

    public DeliveryMethodEntity getOrCreateDeliveryMethod(String deliveryMethodName){
        return deliveryMethodRepo.findByDeliveryMethodName(deliveryMethodName)
                .orElseGet(() -> deliveryMethodRepo.save(new DeliveryMethodEntity(deliveryMethodName)));
    }

    public PaymentMethodEntity getOrCreatePaymentMethod(String paymentMethodName){
        return paymentMethodRepo.findByPaymentMethodName(paymentMethodName)
                .orElseGet(() -> paymentMethodRepo.save(new PaymentMethodEntity(paymentMethodName)));
    }
    public List<String> listPaymentMethods(){
        var paymentMethodsList = (List<PaymentMethodEntity>) paymentMethodRepo.findAll();
        return paymentMethodsList.stream().map(PaymentMethodEntity::getPaymentMethodName).toList();
    }
    public List<String> listDeliveryMethods(){
        var deliveryMethodsList = (List<DeliveryMethodEntity>) deliveryMethodRepo.findAll();
        return deliveryMethodsList.stream().map(DeliveryMethodEntity::getDeliveryMethodName).toList();
    }

    public OrderDTO getOrderById(Integer id){
        OrderEntity order = orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Could not find order with id " + id));
        return orderMapper.toDTO(order);
    }

    public OrderEntity getOrderEntityById(Integer id){
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Could not find order with id " + id));
    }

    public OrderDTO reorder(Integer orderId){
        OrderEntity order = getOrderEntityById(orderId);
        var products = order.getOrderProductsOrders();
        for(OrderProductEntity product: products){
            if(product.getOrderProductProduct().getProductQuantity() < product.getOrderProductQuantity()){
                return null;
            }
        }
        order.setOrderId(null);
        return orderMapper.toDTO(order);
    }
}

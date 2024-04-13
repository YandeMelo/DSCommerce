package com.yandemelo.dscommercePGAdmin.dto.OrderDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.yandemelo.dscommercePGAdmin.entities.Order;
import com.yandemelo.dscommercePGAdmin.entities.OrderItem;
import com.yandemelo.dscommercePGAdmin.entities.OrderStatus;

import jakarta.validation.constraints.NotEmpty;


public class OrderDTO {
    
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private UserMinDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Must have at least one item")
    private List<OrderItemDTO> items = new ArrayList<>();


    public OrderDTO(Long id, Instant moment, OrderStatus status, UserMinDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new UserMinDTO(entity.getClient());
        this.payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item : entity.getItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            items.add(itemDTO);
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UserMinDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderItemDTO item : items){
            sum += item.getSubTotal();
        }
        return sum;
    }
}

package com.mazhar.order.service;

import com.mazhar.order.dto.OrderLineItemsDto;
import com.mazhar.order.dto.OrderRequest;
import com.mazhar.order.model.Order;
import com.mazhar.order.model.OrderLineItems;
import com.mazhar.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

         List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto().stream()
                .map(orderRequ -> mapToOrder(orderRequ))
                .toList();

         order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToOrder(OrderLineItemsDto orderRequest) {
        OrderLineItems order = new OrderLineItems();
        order.setPrice(orderRequest.getPrice());
        order.setQuantity(orderRequest.getQuantity());
        order.setSkuCode(orderRequest.getSkuCode());
        return order;
    }

}

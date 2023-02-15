package com.mazhar.order.service;

import com.mazhar.order.dto.InventoryResponse;
import com.mazhar.order.dto.OrderLineItemsDto;
import com.mazhar.order.dto.OrderRequest;
import com.mazhar.order.model.Order;
import com.mazhar.order.model.OrderLineItems;
import com.mazhar.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

         List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto().stream()
                .map(orderRequ -> mapToOrder(orderRequ))
                .toList();

         order.setOrderLineItems(orderLineItems);
         //removing all sku codes from order line item to pass it in the request param
        List<String> skucodes = orderLineItems.stream()
                                                .map(orderReq ->orderReq.getSkuCode())
                                                    .toList();
         //call inventory service and check if the product is available or not

        InventoryResponse[] inventoryResponse = webClient.get()
                        .uri("http://localhost:8082/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode",skucodes)
                                        .build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                                .block();
       boolean allInStock = Arrays.stream(inventoryResponse).allMatch(resp -> resp.isInStock());
       if (allInStock){
           orderRepository.save(order);
       }
       else {
           throw new IllegalArgumentException("Product is out of stock !!!!!");
       }

    }

    private OrderLineItems mapToOrder(OrderLineItemsDto orderRequest) {
        OrderLineItems order = new OrderLineItems();
        order.setPrice(orderRequest.getPrice());
        order.setQuantity(orderRequest.getQuantity());
        order.setSkuCode(orderRequest.getSkuCode());
        return order;
    }

}

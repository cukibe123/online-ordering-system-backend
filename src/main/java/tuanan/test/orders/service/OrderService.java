package tuanan.test.orders.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tuanan.test.food.entity.FoodEntity;
import tuanan.test.food.repository.FoodRepository;
import tuanan.test.orders.dto.OrderRequest;
import tuanan.test.orders.dto.OrderResponse;
import tuanan.test.orders.repository.OrderRepository;
import tuanan.test.orders.entity.OrderEntity;
import tuanan.test.orders.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomerId(orderRequest.customerId());
        orderEntity.setCustomerName(orderRequest.customerName());

        for (OrderRequest.OrderLine order : orderRequest.orderList()) {

            //TODO: Enforce if the Food is not active
            FoodEntity food = foodRepository.findById(order.itemId()).orElseThrow(() ->
                    new IllegalArgumentException(
                            "Food item not found: " + order.itemId()
                    ));

            OrderItemEntity orderItem = new OrderItemEntity();

            orderItem.setCount(order.count());
            orderItem.setFoodName(food.getName());
            orderItem.setOrder(orderEntity);
            orderItem.setPrice(food.getPrice());

            BigDecimal totalPrice = food.getPrice().multiply(BigDecimal.valueOf(order.count().longValue()));
            orderItem.setTotalPrice(totalPrice);

            orderEntity.addItems(orderItem);
        }

        orderRepository.save(orderEntity);
        return toOrderResponse(orderEntity);
    }

    private OrderResponse toOrderResponse(OrderEntity orderEntity) {

        List<OrderResponse.OrderItemResponse> listItemResponse = orderEntity.getItems().stream()
                .map(item -> new OrderResponse.OrderItemResponse(item.getId(), item.getCount())).toList();

        return new OrderResponse(orderEntity.getCustomerId(), orderEntity.getCustomerName(), listItemResponse);
    }

    public List<OrderResponse> getAllOrder() {
        List<OrderEntity> list =  orderRepository.findAll();

        return list.stream().map(this::toOrderResponse).toList();
    }

}

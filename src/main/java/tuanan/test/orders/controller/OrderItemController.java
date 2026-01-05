package tuanan.test.orders.controller;

import org.springframework.web.bind.annotation.*;
import tuanan.test.orders.repository.OrderItemRepository;
import tuanan.test.orders.entity.OrderItemEntity;

import java.util.List;

@RestController
@RequestMapping("/api/order-item")
public class OrderItemController {
    private OrderItemRepository repo;

    public OrderItemController(OrderItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<OrderItemEntity> getOrderItems(Long orderId) {
        return repo.findByOrderId(orderId);
    }
}

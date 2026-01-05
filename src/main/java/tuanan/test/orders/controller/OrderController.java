package tuanan.test.orders.controller;

import org.springframework.web.bind.annotation.*;
import tuanan.test.orders.dto.OrderRequest;
import tuanan.test.orders.dto.OrderResponse;
import tuanan.test.orders.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderRequest body) {
        service.createOrder(body);
    }

    @GetMapping
    public List<OrderResponse> getOrders() {
        return service.getAllOrder();
    }
}

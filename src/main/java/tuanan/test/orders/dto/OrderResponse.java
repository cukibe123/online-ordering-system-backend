package tuanan.test.orders.dto;
import java.util.List;

public record OrderResponse (
        Long customerId,
        String customerName,
        List<OrderItemResponse> orderList
) {
    public record OrderItemResponse(
            Long itemId,
            Integer count
    ) {}
}

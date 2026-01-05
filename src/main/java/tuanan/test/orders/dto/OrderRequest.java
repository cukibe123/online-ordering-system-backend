package tuanan.test.orders.dto;
import java.util.List;

public record OrderRequest (
    Long customerId,
    String customerName,
    List<OrderLine> orderList
) {
    public record OrderLine(
    Long itemId,
    Integer count
    ) {}
}

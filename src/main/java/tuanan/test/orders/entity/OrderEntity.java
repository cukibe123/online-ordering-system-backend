package tuanan.test.orders.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Orders")
public class OrderEntity {

    //This ID shall be updated to user's id session to make it more maintainable
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;
    private String customerName;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    public void setId(Long id) {
        this.orderId = id;
    }

    public Long getId() {
        return this.orderId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void addItems(OrderItemEntity item) {
        orderItems.add(item);
        item.setOrder(this);
    }

    public List<OrderItemEntity> getItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }
}
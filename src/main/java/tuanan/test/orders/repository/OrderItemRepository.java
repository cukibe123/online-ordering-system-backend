package tuanan.test.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tuanan.test.orders.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrderId(Long orderId);
}

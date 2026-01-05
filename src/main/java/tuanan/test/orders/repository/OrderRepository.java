package tuanan.test.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tuanan.test.orders.entity.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerId (Long customerId);
}

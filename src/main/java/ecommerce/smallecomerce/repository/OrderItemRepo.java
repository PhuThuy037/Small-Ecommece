package ecommerce.smallecomerce.repository;

import ecommerce.smallecomerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}

package ecommerce.smallecomerce.repository;

import ecommerce.smallecomerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}

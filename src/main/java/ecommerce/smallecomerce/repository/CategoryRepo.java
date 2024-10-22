package ecommerce.smallecomerce.repository;

import ecommerce.smallecomerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}

package ecommerce.smallecomerce.repository;

import ecommerce.smallecomerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}

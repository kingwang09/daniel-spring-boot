package net.freehongs.daniel.domain.shop.repository;

import net.freehongs.daniel.domain.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

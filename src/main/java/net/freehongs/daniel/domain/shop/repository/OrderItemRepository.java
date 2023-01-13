package net.freehongs.daniel.domain.shop.repository;

import net.freehongs.daniel.domain.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

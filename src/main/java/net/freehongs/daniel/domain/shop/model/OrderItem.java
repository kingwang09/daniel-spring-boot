package net.freehongs.daniel.domain.shop.model;

import lombok.*;
import net.freehongs.daniel.domain.shop.constant.OrderItemStatus;
import net.freehongs.daniel.domain.shop.dto.OrderItemResponseDto;
import net.freehongs.daniel.domain.shop.dto.OrderResponseDto;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ShopOrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private Long orderCount;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;


    public OrderItemResponseDto toDto(){
        return OrderItemResponseDto.builder()
                .itemName(this.item.getName())
                .orderCount(this.orderCount)
                .orderPrice(this.item.getPrice() * this.orderCount)
                .orderItemStatus(this.status)
                .build();
    }
}

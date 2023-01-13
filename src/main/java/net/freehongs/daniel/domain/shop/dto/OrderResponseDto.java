package net.freehongs.daniel.domain.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.freehongs.daniel.domain.shop.constant.OrderStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;

    private OrderStatus orderStatus;

    private Long totalPrice;

    private List<OrderItemResponseDto> orderItems;
}

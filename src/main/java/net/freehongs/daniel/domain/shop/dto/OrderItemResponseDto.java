package net.freehongs.daniel.domain.shop.dto;

import lombok.*;
import net.freehongs.daniel.domain.shop.constant.OrderItemStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private String itemName;

    private Long orderCount;

    private Long orderPrice;

    private OrderItemStatus orderItemStatus;
}

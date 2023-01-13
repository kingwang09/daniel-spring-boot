package net.freehongs.daniel.domain.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.freehongs.daniel.domain.shop.constant.Category;
import net.freehongs.daniel.domain.shop.constant.ItemStatus;
import net.freehongs.daniel.domain.shop.model.Item;

/**
 * TODO
 *  - validation추가 필요
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;

    private String name;

    private Long price;

    private Long stockCount;

    private Category category;

    public Item toEntity(){ //objectMapper로 대체할 수는 없을까?
        return Item.builder()
                .id(this.id)
                .category(this.category)
                .price(this.price)
                .status(ItemStatus.READY)
                .stockCount(stockCount)
                .build();
    }
}

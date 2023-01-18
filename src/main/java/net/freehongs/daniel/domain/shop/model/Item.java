package net.freehongs.daniel.domain.shop.model;

import lombok.*;
import net.freehongs.daniel.domain.shop.constant.Category;
import net.freehongs.daniel.domain.shop.constant.ItemStatus;
import net.freehongs.daniel.domain.shop.dto.ItemDto;
import net.freehongs.daniel.support.jpa.AbstractDateAuditingEntity;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="ShopItem")
public class Item extends AbstractDateAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stockCount;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Enumerated(EnumType.STRING)
    private Category category;

    public boolean decrementStockCount(int count){
        if(0 < this.stockCount) {
            this.stockCount -= count;
            return true;
        }
        return false;
    }

    public boolean incrementStockCount(int count){
        this.stockCount += count;
        return true;
    }

    public ItemDto toDto(){
        return ItemDto.builder()
                .id(this.id)
                .name(this.name)
                .stockCount(this.stockCount)
                .build();
    }
}

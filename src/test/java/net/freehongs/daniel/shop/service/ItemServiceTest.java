package net.freehongs.daniel.shop.service;

import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.shop.constant.Category;
import net.freehongs.daniel.domain.shop.dto.ItemDto;
import net.freehongs.daniel.domain.shop.service.ItemService;
import net.freehongs.daniel.support.LoadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ItemServiceTest extends LoadContext {
    @Autowired
    private ItemService itemService;

    @Test
    public void 아이템추가(){
        ItemDto item = itemService.add(ItemDto.builder()
                .category(Category.BEAUTY)
                .name("세수한번 클렌징")
                .stockCount(100L)
                .price(13000L)
                .build());
        Assertions.assertNotNull(item.getId());
    }
}

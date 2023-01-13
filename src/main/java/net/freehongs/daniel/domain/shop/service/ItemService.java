package net.freehongs.daniel.domain.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.shop.dto.ItemDto;
import net.freehongs.daniel.domain.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemDto add(ItemDto itemDto){
        return itemRepository.save(itemDto.toEntity()).toDto();
    }
}

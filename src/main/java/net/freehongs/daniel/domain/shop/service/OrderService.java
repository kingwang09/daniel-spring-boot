package net.freehongs.daniel.domain.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.model.Account;
import net.freehongs.daniel.domain.account.repository.AccountRepository;
import net.freehongs.daniel.domain.shop.constant.ItemStatus;
import net.freehongs.daniel.domain.shop.constant.OrderStatus;
import net.freehongs.daniel.domain.shop.dto.OrderItemRequestDto;
import net.freehongs.daniel.domain.shop.dto.OrderRequestDto;
import net.freehongs.daniel.domain.shop.dto.OrderResponseDto;
import net.freehongs.daniel.domain.shop.model.Item;
import net.freehongs.daniel.domain.shop.model.Order;
import net.freehongs.daniel.domain.shop.model.OrderItem;
import net.freehongs.daniel.domain.shop.repository.ItemRepository;
import net.freehongs.daniel.domain.shop.repository.OrderItemRepository;
import net.freehongs.daniel.domain.shop.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final AccountRepository accountRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public ResponseEntity add(OrderRequestDto orderRequestDto, Long accountId){
        log.debug("order add: accountId={}, order={}", accountId, orderRequestDto);
        Set<Long> itemIds = orderRequestDto.getOrderRequests().stream().map(OrderItemRequestDto::getItemId).collect(Collectors.toSet());
        Set<Item> itemList = itemRepository.findItemsByIdList(itemIds, ItemStatus.ON_SALE);
        Account account = accountRepository.findById(accountId).orElseThrow(NoSuchElementException::new);

        if(itemList.stream().anyMatch((v) -> v.getStockCount() <= 0)){
            log.warn("재고가 없는 상품이 존재합니다."); //client측에도 에러 코드 알려줘야할 듯.
            return ResponseEntity.badRequest().body("재고가 없는 상품이 존재합니다.");
        }

        Order newOrder = Order.builder()
                .account(account)
                .status(OrderStatus.ORDER_COMPLETE)
                .build();


        List<OrderItem> newOrderItems = new LinkedList<>();
        Map<Long, Item> itemMap = itemList.stream().collect(Collectors.toMap(Item::getId, Function.identity()));
        List<OrderItemRequestDto> orderItemRequestDtoList = orderRequestDto.getOrderRequests();
        for(OrderItemRequestDto orderItemRequestDto : orderItemRequestDtoList){
            Item item = itemMap.get(orderItemRequestDto.getItemId());
            Long orderCount = orderItemRequestDto.getOrderCount();
            newOrderItems.add(OrderItem.builder()
                    .order(newOrder)
                    .item(item)
                    .orderCount(orderCount)
                    .build());
        }
        newOrder.addOrderItems(newOrderItems);

        orderRepository.save(newOrder);
        orderItemRepository.saveAll(newOrderItems);

        return ResponseEntity.ok(
            OrderResponseDto.builder()
                .orderId(newOrder.getId())
                .orderStatus(newOrder.getStatus())
                .totalPrice(newOrder.getTotalPrice())
                .orderItems(newOrderItems.stream().map(v -> v.toDto()).collect(Collectors.toList()))
            .build()
        );
    }
}

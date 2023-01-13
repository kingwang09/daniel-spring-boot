package net.freehongs.daniel.shop.service;

import lombok.extern.slf4j.Slf4j;
import net.freehongs.daniel.domain.account.dto.AccountDto;
import net.freehongs.daniel.domain.account.model.Address;
import net.freehongs.daniel.domain.account.service.AccountService;
import net.freehongs.daniel.domain.shop.constant.Category;
import net.freehongs.daniel.domain.shop.constant.ItemStatus;
import net.freehongs.daniel.domain.shop.dto.OrderItemRequestDto;
import net.freehongs.daniel.domain.shop.dto.OrderRequestDto;
import net.freehongs.daniel.domain.shop.dto.OrderResponseDto;
import net.freehongs.daniel.domain.shop.model.Item;
import net.freehongs.daniel.domain.shop.repository.ItemRepository;
import net.freehongs.daniel.domain.shop.service.ItemService;
import net.freehongs.daniel.domain.shop.service.OrderService;
import net.freehongs.daniel.support.LoadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class OrderServiceTest extends LoadContext {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    private AccountDto seller = AccountDto.builder()
            .email("kingwang09@gmail.com")
            .address(Address.builder()
                    .zipCode("12345")
                    .address("경기도 성남시")
                    .addressDetail("어딘가")
                    .build())
            .password("12345")
            .build();

    private AccountDto buyer = AccountDto.builder()
            .email("buy@gmail.com")
            .address(Address.builder()
                    .zipCode("5555")
                    .address("경기도 분당구")
                    .addressDetail("비싼 동네")
                    .build())
            .password("7890")
            .build();

    private Item onSaleItem1 = Item.builder()
            .category(Category.BEAUTY)
            .name("세수 한번")
            .price(13000L)
            .status(ItemStatus.ON_SALE)
            .stockCount(10L)
            .build();
    private Item onSaleItem2 = Item.builder()
            .category(Category.BABY)
            .name("베이비 체어")
            .price(30000L)
            .status(ItemStatus.ON_SALE)
            .stockCount(5L)
            .build();
    private Item onSaleItem3 = Item.builder()
            .category(Category.LIVING)
            .name("세라믹 식탁 세트")
            .price(430000L)
            .status(ItemStatus.ON_SALE)
            .stockCount(5L)
            .build();
    private Item noStockCountItem1 = Item.builder()
            .category(Category.CLASS)
            .name("상위 5% 백엔드 개발자 강의")
            .price(500000L)
            .status(ItemStatus.ON_SALE)
            .stockCount(0L)
            .build();

    @BeforeEach
    public void 초기설정(){
        //회원 등록
        Long sellerId = accountService.add(seller).getId();
        seller.setId(sellerId);
        Long buyerId = accountService.add(buyer).getId();
        buyer.setId(buyerId);

        //상품 등록
        itemRepository.saveAll(Arrays.asList(onSaleItem1, onSaleItem2, onSaleItem3, noStockCountItem1));
    }

    @Test
    public void 정상_주문(){
        log.debug("주문");
        List<OrderItemRequestDto> orderItemRequestDtoList = Arrays.asList(
              OrderItemRequestDto.builder()
                      .itemId(onSaleItem1.getId())
                      .orderCount(10L)
                      .build(),
                OrderItemRequestDto.builder()
                        .itemId(onSaleItem2.getId())
                        .orderCount(5L)
                        .build(),
                OrderItemRequestDto.builder()
                        .itemId(onSaleItem3.getId())
                        .orderCount(1L)
                        .build()
        );

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .orderRequests(orderItemRequestDtoList)
                .build();


        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderService.add(orderRequestDto, buyer.getId());
        log.debug("result: {}", responseDtoResponseEntity.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseDtoResponseEntity.getStatusCode());
    }

    @Test
    public void 재고없는_주문(){
        log.debug("재고 없는 주문");
        List<OrderItemRequestDto> orderItemRequestDtoList = Arrays.asList(
                OrderItemRequestDto.builder()
                        .itemId(onSaleItem1.getId())
                        .orderCount(10L)
                        .build(),
                OrderItemRequestDto.builder()
                        .itemId(onSaleItem2.getId())
                        .orderCount(5L)
                        .build(),
                OrderItemRequestDto.builder()
                        .itemId(onSaleItem3.getId())
                        .orderCount(1L)
                        .build(),
                OrderItemRequestDto.builder()
                        .itemId(noStockCountItem1.getId())
                        .orderCount(1L)
                        .build()
        );

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .orderRequests(orderItemRequestDtoList)
                .build();


        ResponseEntity<OrderResponseDto> responseDtoResponseEntity = orderService.add(orderRequestDto, buyer.getId());
        log.debug("result: {}", responseDtoResponseEntity.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseDtoResponseEntity.getStatusCode());
    }
}

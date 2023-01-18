package net.freehongs.daniel.domain.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Order - Delivery랑 ManyToOne이 되어야하지 않는가?
 *  - 분리 배송이 되는 경우도 있으니? (물론 대부분 한번에 배송일텐데 구조를 좀더 고민 필요)
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //주문: Order

    //배송지 주소

    //배송 상태(배송 전, 배송 진행 중, 배송 완료)
    //배송 완료일
    //택배 회사
    //송장 번호
}

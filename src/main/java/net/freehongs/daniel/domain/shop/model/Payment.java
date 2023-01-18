package net.freehongs.daniel.domain.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//상위 객체로 만들고
//하위로 카드결제, 계좌이체, 핸드폰 결제
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //주문

    //결제 상태 (결제 완료, 결제 실패, 결제 취소)

    //결제 완료일

    //결제 취소일
}

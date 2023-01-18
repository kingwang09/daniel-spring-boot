package net.freehongs.daniel.domain.shop.model;

import lombok.*;
import net.freehongs.daniel.domain.account.model.Account;
import net.freehongs.daniel.domain.shop.constant.OrderStatus;
import net.freehongs.daniel.support.jpa.AbstractDateAuditingEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ShopOrder")
public class Order extends AbstractDateAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;


    public Long getTotalPrice(){
        Long totalPrice = 0L;
        for(OrderItem orderItem: orderItems){
            totalPrice += orderItem.getItem().getPrice() * orderItem.getOrderCount();
        }
        return totalPrice;
    }

    public void addOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }
}

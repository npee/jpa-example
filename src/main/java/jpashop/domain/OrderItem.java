package jpashop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID") // Order와의 관계에서 OrderItem이 N이며 연관관계의 주인으로 FK를 관리하면 된다.
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;

    // 양방향 연관관계를 위한 setter
    public void setOrder(Order order) {
        this.order = order;
    }
}

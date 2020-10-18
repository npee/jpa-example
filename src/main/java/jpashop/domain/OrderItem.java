package jpashop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "count")
    private int count;
}

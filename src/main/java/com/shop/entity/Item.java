package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name = "itme_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}

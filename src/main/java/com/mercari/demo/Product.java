package com.mercari.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // 추가


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer price;
    private Integer stock;

    // ★ 핵심: 여러 상품은 한 명의 주인에게 속한다 (N:1)
    @ManyToOne
    @JoinColumn(name = "seller_id") // DB에는 "seller_id"라는 칸에 주인의 숫자(id)가 적힌다.
    private User seller;
}
package com.mercari.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "users") // "user"는 DB 예약어랑 겹칠 수 있어서 "users"로 바꿈
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username; // 홍길동
    private String email;    // hong@test.com

    // ★ 핵심: 한 명의 회원은 여러 개의 상품을 판다 (1:N)
    // mappedBy = "seller" -> Product 쪽에 있는 'seller' 변수가 주인이다. (난 거들뿐)
    @OneToMany(mappedBy = "seller")
    private List<Product> sellingProducts = new ArrayList<>();
}
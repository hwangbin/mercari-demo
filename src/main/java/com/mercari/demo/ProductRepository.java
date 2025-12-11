package com.mercari.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 1. 상품명 검색 (LIKE %name%)
    // findBy(찾아라) + Name(이름으로) + Containing(포함된거)
    List<Product> findByNameContaining(String keyword);

    // 2. 가격 범위 검색 (BETWEEN)
    // findBy(찾아라) + Price(가격을) + Between(사이인거)
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

    // 3. 특정 판매자의 상품만 조회 (WHERE seller_id = ?)
    List<Product> findBySellerId(Integer sellerId);
}

package com.mercari.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 내용은 비어있어도 됨. 껍데기만 있으면 됨.
}
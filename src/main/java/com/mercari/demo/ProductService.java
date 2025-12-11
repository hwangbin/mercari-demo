package com.mercari.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // "나 매니저야!" 명찰
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 1. 조회 업무
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. 등록 업무 (★중요: 검증 로직 추가)
    public Product createProduct(Product product) {
        // 검증: 가격이 마이너스면 안 돼!
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다."); // 에러 발생!
        }
        return productRepository.save(product);
    }

    // 3. 삭제 업무 (★중요: 없으면 에러)
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 상품이 없는데요? (ID: " + id + ")");
        }
        productRepository.deleteById(id);
    }

    // 서비스에 추가
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    public List<Product> filterProductsByPrice(Integer min, Integer max) {
        return productRepository.findByPriceBetween(min, max);
    }
}
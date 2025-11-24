package com.mercari.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable; // 추가!
import org.springframework.web.bind.annotation.PutMapping;    // 추가!
import org.springframework.web.bind.annotation.PostMapping; // 이거 임포트!
import org.springframework.web.bind.annotation.RequestBody; // 이것도!
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @Autowired
    private UserRepository userRepository; // 매니저 소환

    @Autowired
    private ProductService productService; // Repository 대신 Service를 부른다!

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts(); // 매니저, 목록 가져와.
    }

    // @RequestParam: 주소 뒤에 "?이름=값" 형태로 들어오는 걸 받는다.
    @PostMapping("/products")
    public Product createProduct(
            @RequestBody Product product,
            @RequestParam Integer sellerId // 예: /products?sellerId=1
    ) {

        // 1. 판매자(User) 찾기
        User seller = userRepository.findById(sellerId).orElseThrow();

        // 2. 상품에 "주인님 여기에요!" 하고 명찰 붙이기 (이게 진짜 연결)
        product.setSeller(seller);

        // 3. Service에게 검사맡고 저장! (혹은 Repository 직접 저장)
        return productService.createProduct(product);
    }
    // DB에 저장(save)하고, 저장된 놈을 다시 반환해라


    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id); // 매니저한테 시킴
        return "삭제 완료: " + id;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}





package com.mercari.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 역시 껍데기만 있으면 됨. Jpa가 다 해줌.
}
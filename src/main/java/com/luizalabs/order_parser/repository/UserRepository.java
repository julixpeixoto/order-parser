package com.luizalabs.order_parser.repository;

import com.luizalabs.order_parser.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}

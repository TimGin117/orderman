package com.mydemo.orderman.repository;

import com.mydemo.orderman.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Optional<Seller> findById(Integer id);
}

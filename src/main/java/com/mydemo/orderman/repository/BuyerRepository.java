package com.mydemo.orderman.repository;

import com.mydemo.orderman.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer,Integer> {

    Optional<Buyer> findById(Integer id);
}

package com.mydemo.orderman.service;

import com.mydemo.orderman.form.SellerInfoForm;
import com.mydemo.orderman.model.Seller;
import com.mydemo.orderman.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller findOne(Integer id){
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller addSeller(SellerInfoForm sellerInfoForm){
        Seller seller=new Seller();
        seller.setShopName(sellerInfoForm.getShopName());

        return sellerRepository.save(seller);
    }
}

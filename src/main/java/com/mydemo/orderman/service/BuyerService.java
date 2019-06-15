package com.mydemo.orderman.service;

import com.mydemo.orderman.form.BuyerInfoForm;
import com.mydemo.orderman.model.Buyer;
import com.mydemo.orderman.repository.BuyerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer findOne(Integer id){
        Optional<Buyer> buyer = buyerRepository.findById(id);
        return buyer.orElse(null);
    }

    public Buyer addBuyer(BuyerInfoForm buyerInfoForm){
        Buyer buyer=new Buyer();
        buyer.setName(buyerInfoForm.getName());
        buyer.setPhoneNumber(buyerInfoForm.getPhoneNumber());
        buyer.setAddress(buyerInfoForm.getAddress());

        return buyerRepository.save(buyer);
    }
}

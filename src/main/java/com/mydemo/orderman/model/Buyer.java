package com.mydemo.orderman.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@DynamicUpdate
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //收货人姓名
    @NotNull
    private String name;

    //收货人联系电话
    @NotNull
    private String phoneNumber;

    //收货地址
    @NotNull
    private String address;

}

package com.mydemo.orderman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "make_order")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "stocks_id")
    private Stocks stocks;

    @NotNull
    private Integer status;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}

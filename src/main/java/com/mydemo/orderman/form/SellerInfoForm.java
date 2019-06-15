package com.mydemo.orderman.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SellerInfoForm {

    @NotBlank
    private String shopName;

}

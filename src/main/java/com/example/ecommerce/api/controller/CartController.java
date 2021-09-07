package com.example.ecommerce.api.controller;

import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.dto.CartDTO;
import com.example.ecommerce.service.dto.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/createCart")
    public String createNewCart(@RequestHeader String customerEmail) {
        return cartService.createNewCart(customerEmail);
    }

    @PostMapping("/addProductToCart")
    public String addProductToCart(@RequestBody OrderInfoDTO orderInfoDTO){
        return cartService.addProductToCart(orderInfoDTO);
    }

    @GetMapping("/viewCart")
    public CartDTO viewCart(@RequestHeader String customerEmail) {
        return cartService.viewCartForCustomer(customerEmail);
    }

    @DeleteMapping("/emptyCart")
    public String emptyCart(@RequestHeader String customerEmail) {
        return cartService.emptyCartForCustomer(customerEmail);
    }

}
package org.example.ie.controller.before;

import org.example.ie.common.http.ResponseResult;
import org.example.ie.common.sercurity.anno.AuthIgrone;
import org.example.ie.entity.CartEntity;
import org.example.ie.service.admin.GoodsService;
import org.example.ie.service.before.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/before/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;

    @AuthIgrone
    @PostMapping("/myCart")
    public ResponseResult<List<Map<String, Object>>> myCartGoods(@RequestBody CartEntity cartEntity){
        return goodsService.myCartGoods(cartEntity);
    }

    @AuthIgrone
    @PostMapping("/add")
    public ResponseResult<Map<String, Object>> addCart(@RequestBody CartEntity cartEntity){
        return cartService.addCart(cartEntity);
    }

    @AuthIgrone
    @PostMapping("/bupDateCart")
    public ResponseResult<Map<String, Object>> bupDateCart(@RequestBody CartEntity cartEntity){
        return cartService.bupDateCart(cartEntity);
    }

    @PostMapping("/clearCart")
    public ResponseResult<Map<String, Object>> clearCart(@RequestBody CartEntity cartEntity){
        return cartService.clearCart(cartEntity);
    }

    @AuthIgrone
    @PostMapping("/removeCart")
    public ResponseResult<Map<String, Object>> removeCart(@RequestBody CartEntity cartEntity){
        return cartService.removeCart(cartEntity);
    }
}

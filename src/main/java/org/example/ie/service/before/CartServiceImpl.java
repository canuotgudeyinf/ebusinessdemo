package org.example.ie.service.before;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ie.common.http.ResponseResult;
import org.example.ie.entity.CartEntity;
import org.example.ie.mapper.before.CartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartEntity> implements CartService {

    @Override
    public ResponseResult<Map<String, Object>> addCart(CartEntity cartEntity) {
        long n = query()
                .eq("busertable_id",cartEntity.getBusertableId())
                .eq("goodstable_id",cartEntity.getGoodstableId())
                .count();
        boolean b;
        if(n > 0){
            UpdateWrapper<CartEntity> updateWrapper =  new UpdateWrapper<>();
            updateWrapper.setSql("shoppingnum = shoppingnum +" + cartEntity.getShoppingnum());
            updateWrapper.last("where busertable_id = " + cartEntity.getBusertableId() +
                    " and goodstable_id = " + cartEntity.getGoodstableId());
            b = this.update(updateWrapper);
        }
        else{
            b = this.save(cartEntity);
        }
        if(b)
            return ResponseResult.getMessageResult(null,"A001");
        else return ResponseResult.getMessageResult(null,"A002");
    }

    @Transactional
    @Override
    public ResponseResult<Map<String, Object>> bupDateCart(CartEntity cartEntity) {
        List<Integer> bcid = cartEntity.getBcid();
        List<Integer> bshoppingnum = cartEntity.getBshoppingnum();
        ArrayList<CartEntity> bCarts = new ArrayList<>();
        for (int i = 0; i < bcid.size(); i++) {
            CartEntity cart = new CartEntity();
            cart.setId(bcid.get(i));
            cart.setShoppingnum(bshoppingnum.get(i));
            bCarts.add(cart);
        }
        this.updateBatchById(bCarts);
        return ResponseResult.getMessageResult(null,"A001");
    }
}

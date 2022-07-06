package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class CartProvider {

    private final CartDao cartDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartProvider(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public List<GetCartsRes> getCarts(int userIdx) throws BaseException{
        try{
            List<GetCartsRes> getCartsRes = cartDao.getCarts(userIdx);
            return getCartsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetcartRes> getcart(int cartIdx) throws BaseException{
//        if (checkcartIdx(cartIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_cart);
//        }
//        try{
//            List<GetcartRes> getcartRes = cartDao.getcart(cartIdx);
//            return getcartRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkcartIdx(int cartIdx) throws BaseException{
//        try{
//            return cartDao.checkcartIdx(cartIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

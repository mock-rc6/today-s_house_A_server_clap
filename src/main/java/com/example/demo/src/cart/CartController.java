package com.example.demo.src.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/carts")

public class CartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CartProvider cartProvider;
    @Autowired
    private final CartService cartService;

    @Autowired
    private final JwtService jwtService;

    public CartController(CartProvider cartProvider, CartService cartService, JwtService jwtService) {
        this.cartProvider = cartProvider;
        this.cartService = cartService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /carts
     * @return BaseResponse<List<GetcartsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("{userIdx}") // (GET) 127.0.0.1:9000/app/carts
    public BaseResponse<List<GetCartsRes>> getCarts(@PathVariable("userIdx") int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetCartsRes> getCartsRes = cartProvider.getCarts(userIdx);
            return new BaseResponse<>(getCartsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /carts/:cartIdx
//     * @return BaseResponse<GetcartRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{cartIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetcartRes>> getcart(@PathVariable("userIdx") int userIdx, @PathVariable("cartIdx") int cartIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetcartRes> getcartRes = cartProvider.getcart(cartIdx);
//            return new BaseResponse<>(getcartRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

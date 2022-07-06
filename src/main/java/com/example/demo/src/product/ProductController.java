package com.example.demo.src.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/products")

public class ProductController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProductProvider productProvider;
    @Autowired
    private final ProductService productService;

    @Autowired
    private final JwtService jwtService;

    public ProductController(ProductProvider productProvider, ProductService productService, JwtService jwtService) {
        this.productProvider = productProvider;
        this.productService = productService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /products
     * @return BaseResponse<List<GetproductsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("{productIdx}") // (GET) 127.0.0.1:9000/app/products
    public BaseResponse<List<GetProductRes>> getProduct(@PathVariable("productIdx") int productIdx) {
        try{
            List<GetProductRes> getProductRes = productProvider.getProduct(productIdx);
            return new BaseResponse<>(getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/products
    public BaseResponse<List<GetProductsRes>> getProducts() {
        try{
            List<GetProductsRes> getProducstRes = productProvider.getProducts();
            return new BaseResponse<>(getProducstRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


//    /**
//     * 특정크리에이터조회 API
//     * [GET] /products/:productIdx
//     * @return BaseResponse<GetproductRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{productIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetproductRes>> getproduct(@PathVariable("userIdx") int userIdx, @PathVariable("productIdx") int productIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetproductRes> getproductRes = productProvider.getproduct(productIdx);
//            return new BaseResponse<>(getproductRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

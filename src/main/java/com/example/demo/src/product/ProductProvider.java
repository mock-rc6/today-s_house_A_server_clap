package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class ProductProvider {

    private final ProductDao productDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductProvider(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<GetProductRes> getProduct(int productIdx) throws BaseException{
        try{
            List<GetProductRes> getProductRes = productDao.getProduct(productIdx);
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductsRes> getProducts() throws BaseException{
        try{
            List<GetProductsRes> getProductsRes = productDao.getProducts();
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetproductRes> getproduct(int productIdx) throws BaseException{
//        if (checkproductIdx(productIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_product);
//        }
//        try{
//            List<GetproductRes> getproductRes = productDao.getproduct(productIdx);
//            return getproductRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkproductIdx(int productIdx) throws BaseException{
//        try{
//            return productDao.checkproductIdx(productIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service

public class ProductService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final ProductProvider productProvider;


    @Autowired
    public ProductService(ProductDao productDao, ProductProvider productProvider) {
        this.productDao = productDao;
        this.productProvider = productProvider;
    }

}

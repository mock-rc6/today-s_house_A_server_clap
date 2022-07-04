package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class CategoryProvider {

    private final CategoryDao categoryDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CategoryProvider(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<GetCategoriesRes> getCategories() throws BaseException{
        try{
            List<GetCategoriesRes> getCategoriesRes = categoryDao.getCategories();
            return getCategoriesRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetcategoryRes> getcategory(int categoryIdx) throws BaseException{
//        if (checkcategoryIdx(categoryIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_category);
//        }
//        try{
//            List<GetcategoryRes> getcategoryRes = categoryDao.getcategory(categoryIdx);
//            return getcategoryRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkcategoryIdx(int categoryIdx) throws BaseException{
//        try{
//            return categoryDao.checkcategoryIdx(categoryIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

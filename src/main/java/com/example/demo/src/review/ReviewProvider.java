package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class ReviewProvider {

    private final ReviewDao reviewDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<GetReviewsRes> getReviews(int productIdx) throws BaseException{
        try{
            List<GetReviewsRes> getReviewsRes = reviewDao.getReviews(productIdx);
            return getReviewsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkProductIdx(int productIdx) throws BaseException{
        try{
            return reviewDao.checkProductIdx(productIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetreviewRes> getreview(int reviewIdx) throws BaseException{
//        if (checkreviewIdx(reviewIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_review);
//        }
//        try{
//            List<GetreviewRes> getreviewRes = reviewDao.getreview(reviewIdx);
//            return getreviewRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkreviewIdx(int reviewIdx) throws BaseException{
//        try{
//            return reviewDao.checkreviewIdx(reviewIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

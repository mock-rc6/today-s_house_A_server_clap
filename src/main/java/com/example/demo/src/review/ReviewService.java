package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
@Transactional
public class ReviewService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;


    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
    }

    @Transactional
    public void createReview(PostReviewReq postReviewReq) throws BaseException {
        if(reviewProvider.checkProductIdx(postReviewReq.getProductIdx()) == 0){
            throw new BaseException(POST_Reviews_UNEXIST_PRODUCT);
        }
        try{
            int result = reviewDao.createReview(postReviewReq);
            if(result == 0){
                throw new BaseException(POST_FAIL_REVIEW);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}

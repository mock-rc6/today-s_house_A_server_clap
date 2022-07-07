package com.example.demo.src.review;

import com.example.demo.src.knowhow.model.GetKnowhowsRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/reviews")

public class ReviewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final JwtService jwtService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /reviews
     * @return BaseResponse<List<GetreviewsRes>>
     */
    //Query String
    @ResponseBody
    @PostMapping ("/{userIdx}/{productIdx}") // (GET) 127.0.0.1:9000/app/reviews
    public BaseResponse<String> createReview(@PathVariable("userIdx") int userIdx, @PathVariable("productIdx") int productIdx, @RequestBody Review review) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostReviewReq postReviewReq = new PostReviewReq(userIdx, productIdx, review.getContent(), review.getRating());
            reviewService.createReview(postReviewReq);
            String result = "";
            return new BaseResponse<>(result);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("{productIdx}") // (GET) 127.0.0.1:9000/app/knowhows
    public BaseResponse<List<GetReviewsRes>> getReviews(@PathVariable("productIdx") int productIdx) {
        try{
            List<GetReviewsRes> getReviewsRes = reviewProvider.getReviews(productIdx);
            return new BaseResponse<>(getReviewsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /reviews/:reviewIdx
//     * @return BaseResponse<GetreviewRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{reviewIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetreviewRes>> getreview(@PathVariable("userIdx") int userIdx, @PathVariable("reviewIdx") int reviewIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetreviewRes> getreviewRes = reviewProvider.getreview(reviewIdx);
//            return new BaseResponse<>(getreviewRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

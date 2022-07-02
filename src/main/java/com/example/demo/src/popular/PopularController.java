package com.example.demo.src.popular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.popular.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/populars")

public class PopularController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PopularProvider popularProvider;
    @Autowired
    private final PopularService popularService;

    @Autowired
    private final JwtService jwtService;

    public PopularController(PopularProvider popularProvider, PopularService popularService, JwtService jwtService) {
        this.popularProvider = popularProvider;
        this.popularService = popularService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /populars
     * @return BaseResponse<List<GetpopularsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/populars
    public BaseResponse<List<GetPopularsRes>> getPopulars() {
        try{
            List<GetPopularsRes> getPopularRes = popularProvider.getPopulars();
            return new BaseResponse<>(getPopularRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /populars/:popularIdx
//     * @return BaseResponse<GetpopularRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{popularIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetpopularRes>> getpopular(@PathVariable("userIdx") int userIdx, @PathVariable("popularIdx") int popularIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetpopularRes> getpopularRes = popularProvider.getpopular(popularIdx);
//            return new BaseResponse<>(getpopularRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

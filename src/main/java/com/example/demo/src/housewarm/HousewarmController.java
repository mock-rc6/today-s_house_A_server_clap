package com.example.demo.src.housewarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.housewarm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/housewarms")

public class HousewarmController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final HousewarmProvider housewarmProvider;
    @Autowired
    private final HousewarmService housewarmService;

    @Autowired
    private final JwtService jwtService;

    public HousewarmController(HousewarmProvider housewarmProvider, HousewarmService housewarmService, JwtService jwtService) {
        this.housewarmProvider = housewarmProvider;
        this.housewarmService = housewarmService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /housewarms
     * @return BaseResponse<List<GethousewarmsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/housewarms
    public BaseResponse<List<GetHousewarmsRes>> getHousewarms() {
        try{
            List<GetHousewarmsRes> getHousewarmRes = housewarmProvider.getHousewarms();
            return new BaseResponse<>(getHousewarmRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /housewarms/:housewarmIdx
//     * @return BaseResponse<GethousewarmRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{housewarmIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GethousewarmRes>> gethousewarm(@PathVariable("userIdx") int userIdx, @PathVariable("housewarmIdx") int housewarmIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GethousewarmRes> gethousewarmRes = housewarmProvider.gethousewarm(housewarmIdx);
//            return new BaseResponse<>(gethousewarmRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

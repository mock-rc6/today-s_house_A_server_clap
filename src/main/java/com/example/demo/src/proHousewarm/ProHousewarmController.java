package com.example.demo.src.proHousewarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.proHousewarm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/proHousewarms")

public class ProHousewarmController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProHousewarmProvider proHousewarmProvider;
    @Autowired
    private final ProHousewarmService proHousewarmService;

    @Autowired
    private final JwtService jwtService;

    public ProHousewarmController(ProHousewarmProvider proHousewarmProvider, ProHousewarmService proHousewarmService, JwtService jwtService) {
        this.proHousewarmProvider = proHousewarmProvider;
        this.proHousewarmService = proHousewarmService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /proHousewarms
     * @return BaseResponse<List<GetproHousewarmsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/proHousewarms
    public BaseResponse<List<GetProHousewarmsRes>> getProHousewarms() {
        try{
            List<GetProHousewarmsRes> getProHousewarmsRes = proHousewarmProvider.getProHousewarms();
            return new BaseResponse<>(getProHousewarmsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /proHousewarms/:proHousewarmIdx
//     * @return BaseResponse<GetproHousewarmRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{proHousewarmIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetproHousewarmRes>> getproHousewarm(@PathVariable("userIdx") int userIdx, @PathVariable("proHousewarmIdx") int proHousewarmIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetproHousewarmRes> getproHousewarmRes = proHousewarmProvider.getproHousewarm(proHousewarmIdx);
//            return new BaseResponse<>(getproHousewarmRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

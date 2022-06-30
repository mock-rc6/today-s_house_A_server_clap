package com.example.demo.src.knowhow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.knowhow.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/knowhows")

public class KnowhowController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final KnowhowProvider knowhowProvider;
    @Autowired
    private final KnowhowService knowhowService;

    @Autowired
    private final JwtService jwtService;

    public KnowhowController(KnowhowProvider knowhowProvider, KnowhowService knowhowService, JwtService jwtService) {
        this.knowhowProvider = knowhowProvider;
        this.knowhowService = knowhowService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /knowhows
     * @return BaseResponse<List<GetknowhowsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/knowhows
    public BaseResponse<List<GetKnowhowsRes>> getKnowhows() {
        try{
            List<GetKnowhowsRes> getKnowhowRes = knowhowProvider.getKnowhows();
            return new BaseResponse<>(getKnowhowRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /knowhows/:knowhowIdx
//     * @return BaseResponse<GetknowhowRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{knowhowIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetknowhowRes>> getknowhow(@PathVariable("userIdx") int userIdx, @PathVariable("knowhowIdx") int knowhowIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetknowhowRes> getknowhowRes = knowhowProvider.getknowhow(knowhowIdx);
//            return new BaseResponse<>(getknowhowRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

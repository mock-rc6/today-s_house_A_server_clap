package com.example.demo.src.myPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.myPage.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/myPages")

public class MyPageController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final MyPageProvider myPageProvider;
    @Autowired
    private final MyPageService myPageService;

    @Autowired
    private final JwtService jwtService;

    public MyPageController(MyPageProvider myPageProvider, MyPageService myPageService, JwtService jwtService) {
        this.myPageProvider = myPageProvider;
        this.myPageService = myPageService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /myPage
     * @return BaseResponse<List<GetmyPagesRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/app/myPages
    public BaseResponse<GetMyPageRes> getMyPage(@PathVariable("userIdx") int userIdx) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            GetMyPageRes getMyPageRes = myPageProvider.getMyPage(userIdx);
            return new BaseResponse<>(getMyPageRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /myPages/:myPageIdx
//     * @return BaseResponse<GetmyPageRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{myPageIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetmyPageRes>> getmyPage(@PathVariable("userIdx") int userIdx, @PathVariable("myPageIdx") int myPageIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetmyPageRes> getmyPageRes = myPageProvider.getmyPage(myPageIdx);
//            return new BaseResponse<>(getmyPageRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

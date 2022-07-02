package com.example.demo.src.following;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.following.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/followingsEmpty")

public class FollowingController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FollowingProvider followingProvider;
    @Autowired
    private final FollowingService followingService;

    @Autowired
    private final JwtService jwtService;

    public FollowingController(FollowingProvider followingProvider, FollowingService followingService, JwtService jwtService) {
        this.followingProvider = followingProvider;
        this.followingService = followingService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /followings
     * @return BaseResponse<List<GetfollowingsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/followings
    public BaseResponse<List<GetFollowingsRes>> getFollowings() {
        try{
            List<GetFollowingsRes> getFollowingRes = followingProvider.getFollowings();
            return new BaseResponse<>(getFollowingRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /followings/:followingIdx
//     * @return BaseResponse<GetfollowingRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{followingIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetfollowingRes>> getfollowing(@PathVariable("userIdx") int userIdx, @PathVariable("followingIdx") int followingIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetfollowingRes> getfollowingRes = followingProvider.getfollowing(followingIdx);
//            return new BaseResponse<>(getfollowingRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

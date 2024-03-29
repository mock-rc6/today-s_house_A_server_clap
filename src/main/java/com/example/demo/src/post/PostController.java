package com.example.demo.src.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/posts")

public class PostController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PostProvider postProvider;
    @Autowired
    private final PostService postService;

    @Autowired
    private final JwtService jwtService;

    public PostController(PostProvider postProvider, PostService postService, JwtService jwtService) {
        this.postProvider = postProvider;
        this.postService = postService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /posts
     * @return BaseResponse<List<GetpostsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/posts
    public BaseResponse<List<GetPostsRes>> getPosts() {
        try{
            List<GetPostsRes> getPostRes = postProvider.getPosts();
            return new BaseResponse<>(getPostRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /posts/:postIdx
//     * @return BaseResponse<GetpostRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{postIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetpostRes>> getpost(@PathVariable("userIdx") int userIdx, @PathVariable("postIdx") int postIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetpostRes> getpostRes = postProvider.getpost(postIdx);
//            return new BaseResponse<>(getpostRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

package com.example.demo.src.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.comment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/comments")

public class CommentController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CommentProvider commentProvider;
    @Autowired
    private final CommentService commentService;

    @Autowired
    private final JwtService jwtService;


    public CommentController(CommentProvider commentProvider, CommentService commentService, JwtService jwtService) {
        this.commentProvider = commentProvider;
        this.commentService = commentService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /comments
     * @return BaseResponse<List<GetcommentsRes>>
     */
    //Query String
    @ResponseBody
    @PostMapping ("posts/{userIdx}/{postIdx}") // (GET) 127.0.0.1:9000/app/comments
    public BaseResponse<String> createPostsComment(@PathVariable("userIdx") int userIdx, @PathVariable("postIdx") int postIdx, @RequestBody Comment comment) {
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostPostsCommentReq postPostsCommentReqReq = new PostPostsCommentReq(userIdx, postIdx, comment.getContent());
            commentService.createPostsComment(postPostsCommentReqReq);
            String result = "";
            return new BaseResponse<>(result);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/posts/{postIdx}") // (GET) 127.0.0.1:9000/app/knowhows
    public BaseResponse<List<GetPostsCommentsRes>> getPostsComments(@PathVariable("postIdx") int postIdx) {
        try{
            List<GetPostsCommentsRes> getPostsCommentsRes = commentProvider.getPostsComments(postIdx);
            return new BaseResponse<>(getPostsCommentsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /comments/:commentIdx
//     * @return BaseResponse<GetcommentRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{commentIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetcommentRes>> getcomment(@PathVariable("userIdx") int userIdx, @PathVariable("commentIdx") int commentIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetcommentRes> getcommentRes = commentProvider.getcomment(commentIdx);
//            return new BaseResponse<>(getcommentRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

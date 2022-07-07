package com.example.demo.src.comment;

import com.example.demo.config.BaseException;
import com.example.demo.src.comment.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service

public class CommentService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommentDao commentDao;
    private final CommentProvider commentProvider;


    @Autowired
    public CommentService(CommentDao commentDao, CommentProvider commentProvider) {
        this.commentDao = commentDao;
        this.commentProvider = commentProvider;
    }


    public void createPostsComment(PostPostsCommentReq postPostsCommentReq) throws BaseException {
        if(commentProvider.checkPostIdx(postPostsCommentReq.getPostIdx()) == 0){
            throw new BaseException(POST_Comements_UNEXIST_Post);
        }

        try{
            int result = commentDao.createPostsComment(postPostsCommentReq);
            if(result == 0){
                throw new BaseException(POST_FAIL_COMMENT);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}

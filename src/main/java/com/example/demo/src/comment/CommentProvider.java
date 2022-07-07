package com.example.demo.src.comment;

import com.example.demo.config.BaseException;
import com.example.demo.src.comment.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class CommentProvider {

    private final CommentDao commentDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CommentProvider(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<GetPostsCommentsRes> getPostsComments(int postIdx) throws BaseException{
        try{
            List<GetPostsCommentsRes> getPostsCommentsRes = commentDao.getPostsComments(postIdx);
            return getPostsCommentsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkPostIdx(int postIdx) throws BaseException{
        try{
            return commentDao.checkPostIdx(postIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetcommentRes> getcomment(int commentIdx) throws BaseException{
//        if (checkcommentIdx(commentIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_comment);
//        }
//        try{
//            List<GetcommentRes> getcommentRes = commentDao.getcomment(commentIdx);
//            return getcommentRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkcommentIdx(int commentIdx) throws BaseException{
//        try{
//            return commentDao.checkcommentIdx(commentIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

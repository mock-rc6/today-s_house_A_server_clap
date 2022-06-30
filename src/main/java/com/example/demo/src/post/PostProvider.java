package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.src.post.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class PostProvider {

    private final PostDao postDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PostProvider(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<GetPostsRes> getPosts() throws BaseException{
        try{
            List<GetPostsRes> getPostsRes = postDao.getPosts();
            return getPostsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetpostRes> getpost(int postIdx) throws BaseException{
//        if (checkpostIdx(postIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_post);
//        }
//        try{
//            List<GetpostRes> getpostRes = postDao.getpost(postIdx);
//            return getpostRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkpostIdx(int postIdx) throws BaseException{
//        try{
//            return postDao.checkpostIdx(postIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

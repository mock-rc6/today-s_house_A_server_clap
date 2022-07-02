package com.example.demo.src.following;

import com.example.demo.config.BaseException;
import com.example.demo.src.following.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class FollowingProvider {

    private final FollowingDao followingDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FollowingProvider(FollowingDao followingDao) {
        this.followingDao = followingDao;
    }

    public List<GetFollowingsRes> getFollowings() throws BaseException{
        try{
            List<GetFollowingsRes> getFollowingsRes = followingDao.getFollowings();
            return getFollowingsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetfollowingRes> getfollowing(int followingIdx) throws BaseException{
//        if (checkfollowingIdx(followingIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_following);
//        }
//        try{
//            List<GetfollowingRes> getfollowingRes = followingDao.getfollowing(followingIdx);
//            return getfollowingRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkfollowingIdx(int followingIdx) throws BaseException{
//        try{
//            return followingDao.checkfollowingIdx(followingIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

package com.example.demo.src.search;

import com.example.demo.config.BaseException;
import com.example.demo.src.search.model.*;
import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class SearchProvider {

    private final SearchDao searchDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchProvider(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    public List<GetSearchesRes> getSearches() throws BaseException{
        try{
            List<GetSearchesRes> getSearchesRes = searchDao.getSearches();
            return getSearchesRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchPostsRes> getSearchPosts(String searchWord) throws BaseException{
        try{
            List<GetSearchPostsRes> getSearchPostsRes = searchDao.getSearchPosts(searchWord);
            return getSearchPostsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchHousewarmsRes> getSearchHousewarms(String searchWord) throws BaseException{
        try{
            List<GetSearchHousewarmsRes> getSearchHousewarmsRes = searchDao.getSearchHousewarms(searchWord);
            return getSearchHousewarmsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchKnowhowsRes> getSearchKnowhows(String searchWord) throws BaseException{
        try{
            List<GetSearchKnowhowsRes> getSearchKnowhowsRes = searchDao.getSearchKnowhows(searchWord);
            return getSearchKnowhowsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchProductsRes> getSearchProducts(String searchWord) throws BaseException{
        try{
            List<GetSearchProductsRes> getSearchProductsRes = searchDao.getSearchProducts(searchWord);
            return getSearchProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchQuestionAnswersRes> getSearchQuestionAnswers(String searchWord) throws BaseException{
        try{
            List<GetSearchQuestionAnswersRes> getSearchQuestionAnswersRes = searchDao.getSearchQuestionAnswers(searchWord);
            return getSearchQuestionAnswersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetSearchAllRes> getSearchAll(String searchWord) throws BaseException{
        try{
            List<GetSearchAllRes> getSearchAllRes = searchDao.getSearchAll(searchWord);
            return getSearchAllRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetsearchRes> getsearch(int searchIdx) throws BaseException{
//        if (checksearchIdx(searchIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_search);
//        }
//        try{
//            List<GetsearchRes> getsearchRes = searchDao.getsearch(searchIdx);
//            return getsearchRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checksearchIdx(int searchIdx) throws BaseException{
//        try{
//            return searchDao.checksearchIdx(searchIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}

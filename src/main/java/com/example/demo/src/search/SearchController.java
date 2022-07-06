package com.example.demo.src.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.search.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/searches")

public class SearchController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SearchProvider searchProvider;
    @Autowired
    private final SearchService searchService;

    @Autowired
    private final JwtService jwtService;

    public SearchController(SearchProvider searchProvider, SearchService searchService, JwtService jwtService) {
        this.searchProvider = searchProvider;
        this.searchService = searchService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /searchs
     * @return BaseResponse<List<GetsearchsRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchesRes>> getSearches() {
        try{
            List<GetSearchesRes> getSearchesRes = searchProvider.getSearches();
            return new BaseResponse<>(getSearchesRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/posts") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchPostsRes>> getSearchPosts(@RequestParam String searchWord) {
        try{
            List<GetSearchPostsRes> getSearchPostsRes = searchProvider.getSearchPosts(searchWord);
            return new BaseResponse<>(getSearchPostsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/housewarms") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchHousewarmsRes>> getSearchHousewarms(@RequestParam String searchWord) {
        try{
            List<GetSearchHousewarmsRes> getSearchHousewarmsRes = searchProvider.getSearchHousewarms(searchWord);
            return new BaseResponse<>(getSearchHousewarmsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/knowhows") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchKnowhowsRes>> getSearchKnowhows(@RequestParam String searchWord) {
        try{
            List<GetSearchKnowhowsRes> getSearchKnowhowsRes = searchProvider.getSearchKnowhows(searchWord);
            return new BaseResponse<>(getSearchKnowhowsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/stores") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchProductsRes>> getSearchProducts(@RequestParam String searchWord) {
        try{
            List<GetSearchProductsRes> getSearchProductsRes = searchProvider.getSearchProducts(searchWord);
            return new BaseResponse<>(getSearchProductsRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/questionAnswers") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchQuestionAnswersRes>> getSearchQuestionAnswers(@RequestParam String searchWord) {
        try{
            List<GetSearchQuestionAnswersRes> getSearchQuestionAnswersRes = searchProvider.getSearchQuestionAnswers(searchWord);
            return new BaseResponse<>(getSearchQuestionAnswersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/all") // (GET) 127.0.0.1:9000/app/searchs
    public BaseResponse<List<GetSearchAllRes>> getSearchAll(@RequestParam String searchWord) {
        try{
            List<GetSearchAllRes> getSearchAllRes = searchProvider.getSearchAll(searchWord);
            return new BaseResponse<>(getSearchAllRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /searchs/:searchIdx
//     * @return BaseResponse<GetsearchRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{searchIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetsearchRes>> getsearch(@PathVariable("userIdx") int userIdx, @PathVariable("searchIdx") int searchIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetsearchRes> getsearchRes = searchProvider.getsearch(searchIdx);
//            return new BaseResponse<>(getsearchRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}

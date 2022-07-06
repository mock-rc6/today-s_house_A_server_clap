package com.example.demo.src.search;

import com.example.demo.src.category.model.GetCategories2Res;
import com.example.demo.src.housewarm.model.GetHousewarmsPostRes;
import com.example.demo.src.search.model.*;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchDao {

    private JdbcTemplate jdbcTemplate;
    private List<GetSearchHistoriesRes> getSearchHistoriesRes;
    private List<GetSearchWordsRes> getSearchWordsRes;
    private List<GetCategories1Res> getCategories1Res;
    private List<GetHousewarmsRes> getHousewarmsRes;
    private List<GetProHousewarmsRes> getProHousewarmsRes;
    private List<com.example.demo.src.search.model.GetUsersRes> getUsersRes;
    private List<GetThemesRes> getThemesRes;
    private List<GetTagsRes> getTagsRes;

    private List<GetSearchPostsRes> getSearchPostsRes;
    private List<GetSearchProductsRes> getSearchProductsRes;
    private List<GetSearchHousewarmsRes> getSearchHousewarmsRes;
    private List<GetSearchKnowhowsRes> getSearchKnowhowsRes;
    private List<GetSearchQuestionAnswersRes> getSearchQuestionAnswersRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetSearchesRes> getSearches() {
        String getSearchesQuery = "select * from searchHistory where searchHistoryIdx = 1";
        return this.jdbcTemplate.query(getSearchesQuery,
                (rs, rowNum) -> new GetSearchesRes(
                        getSearchHistoriesRes = this.jdbcTemplate.query("select searchHistory from searchHistory order by searchHistoryIdx desc",
                                (rs2, rowNum2) -> new GetSearchHistoriesRes(
                                        rs2.getString("searchHistory")
                                )
                        ),
                        getSearchWordsRes = this.jdbcTemplate.query("select searchWord from searchWord",
                                (rs3, rowNum3) -> new GetSearchWordsRes(
                                        rs3.getString("searchWord")
                                )
                        ),
                        getCategories1Res = this.jdbcTemplate.query("select imageUrl from categori1;",
                                (rs4, rowNum4) -> new GetCategories1Res(
                                        rs4.getString("imageUrl")
                                )
                        )
                )
        );
    }

    public List<GetSearchPostsRes> getSearchPosts(String searchWord){
        String getSearchPostsQuery = "select postIdx, imageUrl, content from post where content Like ?";
        String getSearchPostsParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchPostsQuery,
                (rs, rowNum) -> new GetSearchPostsRes(
                        rs.getInt("postIdx"),
                        rs.getString("content"),
                        rs.getString("imageUrl")),
                getSearchPostsParams);
    }

    public List<GetSearchHousewarmsRes> getSearchHousewarms(String searchWord){
        String getSearchHousewarmsQuery = "select * from housewarm where housewarmIdx = 1";
        String getSearchHousewarmsParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchHousewarmsQuery,
                (rs, rowNum) -> new GetSearchHousewarmsRes(
                        getHousewarmsRes = this.jdbcTemplate.query("select housewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from housewarm where content Like ?",
                                (rs2, rowNum2) -> new GetHousewarmsRes(
                                        rs2.getInt("housewarmIdx"),
                                        rs2.getString("imageUrl"),
                                        rs2.getString("content"),
                                        rs2.getString("scrap"),
                                        rs2.getString("view"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join housewarm using (userIdx) where housewarmIdx = ?",
                                                (rs3, rowNum3) -> new GetUsersRes(
                                                        rs3.getString("nickname"),
                                                        rs3.getString("profileImgUrl")
                                                ), rs2.getInt("housewarmIdx")
                                        )
                                )
                        ,getSearchHousewarmsParams),
                        getProHousewarmsRes = this.jdbcTemplate.query("select proHousewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from proHousewarm where content Like ?",
                                (rs3, rowNum3) -> new GetProHousewarmsRes(
                                        rs3.getInt("proHousewarmIdx"),
                                        rs3.getString("imageUrl"),
                                        rs3.getString("content"),
                                        rs3.getString("scrap"),
                                        rs3.getString("view"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join proHousewarm using (userIdx) where proHousewarmIdx = ?",
                                                (rs4, rowNum4) -> new GetUsersRes(
                                                        rs4.getString("nickname"),
                                                        rs4.getString("profileImgUrl")
                                                ), rs3.getInt("proHousewarmIdx")
                                        )
                                )
                                ,getSearchHousewarmsParams)
                        )
        );
    }

    public List<GetSearchKnowhowsRes> getSearchKnowhows(String searchWord){
        String getSearchKnowhowsQuery = "select knowhowIdx, imageUrl, content, (concat('스크랩 ' , FORMAT(scrap, 0))) as scrap, (concat('조회수 ', FORMAT(view, 0))) as view from knowhow where content Like ?";
        String getSearchKnowhowsParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchKnowhowsQuery,
                (rs, rowNum) -> new GetSearchKnowhowsRes(
                        rs.getInt("knowhowIdx"),
                        rs.getString("imageUrl"),
                        rs.getString("content"),
                        rs.getString("scrap"),
                        rs.getString("view"),
                        getThemesRes = this.jdbcTemplate.query("select theme from knowhowTheme inner join knowhow using (knowhowThemeIdx) where knowhowIdx = ?",
                                (rs2, rowNum2) -> new GetThemesRes(
                                        rs2.getString("theme")
                                ), rs.getInt("knowhowIdx")
                        )
                ),
                getSearchKnowhowsParams);
    }

    public List<GetSearchProductsRes> getSearchProducts(String searchWord){
        String getSearchProductsQuery = "select productIdx, image, brand, title, rating, ratingTotal, (concat(discountRate,'%')) as discountRate, (concat(FORMAT(price, 0),'원')) as price, specialPriceImgUrl as specialPrice, deliveryImgUrl as delivery from product where title Like ?";
        String getSearchProductsParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchProductsQuery,
                (rs, rowNum) -> new GetSearchProductsRes(
                        rs.getInt("productIdx"),
                        rs.getString("image"),
                        rs.getString("brand"),
                        rs.getString("title"),
                        rs.getString("discountRate"),
                        rs.getString("price"),
                        rs.getDouble("rating"),
                        rs.getInt("ratingTotal"),
                        rs.getString("delivery"),
                        rs.getString("specialPrice")),
                getSearchProductsParams);
    }

    public List<GetSearchQuestionAnswersRes> getSearchQuestionAnswers(String searchWord){
        String getSearchQuestionAnswersQuery = "select questionAnswerIdx, imageUrl, content, (concat('댓글 ', FORMAT(comment, 0))) as comment, (concat('조회 ',FORMAT(view, 0))) as view, (date_format(createdAt, '%Y-%m-%d')) as date from questionAnswer where content like ?";
        String getSearchQuestionAnswersParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchQuestionAnswersQuery,
                (rs, rowNum) -> new GetSearchQuestionAnswersRes(
                        rs.getInt("questionAnswerIdx"),
                        rs.getString("imageUrl"),
                        rs.getString("content"),
                        rs.getString("comment"),
                        rs.getString("view"),
                        rs.getString("date"),
                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join questionAnswer using (userIdx) where questionAnswerIdx = ?",
                                (rs2, rowNum2) -> new GetUsersRes(
                                        rs2.getString("nickname"),
                                        rs2.getString("profileImgUrl")
                                ), rs.getInt("questionAnswerIdx")
                        ),
                        getTagsRes = this.jdbcTemplate.query("select tag from tag inner join tagCont using (tagIdx) where questionAnswerIdx = ?",
                                (rs3, rowNum3) -> new GetTagsRes(
                                        rs3.getString("tag")
                                ), rs.getInt("questionAnswerIdx")
                        )
                ),
                getSearchQuestionAnswersParams);
    }

    public List<GetSearchAllRes> getSearchAll(String searchWord){
        String getSearchAllQuery = "select * from product where productIdx = 1";
        String getSearchAllParams = '%' + searchWord + '%';
        return this.jdbcTemplate.query(getSearchAllQuery,
                (rs, rowNum) -> new GetSearchAllRes(
                        getSearchProductsRes = this.jdbcTemplate.query("select productIdx, image, brand, title, rating, ratingTotal, (concat(discountRate,'%')) as discountRate, (concat(FORMAT(price, 0),'원')) as price, specialPriceImgUrl as specialPrice, deliveryImgUrl as delivery from product where title Like ?",
                                (rs2, rowNum2) -> new GetSearchProductsRes(
                                        rs2.getInt("productIdx"),
                                        rs2.getString("image"),
                                        rs2.getString("brand"),
                                        rs2.getString("title"),
                                        rs2.getString("discountRate"),
                                        rs2.getString("price"),
                                        rs2.getDouble("rating"),
                                        rs2.getInt("ratingTotal"),
                                        rs2.getString("delivery"),
                                        rs2.getString("specialPrice")),
                                getSearchAllParams
                        ),

                        getSearchPostsRes = this.jdbcTemplate.query("select postIdx, imageUrl, content from post where content Like ?",
                                (rs3, rowNum3) -> new GetSearchPostsRes(
                                        rs3.getInt("postIdx"),
                                        rs3.getString("content"),
                                        rs3.getString("imageUrl")),
                                getSearchAllParams
                        ),

                        getSearchHousewarmsRes = this.jdbcTemplate.query("select * from housewarm where housewarmIdx = 1",
                                (rs4, rowNum4) -> new GetSearchHousewarmsRes(
                                        getHousewarmsRes = this.jdbcTemplate.query("select housewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from housewarm where content Like ?",
                                                (rs5, rowNum5) -> new GetHousewarmsRes(
                                                        rs5.getInt("housewarmIdx"),
                                                        rs5.getString("imageUrl"),
                                                        rs5.getString("content"),
                                                        rs5.getString("scrap"),
                                                        rs5.getString("view"),
                                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join housewarm using (userIdx) where housewarmIdx = ?",
                                                                (rs6, rowNum6) -> new GetUsersRes(
                                                                        rs6.getString("nickname"),
                                                                        rs6.getString("profileImgUrl")
                                                                ), rs5.getInt("housewarmIdx")
                                                        )
                                                )
                                                ,getSearchAllParams),
                                        getProHousewarmsRes = this.jdbcTemplate.query("select proHousewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from proHousewarm where content Like ?",
                                                (rs7, rowNum7) -> new GetProHousewarmsRes(
                                                        rs7.getInt("proHousewarmIdx"),
                                                        rs7.getString("imageUrl"),
                                                        rs7.getString("content"),
                                                        rs7.getString("scrap"),
                                                        rs7.getString("view"),
                                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join proHousewarm using (userIdx) where proHousewarmIdx = ?",
                                                                (rs8, rowNum8) -> new GetUsersRes(
                                                                        rs8.getString("nickname"),
                                                                        rs8.getString("profileImgUrl")
                                                                ), rs7.getInt("proHousewarmIdx")
                                                        )
                                                )
                                                ,getSearchAllParams)
                                        )
                        ),

                        getSearchKnowhowsRes = this.jdbcTemplate.query("select knowhowIdx, imageUrl, content, (concat('스크랩 ' , FORMAT(scrap, 0))) as scrap, (concat('조회수 ', FORMAT(view, 0))) as view from knowhow where content Like ?",
                                (rs9, rowNum9) -> new GetSearchKnowhowsRes(
                                        rs9.getInt("knowhowIdx"),
                                        rs9.getString("imageUrl"),
                                        rs9.getString("content"),
                                        rs9.getString("scrap"),
                                        rs9.getString("view"),
                                        getThemesRes = this.jdbcTemplate.query("select theme from knowhowTheme inner join knowhow using (knowhowThemeIdx) where knowhowIdx = ?",
                                                (rs10, rowNum10) -> new GetThemesRes(
                                                        rs10.getString("theme")
                                                ), rs9.getInt("knowhowIdx")
                                        )
                                ), getSearchAllParams
                        ),

                        getSearchQuestionAnswersRes = this.jdbcTemplate.query("select questionAnswerIdx, imageUrl, content, (concat('댓글 ', FORMAT(comment, 0))) as comment, (concat('조회 ',FORMAT(view, 0))) as view, (date_format(createdAt, '%Y-%m-%d')) as date from questionAnswer where content like ?",
                                (rs11, rowNum11) -> new GetSearchQuestionAnswersRes(
                                        rs11.getInt("questionAnswerIdx"),
                                        rs11.getString("imageUrl"),
                                        rs11.getString("content"),
                                        rs11.getString("comment"),
                                        rs11.getString("view"),
                                        rs11.getString("date"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join questionAnswer using (userIdx) where questionAnswerIdx = ?",
                                                (rs12, rowNum12) -> new GetUsersRes(
                                                        rs12.getString("nickname"),
                                                        rs12.getString("profileImgUrl")
                                                ), rs11.getInt("questionAnswerIdx")
                                        ),
                                        getTagsRes = this.jdbcTemplate.query("select tag from tag inner join tagCont using (tagIdx) where questionAnswerIdx = ?",
                                                (rs13, rowNum13) -> new GetTagsRes(
                                                        rs13.getString("tag")
                                                ), rs11.getInt("questionAnswerIdx")
                                        )
                                ), getSearchAllParams
                        )
                )
        );
    }

//    public List<GetsearchRes> getsearch(int searchIdx) {
//        String getsearchQuery = "select searchIdx, name from search where searchIdx = ?";
//        int getsearchParams = searchIdx;
//        return this.jdbcTemplate.query(getsearchQuery,
//                (rs, rowNum) -> new GetsearchRes(
//                        rs.getInt("searchIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join searchVideo using (seriesIdx) where searchVideo.searchIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("searchIdx")
//                        )),
//                getsearchParams);
//    }
//
//    public int checksearchIdx(int searchIdx){
//        String checksearchIdxQuery = "select exists(select searchIdx from search where searchIdx = ?)";
//        int checksearchIdxParams = searchIdx;
//        return this.jdbcTemplate.queryForObject(checksearchIdxQuery,
//                int.class,
//                checksearchIdxParams);
//    }


}

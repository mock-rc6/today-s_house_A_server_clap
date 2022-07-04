package com.example.demo.src.search;

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

    public List<GetSearchRes> getSearch(String searchWord){
        String getSearchQuery = "select content from knowhow where content Like ?";
        String getSearchParams = '%' + searchWord + '%';
        System.out.println(getSearchParams);
        return this.jdbcTemplate.query(getSearchQuery,
                (rs, rowNum) -> new GetSearchRes(
                        rs.getString("content")),
                getSearchParams);
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

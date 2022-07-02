package com.example.demo.src.popular;

import com.example.demo.src.popular.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PopularDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetEventBannersRes> getEventBannersRes;
    private List<GetEventIconsRes> getEventIconsRes;
    private List<GetHousewarmsRes> getHousewarmsRes;
    private List<GetKnowhowsRes> getKnowhowsRes;

    private List<GetProHousewarmsRes> getProHousewarmsRes;

    private List<GetCategoriesRes> getCategoriesRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetPopularsRes> getPopulars() {
        String getPopularsQuery = "select * from event where eventIdx = 1";
        return this.jdbcTemplate.query(getPopularsQuery,
                (rs, rowNum) -> new GetPopularsRes(
                        getEventBannersRes = this.jdbcTemplate.query("select * from event",
                                (rs2, rowNum2) -> new GetEventBannersRes(
                                        rs2.getString("eventImgUrl")
                                )
                        ),
                        getEventIconsRes = this.jdbcTemplate.query("select * from eventIcon",
                                (rs3, rowNum3) -> new GetEventIconsRes(
                                        rs3.getString("eventImgUrl")
                                )
                        ),
                        getHousewarmsRes = this.jdbcTemplate.query("select imageUrl, content from housewarm inner join popularHousewarms using (housewarmIdx)",
                                (rs4, rowNum4) -> new GetHousewarmsRes(
                                        rs4.getString("imageUrl"),
                                        rs4.getString("content")
                                )
                        ),
                        getKnowhowsRes = this.jdbcTemplate.query("select imageUrl, content from knowhow inner join popularKnowhows using (knowhowIdx)",
                                (rs5, rowNum5) -> new GetKnowhowsRes(
                                        rs5.getString("imageUrl"),
                                        rs5.getString("content")
                                )
                        ),
                        getProHousewarmsRes = this.jdbcTemplate.query("select imageUrl, content from proHousewarm inner join popularProHousewarms using (proHousewarmIdx)",
                                (rs5, rowNum5) -> new GetProHousewarmsRes(
                                        rs5.getString("imageUrl"),
                                        rs5.getString("content")
                                )
                        ),
                        getCategoriesRes = this.jdbcTemplate.query("select imageUrl from categori1",
                                (rs6, rowNum6) -> new GetCategoriesRes(
                                        rs6.getString("imageUrl")
                                )
                        )
                )
        );
    }

//    public List<GetpopularRes> getpopular(int popularIdx) {
//        String getpopularQuery = "select popularIdx, name from popular where popularIdx = ?";
//        int getpopularParams = popularIdx;
//        return this.jdbcTemplate.query(getpopularQuery,
//                (rs, rowNum) -> new GetpopularRes(
//                        rs.getInt("popularIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join popularVideo using (seriesIdx) where popularVideo.popularIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("popularIdx")
//                        )),
//                getpopularParams);
//    }
//
//    public int checkpopularIdx(int popularIdx){
//        String checkpopularIdxQuery = "select exists(select popularIdx from popular where popularIdx = ?)";
//        int checkpopularIdxParams = popularIdx;
//        return this.jdbcTemplate.queryForObject(checkpopularIdxQuery,
//                int.class,
//                checkpopularIdxParams);
//    }


}

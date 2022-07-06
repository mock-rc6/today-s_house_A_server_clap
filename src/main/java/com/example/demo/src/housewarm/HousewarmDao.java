package com.example.demo.src.housewarm;

import com.example.demo.src.housewarm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HousewarmDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetHousewarmsPostRes> getHousewarmsPostRes;
    private List<GetUsersRes> getUsersRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetHousewarmsRes> getHousewarms() {
        String getHousewarmsQuery = "SELECT (concat('전체 ',(FORMAT(COUNT(housewarmIdx), 0)))) as totalViews FROM housewarm";
        return this.jdbcTemplate.query(getHousewarmsQuery,
                (rs, rowNum) -> new GetHousewarmsRes(
                        rs.getString("totalViews"),
                        getHousewarmsPostRes = this.jdbcTemplate.query("select housewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from housewarm",
                                (rs2, rowNum2) -> new GetHousewarmsPostRes(
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
                        )
                )
        );
    }

//    public List<GethousewarmRes> gethousewarm(int housewarmIdx) {
//        String gethousewarmQuery = "select housewarmIdx, name from housewarm where housewarmIdx = ?";
//        int gethousewarmParams = housewarmIdx;
//        return this.jdbcTemplate.query(gethousewarmQuery,
//                (rs, rowNum) -> new GethousewarmRes(
//                        rs.getInt("housewarmIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join housewarmVideo using (seriesIdx) where housewarmVideo.housewarmIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("housewarmIdx")
//                        )),
//                gethousewarmParams);
//    }
//
//    public int checkhousewarmIdx(int housewarmIdx){
//        String checkhousewarmIdxQuery = "select exists(select housewarmIdx from housewarm where housewarmIdx = ?)";
//        int checkhousewarmIdxParams = housewarmIdx;
//        return this.jdbcTemplate.queryForObject(checkhousewarmIdxQuery,
//                int.class,
//                checkhousewarmIdxParams);
//    }


}

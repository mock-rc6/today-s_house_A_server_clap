package com.example.demo.src.proHousewarm;

import com.example.demo.src.proHousewarm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProHousewarmDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetProHousewarmsPostRes> getProHousewarmsPostRes;
    private List<GetUsersRes> getUsersRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetProHousewarmsRes> getProHousewarms() {
        String getProHousewarmsQuery = "SELECT (concat('전체 ',(FORMAT(COUNT(proHousewarmIdx), 0)))) as totalViews, eventImgUrl FROM proHousewarm, event where event.eventIdx = 1";
        return this.jdbcTemplate.query(getProHousewarmsQuery,
                (rs, rowNum) -> new GetProHousewarmsRes(
                        rs.getString("totalViews"),
                        rs.getString("eventImgUrl"),
                        getProHousewarmsPostRes = this.jdbcTemplate.query("select proHousewarmIdx, imageUrl, content, (concat(FORMAT(scrap, 0),'명 스크랩')) as scrap, (concat(FORMAT(view, 0),'명 조회')) as view from proHousewarm",
                                (rs2, rowNum2) -> new GetProHousewarmsPostRes(
                                        rs2.getInt("proHousewarmIdx"),
                                        rs2.getString("imageUrl"),
                                        rs2.getString("content"),
                                        rs2.getString("scrap"),
                                        rs2.getString("view"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join proHousewarm using (userIdx) where proHousewarmIdx = ?",
                                                (rs3, rowNum3) -> new GetUsersRes(
                                                        rs3.getString("nickname"),
                                                        rs3.getString("profileImgUrl")
                                                ), rs2.getInt("proHousewarmIdx")
                                        )
                                )
                        ))
        );
    }

//    public List<GetproHousewarmRes> getproHousewarm(int proHousewarmIdx) {
//        String getproHousewarmQuery = "select proHousewarmIdx, name from proHousewarm where proHousewarmIdx = ?";
//        int getproHousewarmParams = proHousewarmIdx;
//        return this.jdbcTemplate.query(getproHousewarmQuery,
//                (rs, rowNum) -> new GetproHousewarmRes(
//                        rs.getInt("proHousewarmIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join proHousewarmVideo using (seriesIdx) where proHousewarmVideo.proHousewarmIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("proHousewarmIdx")
//                        )),
//                getproHousewarmParams);
//    }
//
//    public int checkproHousewarmIdx(int proHousewarmIdx){
//        String checkproHousewarmIdxQuery = "select exists(select proHousewarmIdx from proHousewarm where proHousewarmIdx = ?)";
//        int checkproHousewarmIdxParams = proHousewarmIdx;
//        return this.jdbcTemplate.queryForObject(checkproHousewarmIdxQuery,
//                int.class,
//                checkproHousewarmIdxParams);
//    }


}

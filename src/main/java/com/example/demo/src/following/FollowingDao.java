package com.example.demo.src.following;

import com.example.demo.src.following.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FollowingDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetTagsRes> getTagsRes;

    private List<GetUserImgsRes> getUserImgsRes;
    private List<GetUsersRes> getUsersRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetFollowingsRes> getFollowings() {
        String getFollowingsQuery = "SELECT userIdx FROM followingRecommend";
        return this.jdbcTemplate.query(getFollowingsQuery,
                (rs, rowNum) -> new GetFollowingsRes(
                        rs.getInt("userIdx"),
                        getUsersRes = this.jdbcTemplate.query("select userIdx, nickname, profileImgUrl, message from user where userIdx = ?",
                                (rs2, rowNum2) -> new GetUsersRes(
                                        rs2.getString("nickname"),
                                        rs2.getString("profileImgUrl"),
                                        rs2.getString("message"),
                                        getTagsRes = this.jdbcTemplate.query("select tag from tag inner join tagCont using(tagIdx) where userIdx = ?",
                                                (rs3, rowNum3) -> new GetTagsRes(
                                                        rs3.getString("tag")
                                                ), rs.getInt("userIdx")
                                        ),
                                        getUserImgsRes = this.jdbcTemplate.query("select userImgUrl from userImg where userIdx = ?",
                                                (rs4, rowNum4) -> new GetUserImgsRes(
                                                        rs4.getString("userImgUrl")
                                                ), rs.getInt("userIdx")
                                        )
                                ), rs.getInt("userIdx")
                        )
                )
        );
    }

//    public List<GetfollowingRes> getfollowing(int followingIdx) {
//        String getfollowingQuery = "select followingIdx, name from following where followingIdx = ?";
//        int getfollowingParams = followingIdx;
//        return this.jdbcTemplate.query(getfollowingQuery,
//                (rs, rowNum) -> new GetfollowingRes(
//                        rs.getInt("followingIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join followingVideo using (seriesIdx) where followingVideo.followingIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("followingIdx")
//                        )),
//                getfollowingParams);
//    }
//
//    public int checkfollowingIdx(int followingIdx){
//        String checkfollowingIdxQuery = "select exists(select followingIdx from following where followingIdx = ?)";
//        int checkfollowingIdxParams = followingIdx;
//        return this.jdbcTemplate.queryForObject(checkfollowingIdxQuery,
//                int.class,
//                checkfollowingIdxParams);
//    }


}

package com.example.demo.src.post;

import com.example.demo.src.post.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;

    //private List<GetSeriesRes> getSeriesRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetPostsRes> getPosts() {
        String getPostsQuery = "select postIdx, content, imageUrl from post";
        return this.jdbcTemplate.query(getPostsQuery,
                (rs, rowNum) -> new GetPostsRes(
                        rs.getInt("postIdx"),
                        rs.getString("content"),
                        rs.getString("imageUrl"))
        );
    }

//    public List<GetpostRes> getpost(int postIdx) {
//        String getpostQuery = "select postIdx, name from post where postIdx = ?";
//        int getpostParams = postIdx;
//        return this.jdbcTemplate.query(getpostQuery,
//                (rs, rowNum) -> new GetpostRes(
//                        rs.getInt("postIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join postVideo using (seriesIdx) where postVideo.postIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("postIdx")
//                        )),
//                getpostParams);
//    }
//
//    public int checkpostIdx(int postIdx){
//        String checkpostIdxQuery = "select exists(select postIdx from post where postIdx = ?)";
//        int checkpostIdxParams = postIdx;
//        return this.jdbcTemplate.queryForObject(checkpostIdxQuery,
//                int.class,
//                checkpostIdxParams);
//    }


}

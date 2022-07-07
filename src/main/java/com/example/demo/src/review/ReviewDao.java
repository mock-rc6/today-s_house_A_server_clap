package com.example.demo.src.review;

import com.example.demo.src.review.model.*;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetUserRes> getUserRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createReview(PostReviewReq postReviewReq){
        String createReviewQuery = "insert into review (userIdx, productIdx, content, rating) VALUES (?,?,?,?)";
        Object[] createReviewParams = new Object[]{postReviewReq.getUserIdx(), postReviewReq.getProductIdx(), postReviewReq.getContent(), postReviewReq.getRating()};
        return this.jdbcTemplate.update(createReviewQuery, createReviewParams);

    }

    public List<GetReviewsRes> getReviews(int productIdx) {
        String getReviewsQuery = "select userIdx, content, purchasePlace, rating, date_format(createdAt, '%Y.%m.%d') as createdAt from review where productIdx = ?";
        int getReviewsParams = productIdx;
        return this.jdbcTemplate.query(getReviewsQuery,
                (rs, rowNum) -> new GetReviewsRes(
                        rs.getInt("userIdx"),
                        rs.getString("content"),
                        rs.getString("purchasePlace"),
                        rs.getDouble("rating"),
                        rs.getString("createdAt"),
                        getUserRes = this.jdbcTemplate.query("select profileImgUrl, nickname from user where userIdx = ?",
                                (rs2, rowNum2) -> new GetUserRes(
                                        rs2.getString("profileImgUrl"),
                                        rs2.getString("nickname")
                                ), rs.getInt("userIdx")
                        )),
                getReviewsParams);
    }

    public int checkProductIdx(int productIdx){
        String checkProductIdxQuery = "select exists(select productIdx from product where productIdx = ?)";
        int checkProductIdxParams = productIdx;
        return this.jdbcTemplate.queryForObject(checkProductIdxQuery,
                int.class,
                checkProductIdxParams);
    }
//
//    public int checkreviewIdx(int reviewIdx){
//        String checkreviewIdxQuery = "select exists(select reviewIdx from review where reviewIdx = ?)";
//        int checkreviewIdxParams = reviewIdx;
//        return this.jdbcTemplate.queryForObject(checkreviewIdxQuery,
//                int.class,
//                checkreviewIdxParams);
//    }


}

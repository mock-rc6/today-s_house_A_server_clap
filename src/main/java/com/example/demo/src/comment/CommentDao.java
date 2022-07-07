package com.example.demo.src.comment;

import com.example.demo.src.comment.model.*;
import com.example.demo.src.user.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetUserRes> getUserRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createPostsComment(PostPostsCommentReq postPostsCommentReq){
        String createPostsCommentQuery = "insert into postComment (postIdx, content, userIdx) VALUES (?,?,?)";
        Object[] createPostsCommentParams = new Object[]{postPostsCommentReq.getPostIdx(), postPostsCommentReq.getContent(), postPostsCommentReq.getUserIdx()};
        return this.jdbcTemplate.update(createPostsCommentQuery, createPostsCommentParams);
    }

    public List<GetPostsCommentsRes> getPostsComments(int postIdx) {
        String getPostsCommentsQuery = "select userIdx, content, " +
                "case\n" +
                "    when TIMESTAMPDIFF(MINUTE, createdAt, NOW()) < 60 then (concat(TIMESTAMPDIFF(MINUTE, createdAt, NOW()),'분'))\n" +
                "    when TIMESTAMPDIFF(HOUR, createdAt, NOW()) < 24 then (concat(TIMESTAMPDIFF(HOUR, createdAt, NOW()),'시간'))\n" +
                "    when TIMESTAMPDIFF(DAY, createdAt, NOW()) < 7 then (concat(TIMESTAMPDIFF(DAY, createdAt, NOW()),'일'))\n" +
                "    when TIMESTAMPDIFF(WEEK, createdAt, NOW()) < 4 then (concat(TIMESTAMPDIFF(WEEK, createdAt, NOW()),'주'))\n" +
                "    WHEN TIMESTAMPDIFF(MONTH, createdAt, NOW()) < 12 then (concat(TIMESTAMPDIFF(MONTH, createdAt, NOW()),'개월'))\n" +
                "    ELSE (concat(TIMESTAMPDIFF(YEAR, createdAt, NOW()),'년')) END AS createdAt " +
                "from postComment where postIdx = ?";
        int getPostsCommentsParams = postIdx;
        return this.jdbcTemplate.query(getPostsCommentsQuery,
                (rs, rowNum) -> new GetPostsCommentsRes(
                        rs.getInt("userIdx"),
                        rs.getString("content"),
                        rs.getString("createdAt"),
                        getUserRes = this.jdbcTemplate.query("select profileImgUrl, nickname from user where userIdx = ?",
                                (rs2, rowNum2) -> new GetUserRes(
                                        rs2.getString("profileImgUrl"),
                                        rs2.getString("nickname")
                                ), rs.getInt("userIdx")
                        )),
                getPostsCommentsParams);
    }

    public int checkPostIdx(int postIdx){
        String checkPostIdxQuery = "select exists(select postIdx from post where postIdx = ?)";
        int checkPostIdxParams = postIdx;
        return this.jdbcTemplate.queryForObject(checkPostIdxQuery,
                int.class,
                checkPostIdxParams);
    }
//
//    public int checkcommentIdx(int commentIdx){
//        String checkcommentIdxQuery = "select exists(select commentIdx from comment where commentIdx = ?)";
//        int checkcommentIdxParams = commentIdx;
//        return this.jdbcTemplate.queryForObject(checkcommentIdxQuery,
//                int.class,
//                checkcommentIdxParams);
//    }


}

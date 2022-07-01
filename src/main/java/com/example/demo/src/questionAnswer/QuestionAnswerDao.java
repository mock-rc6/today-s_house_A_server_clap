package com.example.demo.src.questionAnswer;

import com.example.demo.src.questionAnswer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class QuestionAnswerDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetQuestionAnswersPostRes> getQuestionAnswersPostRes;
    private List<GetUsersRes> getUsersRes;

    private List<GetTagsRes> getTagsRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetQuestionAnswersRes> getQuestionAnswers() {
        String getQuestionAnswersQuery = "SELECT * FROM questionAnswer where questionAnswerIdx = 1";
        return this.jdbcTemplate.query(getQuestionAnswersQuery,
                (rs, rowNum) -> new GetQuestionAnswersRes(
                        getQuestionAnswersPostRes = this.jdbcTemplate.query("select questionAnswerIdx, imageUrl, content, (concat('댓글 ', FORMAT(comment, 0))) as comment, (concat('조회 ',FORMAT(view, 0))) as view, (date_format(createdAt, '%Y-%m-%d')) as date from questionAnswer",
                                (rs2, rowNum2) -> new GetQuestionAnswersPostRes(
                                        rs2.getInt("questionAnswerIdx"),
                                        rs2.getString("imageUrl"),
                                        rs2.getString("content"),
                                        rs2.getString("comment"),
                                        rs2.getString("view"),
                                        rs2.getString("date"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname, profileImgUrl from user inner join questionAnswer using (userIdx) where questionAnswerIdx = ?",
                                                (rs3, rowNum3) -> new GetUsersRes(
                                                        rs3.getString("nickname"),
                                                        rs3.getString("profileImgUrl")
                                                ), rs2.getInt("questionAnswerIdx")
                                        ),
                                        getTagsRes = this.jdbcTemplate.query("select tag from tag inner join tagCont using (tagIdx) where questionAnswerIdx = ?",
                                                (rs4, rowNum4) -> new GetTagsRes(
                                                        rs4.getString("tag")
                                                ), rs2.getInt("questionAnswerIdx")
                                        )
                                )
                        ))
        );
    }

//    public List<GetquestionAnswerRes> getquestionAnswer(int questionAnswerIdx) {
//        String getquestionAnswerQuery = "select questionAnswerIdx, name from questionAnswer where questionAnswerIdx = ?";
//        int getquestionAnswerParams = questionAnswerIdx;
//        return this.jdbcTemplate.query(getquestionAnswerQuery,
//                (rs, rowNum) -> new GetquestionAnswerRes(
//                        rs.getInt("questionAnswerIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join questionAnswerVideo using (seriesIdx) where questionAnswerVideo.questionAnswerIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("questionAnswerIdx")
//                        )),
//                getquestionAnswerParams);
//    }
//
//    public int checkquestionAnswerIdx(int questionAnswerIdx){
//        String checkquestionAnswerIdxQuery = "select exists(select questionAnswerIdx from questionAnswer where questionAnswerIdx = ?)";
//        int checkquestionAnswerIdxParams = questionAnswerIdx;
//        return this.jdbcTemplate.queryForObject(checkquestionAnswerIdxQuery,
//                int.class,
//                checkquestionAnswerIdxParams);
//    }


}

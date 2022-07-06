package com.example.demo.src.knowhow;

import com.example.demo.src.knowhow.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class KnowhowDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetGuideBooksRes> getGuideBooksRes;
    private List<GetKnowhowsPostRes> getKnowhowsPostRes;

    private List<GetUsersRes> getUsersRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetKnowhowsRes> getKnowhows() {
        String getKnowhowsQuery = "select * from knowhow where knowhowIdx = 1";
        return this.jdbcTemplate.query(getKnowhowsQuery,
                (rs, rowNum) -> new GetKnowhowsRes(
                        getGuideBooksRes = this.jdbcTemplate.query("select guideBookImgUrl from guideBook",
                                (rs2, rowNum2) -> new GetGuideBooksRes(
                                        rs2.getString("guideBookImgUrl")
                                )
                        ),
                        getKnowhowsPostRes = this.jdbcTemplate.query("select knowhowIdx, imageUrl, content, (concat('스크랩 ' , FORMAT(scrap, 0))) as scrap, (concat('조회수 ', FORMAT(view, 0))) as view from knowhow",
                                (rs3, rowNum3) -> new GetKnowhowsPostRes(
                                        rs3.getInt("knowhowIdx"),
                                        rs3.getString("imageUrl"),
                                        rs3.getString("content"),
                                        rs3.getString("scrap"),
                                        rs3.getString("view"),
                                        getUsersRes = this.jdbcTemplate.query("select nickname from user inner join knowhow using (userIdx) where knowhowIdx = ?",
                                                (rs4, rowNum4) -> new GetUsersRes(
                                                        rs4.getString("nickname")
                                                ), rs3.getInt("knowhowIdx")
                                        )
                                )
                        )
                )
        );
    }

//    public List<GetknowhowRes> getknowhow(int knowhowIdx) {
//        String getknowhowQuery = "select knowhowIdx, name from knowhow where knowhowIdx = ?";
//        int getknowhowParams = knowhowIdx;
//        return this.jdbcTemplate.query(getknowhowQuery,
//                (rs, rowNum) -> new GetknowhowRes(
//                        rs.getInt("knowhowIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join knowhowVideo using (seriesIdx) where knowhowVideo.knowhowIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("knowhowIdx")
//                        )),
//                getknowhowParams);
//    }
//
//    public int checkknowhowIdx(int knowhowIdx){
//        String checkknowhowIdxQuery = "select exists(select knowhowIdx from knowhow where knowhowIdx = ?)";
//        int checkknowhowIdxParams = knowhowIdx;
//        return this.jdbcTemplate.queryForObject(checkknowhowIdxQuery,
//                int.class,
//                checkknowhowIdxParams);
//    }


}

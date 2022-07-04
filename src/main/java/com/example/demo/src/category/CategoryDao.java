package com.example.demo.src.category;

import com.example.demo.src.category.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetCategories1Res> getCategories1Res;
    private List<GetCategories2Res> getCategories2Res;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetCategoriesRes> getCategories() {
        String getCategoriesQuery = "select * from categori1 where categori1Idx = 1";
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoriesRes(
                        getCategories1Res = this.jdbcTemplate.query("select categori1Idx, categori1 from categori1",
                                (rs2, rowNum2) -> new GetCategories1Res(
                                        rs2.getInt("categori1Idx"),
                                        rs2.getString("categori1"),
                                        getCategories2Res = this.jdbcTemplate.query("select categori2Idx, categori2 from categori2 where categori1Idx = ?",
                                                (rs3, rowNum3) -> new GetCategories2Res(
                                                        rs3.getInt("categori2Idx"),
                                                        rs3.getString("categori2")
                                                ), rs2.getInt("categori1Idx")
                                        )
                                )
                        ))
        );
    }

//    public List<GetcategoryRes> getcategory(int categoryIdx) {
//        String getcategoryQuery = "select categoryIdx, name from category where categoryIdx = ?";
//        int getcategoryParams = categoryIdx;
//        return this.jdbcTemplate.query(getcategoryQuery,
//                (rs, rowNum) -> new GetcategoryRes(
//                        rs.getInt("categoryIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join categoryVideo using (seriesIdx) where categoryVideo.categoryIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("categoryIdx")
//                        )),
//                getcategoryParams);
//    }
//
//    public int checkcategoryIdx(int categoryIdx){
//        String checkcategoryIdxQuery = "select exists(select categoryIdx from category where categoryIdx = ?)";
//        int checkcategoryIdxParams = categoryIdx;
//        return this.jdbcTemplate.queryForObject(checkcategoryIdxQuery,
//                int.class,
//                checkcategoryIdxParams);
//    }


}

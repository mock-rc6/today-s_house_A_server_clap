package com.example.demo.src.store;

import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetStoreEventBannersRes> getStoreEventBannersRes;
    private List<GetStoreEventIconsRes> getStoreEventIconsRes;
    private List<GetCategoriesRes> getCategoriesRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetStoreRes> getStore() {
        String getStoreQuery = "select * from storeEvent where storeEventIdx = 1";
        return this.jdbcTemplate.query(getStoreQuery,
                (rs, rowNum) -> new GetStoreRes(
                        getStoreEventBannersRes = this.jdbcTemplate.query("select eventImgUrl from storeEvent",
                                (rs2, rowNum2) -> new GetStoreEventBannersRes(
                                        rs2.getString("eventImgUrl")
                                )
                        ),
                        getStoreEventIconsRes = this.jdbcTemplate.query("select eventImgUrl from storeEventIcon",
                                (rs3, rowNum3) -> new GetStoreEventIconsRes(
                                        rs3.getString("eventImgUrl")
                                )
                        ),
                        getCategoriesRes = this.jdbcTemplate.query("select imageUrl from categori1",
                                (rs4, rowNum4) -> new GetCategoriesRes(
                                        rs4.getString("imageUrl")
                                )
                        )
                )
        );
    }

//    public List<GetstoreRes> getstore(int storeIdx) {
//        String getstoreQuery = "select storeIdx, name from store where storeIdx = ?";
//        int getstoreParams = storeIdx;
//        return this.jdbcTemplate.query(getstoreQuery,
//                (rs, rowNum) -> new GetstoreRes(
//                        rs.getInt("storeIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join storeVideo using (seriesIdx) where storeVideo.storeIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("storeIdx")
//                        )),
//                getstoreParams);
//    }
//
//    public int checkstoreIdx(int storeIdx){
//        String checkstoreIdxQuery = "select exists(select storeIdx from store where storeIdx = ?)";
//        int checkstoreIdxParams = storeIdx;
//        return this.jdbcTemplate.queryForObject(checkstoreIdxQuery,
//                int.class,
//                checkstoreIdxParams);
//    }


}

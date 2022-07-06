package com.example.demo.src.cart;

import com.example.demo.src.cart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetCartsRes> getCarts(int userIdx) {
        String getCartsQuery = "select productIdx, image, brand, title, delivery, deliveryDetail, originalPrice,\n" +
                "deliveryFee, discount, price from product inner join cart using (productIdx) where cart.userIdx = ?";
        int getCartsParams = userIdx;
        return this.jdbcTemplate.query(getCartsQuery,
                (rs, rowNum) -> new GetCartsRes(
                        rs.getInt("productIdx"),
                        rs.getString("image"),
                        rs.getString("brand"),
                        rs.getString("title"),
                        rs.getString("delivery"),
                        rs.getString("deliveryDetail"),
                        rs.getInt("originalPrice"),
                        rs.getInt("deliveryFee"),
                        rs.getInt("discount"),
                        rs.getInt("price")
                ), getCartsParams
        );
    }

//    public List<GetcartRes> getcart(int cartIdx) {
//        String getcartQuery = "select cartIdx, name from cart where cartIdx = ?";
//        int getcartParams = cartIdx;
//        return this.jdbcTemplate.query(getcartQuery,
//                (rs, rowNum) -> new GetcartRes(
//                        rs.getInt("cartIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join cartVideo using (seriesIdx) where cartVideo.cartIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("cartIdx")
//                        )),
//                getcartParams);
//    }
//
//    public int checkcartIdx(int cartIdx){
//        String checkcartIdxQuery = "select exists(select cartIdx from cart where cartIdx = ?)";
//        int checkcartIdxParams = cartIdx;
//        return this.jdbcTemplate.queryForObject(checkcartIdxQuery,
//                int.class,
//                checkcartIdxParams);
//    }


}

package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import com.example.demo.src.store.model.GetStoreEventBannersRes;
import com.example.demo.src.store.model.GetStoreEventIconsRes;
import com.example.demo.src.store.model.GetStoreRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    private List<GetProductImgRes> getProductImgRes;
    private List<com.example.demo.src.product.model.GetCategoriesRes> getCategoriesRes;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetProductRes> getProduct(int productIdx) {
        String getProductQuery = "select productIdx, brand, title, rating, ratingTotal, (concat(discountRate,'%')) as discountRate,\n" +
                "FORMAT(originalPrice, 0) as originalPrice,\n" +
                "(concat(FORMAT(price, 0),'원')) as price, specialPriceImgUrl as specialPrice, benefit, delivery,\n" +
                "deliveryFeeDetail, deliveryDate, deliveryDetail from product where productIdx = ?";
        int getProductParams = productIdx;
        return this.jdbcTemplate.query(getProductQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("productIdx"),
                        rs.getString("brand"),
                        rs.getString("title"),
                        rs.getDouble("rating"),
                        rs.getInt("ratingTotal"),
                        rs.getString("discountRate"),
                        rs.getString("originalPrice"),
                        rs.getString("price"),
                        rs.getString("specialPrice"),
                        rs.getString("benefit"),
                        rs.getString("delivery"),
                        rs.getString("deliveryDetail"),
                        rs.getString("deliveryFeeDetail"),
                        rs.getString("deliveryDate"),
                        getProductImgRes = this.jdbcTemplate.query("select productImgUrl from productImg where productIdx = ?",
                                (rs2, rowNum2) -> new GetProductImgRes(
                                        rs2.getString("productImgUrl")
                                ), rs.getInt("productIdx")
                        ),
                        getCategoriesRes = this.jdbcTemplate.query("select (select categori1 from categori1 inner join product using (categori1Idx) where productIdx = ?) as categori1,\n" +
                                        "(select categori2 from categori2 inner join product using (categori2Idx) where productIdx = ?) as categori2,\n" +
                                        "(select categori3 from categori3 inner join product using (categori3Idx) where productIdx = ?) as categori3,\n" +
                                        "(select categori4 from categori4 inner join product using (categori4Idx) where productIdx = ?) as categori4",
                                (rs3, rowNum3) -> new GetCategoriesRes(
                                        rs3.getString("categori1"),
                                        rs3.getString("categori2"),
                                        rs3.getString("categori3"),
                                        rs3.getString("categori4")
                                ), rs.getInt("productIdx"), rs.getInt("productIdx"), rs.getInt("productIdx"), rs.getInt("productIdx")
                        )
                ),
                getProductParams);
    }

    public List<GetProductsRes> getProducts() {
        String getProductsQuery = "select productIdx, image, brand, title, rating, ratingTotal, (concat(discountRate,'%')) as discountRate,\n" +
                "(concat(FORMAT(price, 0),'원')) as price, specialPriceImgUrl as specialPrice, deliveryImgUrl as delivery from product";
        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductsRes(
                        rs.getInt("productIdx"),
                        rs.getString("image"),
                        rs.getString("brand"),
                        rs.getString("title"),
                        rs.getString("discountRate"),
                        rs.getString("price"),
                        rs.getDouble("rating"),
                        rs.getInt("ratingTotal"),
                        rs.getString("delivery"),
                        rs.getString("specialPrice")
                )
        );
    }


//    public List<GetproductRes> getproduct(int productIdx) {
//        String getproductQuery = "select productIdx, name from product where productIdx = ?";
//        int getproductParams = productIdx;
//        return this.jdbcTemplate.query(getproductQuery,
//                (rs, rowNum) -> new GetproductRes(
//                        rs.getInt("productIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join productVideo using (seriesIdx) where productVideo.productIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("productIdx")
//                        )),
//                getproductParams);
//    }
//
//    public int checkproductIdx(int productIdx){
//        String checkproductIdxQuery = "select exists(select productIdx from product where productIdx = ?)";
//        int checkproductIdxParams = productIdx;
//        return this.jdbcTemplate.queryForObject(checkproductIdxQuery,
//                int.class,
//                checkproductIdxParams);
//    }


}

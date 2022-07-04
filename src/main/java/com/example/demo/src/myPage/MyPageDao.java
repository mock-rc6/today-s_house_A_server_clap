package com.example.demo.src.myPage;

import com.example.demo.src.myPage.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MyPageDao {

    private JdbcTemplate jdbcTemplate;

    private GetScrapNumRes getScrapNumRes;

    private GetLikeNumRes getLikeNumRes;

    private GetCouponNumRes getCouponNumRes;

    private GetOrderNumRes getOrderNumRes;

    private GetFollowerRes getFollowerRes;

    private GetFollowingRes getFollowingRes;

    private GetAllImgRes getAllImgRes;

    private GetLivingRoomImgRes getLivingRoomImgRes;

    private GetBedRoomImgRes getBedRoomImgRes;

    private GetKitchenImgRes getKitchenImgRes;

    private GetWorkRoomImgRes getWorkRoomImgRes;

    private GetVerandaImgRes getVerandaImgRes;

    private GetBathRoomImgRes getBathRoomImgRes;

    private GetDressRoomImgRes getDressRoomImgRes;

    private GetFurnitureImgRes getFurnitureImgRes;

    private GetScrapImgRes getScrapImgRes;

    private GetHousewarmNumRes getHousewarmNumRes;
    private GetKnowhowNumRes getKnowhowNumRes;
    private GetReviewNumRes getReviewNumRes;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public GetMyPageRes getMyPage(int userIdx) {
        String getMyPageQuery = "select (select userIdx from user where userIdx = ?) as userIdx, (select format(point, 0) as point from user where userIdx = ?) as point,\n" +
                "(select nickname from user where userIdx = ?) as nickname, (select eventImgUrl from event where eventIdx = 4) as eventBanner1, (select eventImgUrl from event where eventIdx = 10) as eventBanner2";
        int getMyPageParams = userIdx;
        return this.jdbcTemplate.queryForObject(getMyPageQuery,
                (rs, rowNum) -> new GetMyPageRes(
                        rs.getInt("userIdx"),
                        rs.getString("point"),
                        rs.getString("nickname"),
                        rs.getString("eventBanner1"),
                        rs.getString("eventBanner2"),
                        getScrapNumRes = this.jdbcTemplate.queryForObject("select ((select count(*) from housewarmScrap where userIdx = ?) + " +
                                        "(select count(*) from proHousewarmScrap where userIdx = ?) + (select count(*) from knowhowScrap where userIdx = ?) + " +
                                        "(select count(*) from postScrap where userIdx = ?) + (select count(*) from productScrap where userIdx = ?)) as scrapNum",
                                (rs2, rowNum2) -> new GetScrapNumRes(
                                        rs2.getInt("scrapNum")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx"), rs.getInt("userIdx"), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getLikeNumRes = this.jdbcTemplate.queryForObject("select ((select count(*) from housewarmLike where userIdx = ?) + (select count(*) from proHousewarmLike where userIdx = ?) + (select count(*) from knowhowLike where userIdx = ?) + (select count(*) from postLike where userIdx = ?)) as likeNum",
                                (rs4, rowNum4) -> new GetLikeNumRes(
                                        rs4.getInt("likeNum")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx"), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getCouponNumRes = this.jdbcTemplate.queryForObject("select count(*) as couponNum from coupon where userIdx = ?",
                                (rs5, rowNum5) -> new GetCouponNumRes(
                                        rs5.getInt("couponNum")
                                ), rs.getInt("userIdx")
                        ),
                        getOrderNumRes = this.jdbcTemplate.queryForObject("select count(*) as orderNum from orders where userIdx = ?",
                                (rs6, rowNum6) -> new GetOrderNumRes(
                                        rs6.getInt("orderNum")
                                ), rs.getInt("userIdx")
                        ),
                        getFollowerRes = this.jdbcTemplate.queryForObject("select count(followingIdx) as follower from follow where followerIdx = ?",
                                (rs7, rowNum7) -> new GetFollowerRes(
                                        rs7.getInt("follower")
                                ), rs.getInt("userIdx")
                        ),
                        getFollowingRes = this.jdbcTemplate.queryForObject("select count(followerIdx)as following from follow where followingIdx = ?",
                                (rs8, rowNum8) -> new GetFollowingRes(
                                        rs8.getInt("following")
                                ), rs.getInt("userIdx")
                        ),
                        getAllImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using\n" +
                                        "    (postIdx) where postIdx = (select postIdx from post where userIdx = ? order by postIdx desc limit 1))) = 1, (select postImgUrl from postImg inner join post using\n" +
                                        "    (postIdx) where postIdx = (select postIdx from post where userIdx = ? order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs9, rowNum9) -> new GetAllImgRes(
                                        rs9.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getLivingRoomImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 23 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 23 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs10, rowNum10) -> new GetLivingRoomImgRes(
                                        rs10.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getBedRoomImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 24 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 24 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs11, rowNum11) -> new GetBedRoomImgRes(
                                        rs11.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getKitchenImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 25 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 25 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs12, rowNum12) -> new GetKitchenImgRes(
                                        rs12.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getWorkRoomImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 26 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 26 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs13, rowNum13) -> new GetWorkRoomImgRes(
                                        rs13.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getVerandaImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 27 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 27 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs14, rowNum14) -> new GetVerandaImgRes(
                                        rs14.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getBathRoomImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 28 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 28 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs15, rowNum15) -> new GetBathRoomImgRes(
                                        rs15.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getDressRoomImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 29 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 29 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs16, rowNum16) -> new GetDressRoomImgRes(
                                        rs16.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getFurnitureImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 30 order by postIdx desc limit 1))), (select postImgUrl from postImg inner join post using (postIdx)\n" +
                                        "where postIdx = (select postIdx from post inner join tagCont using (postIdx)\n" +
                                        "where post.userIdx = ? and tagCont.tagIdx = 30 order by postIdx desc limit 1)), 'none') as postImgUrl",
                                (rs17, rowNum17) -> new GetFurnitureImgRes(
                                        rs17.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getScrapImgRes = this.jdbcTemplate.queryForObject("select if ((select exists(select postImgUrl from postImg inner join postScrap using (postIdx) where userIdx = ?)), (select postImgUrl from postImg inner join postScrap using (postIdx) where userIdx = ?), ('none')) as postImgUrl",
                                (rs18, rowNum18) -> new GetScrapImgRes(
                                        rs18.getString("postImgUrl")
                                ), rs.getInt("userIdx"), rs.getInt("userIdx")
                        ),
                        getHousewarmNumRes = this.jdbcTemplate.queryForObject("select count(*) as housewarmNum from housewarm where userIdx = ?",
                                (rs19, rowNum19) -> new GetHousewarmNumRes(
                                        rs19.getInt("housewarmNum")
                                ), rs.getInt("userIdx")
                        ),
                        getKnowhowNumRes = this.jdbcTemplate.queryForObject("select count(*) as knowhowNum from knowhow where userIdx = ?",
                                (rs20, rowNum20) -> new GetKnowhowNumRes(
                                        rs20.getInt("knowhowNum")
                                ), rs.getInt("userIdx")
                        ),
                        getReviewNumRes = this.jdbcTemplate.queryForObject("select count(*) as reviewNum from review where userIdx = ?",
                                (rs21, rowNum21) -> new GetReviewNumRes(
                                        rs21.getInt("reviewNum")
                                ), rs.getInt("userIdx")
                        )


                ),getMyPageParams, getMyPageParams, getMyPageParams
        );
    }

//    public List<GetmyPageRes> getmyPage(int myPageIdx) {
//        String getmyPageQuery = "select myPageIdx, name from myPage where myPageIdx = ?";
//        int getmyPageParams = myPageIdx;
//        return this.jdbcTemplate.query(getmyPageQuery,
//                (rs, rowNum) -> new GetmyPageRes(
//                        rs.getInt("myPageIdx"),
//                        rs.getString("name"),
//                        getSeriesRes = this.jdbcTemplate.query("select name from series inner join myPageVideo using (seriesIdx) where myPageVideo.myPageIdx = ?",
//                                (rs2, rowNum2) -> new GetSeriesRes(
//                                        rs2.getString("name")
//                                ), rs.getInt("myPageIdx")
//                        )),
//                getmyPageParams);
//    }
//
//    public int checkmyPageIdx(int myPageIdx){
//        String checkmyPageIdxQuery = "select exists(select myPageIdx from myPage where myPageIdx = ?)";
//        int checkmyPageIdxParams = myPageIdx;
//        return this.jdbcTemplate.queryForObject(checkmyPageIdxQuery,
//                int.class,
//                checkmyPageIdxParams);
//    }


}

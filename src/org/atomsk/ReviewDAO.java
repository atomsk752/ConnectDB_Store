package org.atomsk;

import org.atomsk.domain.ReviewVO;
import org.atomsk.domain.ScoreVO;
import org.atomsk.domain.StoreVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public String getColor(double val){
        String str = "";
        if(val >=4){
            str = "success";
            return str;
        }else if (val >=2){
            str = "warning";
            return str;
        }else{
            str = "danger";
            return str;
        }
    }
    public String getProgress(double val){
        String str = "";
        if(val == 5){
            str = "aqua";
            return str;
        }else if (val ==4){
            str = "primary";
            return str;
        }else if (val ==3){
            str = "light-blue";
            return str;
        }else if (val ==2){
            str = "green";
            return str;
        }else if (val ==1){
            str = "yellow";
            return str;
        }else{
            str = "danger";
            return str;
        }
    }
    public String getLike(double val){
        String str = "";
        if(val >=4){
            str = "Like";
            return str;
        }else if (val >=2){
            str = "Not Bad";
            return str;
        }else{
            str = "Suck";
            return str;
        }
    }



    public List<ReviewVO> getReviews(int sno) {
        List<ReviewVO> list = new ArrayList<>();
        String sql = "select * from tbl_review \n" +
                "where mno IN (select mno from tbl_menu where sno=?)\n" +
                "order by rno desc";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.10.91:1521:XE",
                    "zerock",
                    "12345678");
            System.out.println(con);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, sno);
            //mid, mno, score

            rs = stmt.executeQuery();

            while (rs.next()) {
                ReviewVO vo = new ReviewVO();
                vo.setRno(rs.getInt("rno"));
                vo.setMid(rs.getString("mid"));
                vo.setCmt(rs.getString("cmt"));
                vo.setReviewDate(rs.getDate("reviewdate"));
                vo.setScore(rs.getDouble("score"));

                list.add(vo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }


        }
        return list;
    }

    public List<ScoreVO> getScores(int sno) {
        List<ScoreVO> list = new ArrayList<>();
        String sql = "select s.score, nvl(cnt,0) cnt,\n" +
                "  round(nvl(cnt,0) / (sum(nvl(cnt,0)) over() )*100) pnt\n" +
                "from\n" +
                "  (select round(score) score, count(rno) cnt\n" +
                "   from\n" +
                "        (select mno from tbl_menu where sno = ?) menu,\n" +
                "        tbl_review review\n" +
                "    where menu.mno = review.mno\n" +
                "    group by round(score)) review, tbl_score s\n" +
                "where s.score = review.score(+) \n" +
                "order by s.score desc";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.10.91:1521:XE",
                    "zerock",
                    "12345678");
            System.out.println(con);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, sno);
            //mid, mno, score

            rs = stmt.executeQuery();

            while (rs.next()) {
                ScoreVO vo = new ScoreVO();
                vo.setScore(rs.getInt("score"));
                vo.setCnt(rs.getInt("cnt"));
                vo.setPnt(rs.getInt("pnt"));

                list.add(vo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }


        }
        return list;
    }




    public void add(ReviewVO vo){
        //Connection 맺기

        Connection con = null;
        PreparedStatement stmt = null;
        String sql ="insert into tbl_review (rno,mid,mno,score,cmt) values (seq_review.nextval,?,?,?,?)";

        try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.10.91:1521:XE",
                    "zerock",
                    "12345678");
            System.out.println(con);
            stmt = con.prepareStatement(sql);
            //mid, mno, score
            stmt.setString(1,vo.getMid());
            stmt.setInt(2, vo.getMno());
            stmt.setDouble(3,vo.getScore());
            stmt.setString(4,vo.getCmt());
            System.out.println(sql);

            int count = stmt.executeUpdate();

            System.out.println(count);

        }catch (Exception e){

        }finally {
            if (stmt != null){ try{stmt.close();} catch (Exception e){}
            }

        }//End final


        //Statement SQL전달
        //Statement 실행
        //close
    }

}




package org.atomsk;

import org.atomsk.domain.ReviewVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ReviewDAO {

    public void add(ReviewVO vo){
        //Connection 맺기

        Connection con = null;
        PreparedStatement stmt = null;
        String sql ="insert into tbl_review (rno,mid,mno,score,cmt) values (seq_review.nextval,?,?,?,?)";

        try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.10.95:1521:XE",
                    "zz",
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




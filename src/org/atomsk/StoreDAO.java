package org.atomsk;

import org.atomsk.domain.StoreVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    public List<StoreVO> getAll(){
        String sql = "select * from tbl_store order by sname";
        List<StoreVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; // = InputStream

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.10.95:1521:XE",
                    "zz",
                    "12345678");
            System.out.println(con);
            stmt = con.prepareStatement(sql);
            //mid, mno, score

            rs = stmt.executeQuery();

            while (rs.next()) { // 다음칸으로 넘어가면서 데이터를 받아옴
                StoreVO vo = new StoreVO(); //라인하나당 menuVo 하나씩
                vo.setSno(rs.getInt("sno"));
                vo.setLat(rs.getInt("lat"));
                vo.setLng(rs.getInt("lng"));
                vo.setSname(rs.getString("sname"));
                vo.setGubun(rs.getString("gubun"));
                vo.setImg(rs.getString("img"));
                list.add(vo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {   try{ rs.close();} catch (Exception e){}     }
            if (stmt != null) {   try{ rs.close();} catch (Exception e){}   }
            if (con != null) {   try{ rs.close();} catch (Exception e){}    }


        }
        return list;
    }

}

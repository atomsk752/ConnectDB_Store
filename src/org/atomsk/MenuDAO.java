//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.atomsk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.atomsk.domain.MenuVO;

public class MenuDAO {
    public MenuDAO() {
    }

    public List<MenuVO> getMenus(int sno) {
        String sql = "select * from tbl_menu where sno = ? order by mno";
        List<MenuVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.95:1521:XE", "zz", "12345678");
            System.out.println(con);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, sno);
            rs = stmt.executeQuery();

            while(rs.next()) {
                MenuVO vo = new MenuVO();
                vo.setMno(rs.getInt("mno"));
                vo.setSno(rs.getInt("sno"));
                vo.setMname(rs.getString("mname"));
                vo.setPrice(rs.getInt("price"));
                list.add(vo);
            }
        } catch (Exception var24) {
            System.out.println(var24.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception var23) {
                    ;
                }
            }

            if (stmt != null) {
                try {
                    rs.close();
                } catch (Exception var22) {
                    ;
                }
            }

            if (con != null) {
                try {
                    rs.close();
                } catch (Exception var21) {
                    ;
                }
            }

        }

        return list;
    }
}

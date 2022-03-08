package lesson2;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TestDelete {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs =null;


        try {
            conn = JdbcUtils.getConnection();

            String sql = "delete from users where id = ?";

            st=conn.prepareStatement(sql);

            st.setInt(1,4);

            int i = st.executeUpdate();
            if (i>0) {
                System.out.println("删除成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

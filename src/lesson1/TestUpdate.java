package lesson1;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();   //建立连接
            st = conn.createStatement();    //建立会话

            String sql = "UPDATE `users` SET `NAME`='kuangshen',`email`='419284169@qq.com' WHERE id =1";

            int i = st.executeUpdate(sql);
            if (i>0) {
                System.out.println("修改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);  //关闭连接
        }
    }
}

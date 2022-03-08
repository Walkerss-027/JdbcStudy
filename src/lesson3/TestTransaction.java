package lesson3;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交，会自动开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set money = money-100 where name = 'A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            int x = 1/0;    //万能报错

            String sql2 = "update account set money = money+100 where name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            conn.commit();
            System.out.println("成功！");
        } catch (Exception e) {
            try {
                conn.rollback();    //如果失败则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

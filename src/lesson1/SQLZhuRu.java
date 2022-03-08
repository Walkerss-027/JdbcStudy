package lesson1;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLZhuRu {
    public static void main(String[] args) {
//        login("lisi","123456");
        login("'' or 1=1","123456");   //技巧
    }


    //登录业务
    public static void login(String username,String password){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM users WHERE `NAME` = ? AND `password` = ?";
            //PreparedStatement 防注入的本质，把传递进来的参数当作字符
            //假设其中存在转义字符，比如 ' 会被直接转义
            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);

            rs = st.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }

    }
}

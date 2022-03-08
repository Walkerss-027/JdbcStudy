package lesson2;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用 ? 占位符代替数值
            String sql = "INSERT INTO users(id,`NAME`,`PASSWORD`,`email`,`birthday`) VALUES(?,?,?,?,?)";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行
            st.setInt(1,4);//id
            st.setString(2,"qinjiang");
            st.setString(3,"1232112");
            st.setString(4,"49284169@qq.com");
            //注意点：sql.Date    数据库
            //       util.Date   Java     new Date(),getTime() 获得时间戳
            st.setDate(5,new Date(new java.util.Date().getTime()));

            //执行
            int i = st.executeUpdate();
            if (i>0){
                System.out.println("插入成功!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

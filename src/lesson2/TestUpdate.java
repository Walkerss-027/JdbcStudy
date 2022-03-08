package lesson2;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "update users set `NAME` = ? where id = ?;";

            st = conn.prepareStatement(sql);

            st.setString(1, "狂神");
            st.setInt(2, 1);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("修改成功！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}

package dao;

import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MvDao {
    public int Insert(String title, String writer, String time, String url,
                      int userid) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement pst=null;
        int number = 0;
        try {
            pst=conn.prepareStatement("insert into mv(title,writer,time,url,userid) values(?,?,?,?,?)");
            pst.setString(1,title);
            pst.setString(2,writer);
            pst.setString(3,time);
            pst.setString(4,url);
            pst.setInt(5,userid);
            number=pst.executeUpdate();
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            DBUtils.getClose(conn, pst, null);
        }
        return 0;
    }
}

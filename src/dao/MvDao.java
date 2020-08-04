package dao;

import entity.MV;
import entity.Music;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MvDao {
    public int deleteMvById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "delete from mv where id=?";
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int ret = preparedStatement.executeUpdate();
            if(ret == 1) {



                    return 1;

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,preparedStatement,null);
        }
        return 0;
    }
    public MV findMvById(int id){
        MV mv = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("select * from mv where id=?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()) {
                mv = new MV();
                mv.setId(rs.getInt("id"));
                mv.setTitle(rs.getString("title"));
                mv.setWriter(rs.getString("writer"));
                mv.setTime(rs.getDate("time"));
                mv.setUrl(rs.getString("url"));
                mv.setUserid(rs.getInt("userid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtils.getClose(conn, ps, rs);
        }
        return mv;
    }
    public List<MV> ifMv(String str){
        List<MV> mvs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("select*from mv where title like '%"+str+"%'");
            rs = ps.executeQuery();
            while(rs.next()) {
                MV mv = new MV();
                mv.setId(rs.getInt("id"));
                mv.setTitle(rs.getString("title"));
                mv.setWriter(rs.getString("writer"));
                mv.setTime(rs.getDate("time"));
                mv.setUrl(rs.getString("url"));
                mv.setUserid(rs.getInt("userid"));
                mvs.add(mv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtils.getClose(conn, ps, rs);
        }
        return mvs;
    }
    public List<MV> findMv() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<MV> list = new ArrayList<>();
        String sql = "select * from mv";
        connection = DBUtils.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                MV mv = new MV();
                mv.setId(resultSet.getInt("id"));
                mv.setTitle(resultSet.getString("title"));
                mv.setWriter(resultSet.getString("writer"));
                mv.setTime(resultSet.getDate("time"));
                mv.setUrl(resultSet.getString("url"));
                mv.setUserid(resultSet.getInt("userid"));
                list.add(mv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,ps,resultSet);
        }
        return list;
    }
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

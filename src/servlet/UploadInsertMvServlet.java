package servlet;

import dao.MvDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/uploadmvsucess")
public class UploadInsertMvServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String writer =req.getParameter("writer");
        String fileName = (String)req.getSession().getAttribute("fileName");
        String[] s = fileName.split("\\.");
        String title = s[0];
        //2020-07-30
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        User user = (User)req.getSession().getAttribute("user");
        int user_id = user.getId();
        String url = "video/"+title;

        MvDao mvDao = new MvDao();
        int ret = mvDao.Insert(title,writer,time,url,user_id);

        if(ret == 1) {
            resp.sendRedirect("MV.html");
        }
    }
}

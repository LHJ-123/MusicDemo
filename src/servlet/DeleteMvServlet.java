package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import dao.MvDao;
import entity.MV;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/deleteMvServlet")
public class DeleteMvServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        MvDao mvDao = new MvDao();
        MV mv = mvDao.findMvById(id);
        if(mv == null) {
            return;
        }
        int delete = mvDao.deleteMvById(id);
        Map<String,Object> map = new HashMap<>();
        if (delete == 1) {
            File file = new File("root/java/apache-tomcat-8.5.57/webapps/MusicDemo1/"+mv.getUrl()+".mp4");
            if (file.delete()) {
                map.put("msg",true);
                System.out.println("服务器删除成功");
            }else {
                map.put("msg",false);
                System.out.println("服务器删除失败");
            }

        }else {
            map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}

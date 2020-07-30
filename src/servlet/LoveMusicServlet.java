package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loveMusicServlet")
public class LoveMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String id = req.getParameter("id");
        int musicId = Integer.parseInt(id);
        User user = (User)req.getSession().getAttribute("user");
        int userId = user.getId();
        MusicDao musicDao = new MusicDao();
        Map<String,Object> map = new HashMap<>();
        boolean cur = musicDao.findMusicByMusicId(userId,musicId);
        if (cur) {
            map.put("msg",false);
        }else {
            boolean cur1 = musicDao.insertLoveMusic(userId,musicId);
            if (cur1) {
                map.put("msg",true);
            }else {
                map.put("msg",false);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);

    }
}

package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        Map<String,Object> map = new HashMap<>();
        if (user != null) {
            System.out.println("登录成功");
            req.getSession().setAttribute("user",user);
            map.put("msg",true);
        }else {
            System.out.println("登录失败");
            map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}

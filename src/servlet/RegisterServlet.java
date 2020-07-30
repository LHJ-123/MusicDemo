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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<>();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String ageStr = req.getParameter("age");
        int age = Integer.parseInt(ageStr);
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");

        UserDao userDao = new UserDao();
        User user = userDao.selectUserByName(username);




        if(user != null) {
            System.out.println("用户名已经存在");
            map.put("msg",false);
        }else {
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(password);
            user1.setAge(age);
            user1.setGender(gender);
            user1.setEmail(email);
           userDao.register(user1);
           map.put("msg",true);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }
}

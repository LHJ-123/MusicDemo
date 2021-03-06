package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MvDao;
import entity.MV;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findMv")

public class FindMvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String mvName = req.getParameter("mvName");
        MvDao mvDao = new MvDao();
        List<MV> mvList = new ArrayList<>();
        if (mvName != null) {
            mvList = mvDao.ifMv(mvName);
        }else {
            mvList = mvDao.findMv();
        }
        for (MV mv : mvList) {
            System.out.println(mv.getUrl());
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),mvList);
    }
}

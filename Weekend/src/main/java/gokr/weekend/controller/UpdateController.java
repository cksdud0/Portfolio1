package gokr.weekend.controller;

import gokr.weekend.dao.WebzineDAO;
import gokr.weekend.dto.WebzineDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // View.jsp에서 전달되는 파라미터 이름에 맞게 수정
        String wno = request.getParameter("wno");
        String wtitle = request.getParameter("wtitle");
        String wtext = request.getParameter("wtext");

        // 콘솔 디버깅
        System.out.println("wno: " + wno);
        System.out.println("wtitle: " + wtitle);
        System.out.println("wtext: " + wtext);

        // DTO 세팅
        WebzineDTO dto = new WebzineDTO();
        dto.setWno(wno);
        dto.setWtitle(wtitle);
        dto.setWtext(wtext);

        // DAO 호출
        WebzineDAO dao = new WebzineDAO();
        int result = dao.updateArticle(dto);
        dao.close();

        if (result > 0) {
            response.sendRedirect("View.jsp?wno=" + wno);  // 수정 성공 시 다시 보기 페이지로
        } else {
            response.getWriter().println("<h3>수정 실패</h3>");
        }
    }
}
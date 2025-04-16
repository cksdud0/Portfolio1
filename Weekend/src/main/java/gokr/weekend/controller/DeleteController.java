package gokr.weekend.controller;

import gokr.weekend.dao.WebzineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String wno = request.getParameter("wno");

        // 콘솔로 확인
        System.out.println("삭제할 wno: " + wno);

        WebzineDAO dao = new WebzineDAO();
        int result = dao.deleteArticle(wno);
        dao.close();

        if (result > 0) {
            // 삭제 성공 시 목록 페이지나 홈으로 이동
            response.sendRedirect("List.jsp"); // ← 적절한 목록 페이지 경로로 수정하세요
        } else {
            response.getWriter().println("<h3>삭제 실패</h3>");
        }
    }
}
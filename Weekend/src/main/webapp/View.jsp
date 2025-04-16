<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="gokr.weekend.dao.WebzineDAO" %>
<%@ page import="gokr.weekend.dto.WebzineDTO" %>

<%
    request.setCharacterEncoding("UTF-8");

    String wno = request.getParameter("wno");  // int로 바꾸지 않고 그대로 String 유지
    if (wno == null || wno.trim().equals("")) {
        out.println("<h3>잘못된 접근입니다.</h3>");
        return;
    }

    WebzineDAO dao = new WebzineDAO();
    WebzineDTO article = dao.selectArticle(wno);  // DAO의 String 버전 메서드 사용
    dao.close();

    if (article.getWtitle() == null) {
        out.println("<h3>해당 기사를 찾을 수 없습니다.</h3>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title><%= article.getWtitle() %></title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/news.css">
    <script>
        function enableEditMode() {
            document.getElementById('titleText').style.display = 'none';
            document.getElementById('contentText').style.display = 'none';
            document.getElementById('editForm').style.display = 'block';
            document.getElementById('editButtons').style.display = 'none';
        }
    </script>
</head>
<body>
    <div class="site_title">WEEKEND</div>

    <nav id="header">
        <li class="nav nav_logo"><a href="lk.jsp"></a></li>
        <div class="nav_word">WEEKEND</div>
        <ul class="nav_item">
            <li class="nav nav_li"><a href="#">웹진</a></li>
            <li class="nav nav_li"><a href="#">커뮤니티</a></li>
        </ul>
        <button class="nav_loginbutton"><a href="#">로그인/회원가입</a></button>
    </nav>
        <div class="photo_container">
            <div class="photo">
                <img src="<%= article.getWsfile() != null ? article.getWsfile() : "img/p1.jpg" %>" alt="기사 이미지">
            </div>
        </div>

    <div class="article-content">
        <!-- 읽기 모드 -->
        <h2 id="titleText"><%= article.getWtitle() %></h2>
        <p id="contentText"><%= article.getWtext().replaceAll("\n", "<br>") %></p>

        <!-- 수정 모드 (처음에는 숨김) -->
        <form id="editForm" method="post" action="UpdateController" style="display:none;">
            <input type="hidden" name="wno" value="<%= article.getWno() %>">
            제목: <input type="text" name="wtitle" value="<%= article.getWtitle() %>"><br><br>
            내용: <textarea name="wtext" rows="5" cols="40"><%= article.getWtext() %></textarea><br><br>
            <button type="submit">저장</button>
        </form>

        <div class="article-info">
            조회수: <%= article.getWviewcount() != null ? article.getWviewcount().toString() : "0" %>
            <div id="editButtons">
                <button type="button" onclick="enableEditMode()">수정</button>
                <form method="post" action="DeleteController" style="display:inline;">
                    <input type="hidden" name="wno" value="<%= article.getWno() %>">
                    <button type="submit" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                </form>
            </div>
        </div>

    </div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="gokr.weekend.dao.TestArticleDAO, gokr.weekend.dto.TestArticleDTO" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    TestArticleDAO dao = new TestArticleDAO();
    TestArticleDTO dto = dao.selectArticle(id);
%>
<html>
<head><title>글 수정</title></head>
<body>
<h2>글 수정</h2>
<form method="post" action="UpdateController">
    <input type="hidden" name="id" value="<%= dto.getId() %>">
    제목: <input type="text" name="title" value="<%= dto.getTitle() %>"><br>
    내용: <textarea name="content"><%= dto.getContent() %></textarea><br>
    <button type="submit">수정</button>
</form>
</body>
</html>
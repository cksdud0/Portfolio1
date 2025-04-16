<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<style>a{text-decoration:none;}</style>

</head>
<body>
	<h2>최신 기사 페이지</h2>
	
	<!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
            <th width="8%">첨부</th>
        </tr>
<c:choose>    
    <c:when test="${ empty boardLists }">  <!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다.
            </td>
        </tr>
    </c:when>
    <c:otherwise>  <!-- 게시물이 있을 때 -->
        <c:forEach items="${ boardLists }" var="row" varStatus="loop">    
        <tr align="center">
            <td>  <!-- 번호 -->
                ${ row.cno }   
            </td>
            <td align="left">  <!-- 제목(링크) -->
                <a href="/view.do?cno=${ row.cno }">${ row.ctitle }</a> 
            </td> 
            <td>${ row.cwuser }</td>  <!-- 작성자 -->
            <td>${ row.cviewcount }</td>  <!-- 조회수 -->
            <td>${ row.cwdate }</td>  <!-- 작성일 -->
            <td>  <!-- 첨부 파일 -->
            <c:if test="${ not empty row.cofile }">
                <a href="/download.do?ofile=${ row.cofile }&sfile=${ row.csfile }&idx=${ row.cno }">[Down]</a>
            </c:if>
            </td>
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose> 
    </table>
    
</body>
</html>
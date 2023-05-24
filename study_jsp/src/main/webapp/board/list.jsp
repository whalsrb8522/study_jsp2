<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board List Page</h1>

	<table border="1" style="border-collapse: collapse;">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bvo" items="${list }" varStatus="status">
				<tr onclick="location.href='/brd/detail?bno=${bvo.bno }'">
					<td>${bvo.bno}</td>
					<td>${bvo.title }</td>
					<td>${bvo.writer }</td>
					<td>${bvo.regdate }</td>
					<td>${bvo.readcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${pgh.prev }">
		<a href="/brd/page?pageNo=${pgh.startPage - 1 }&qty=${pgh.pgvo.qty}"> ◁ </a>
	</c:if>
	
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}">${i } | </a>
	</c:forEach>
	
	<c:if test="${pgh.next }">
		<a href="/brd/page?pageNo=${pgh.endPage + 1 }&qty=${pgh.pgvo.qty}"> ▷ </a>
	</c:if>
	
	<br>
	
	<c:if test="${ses.id ne null }">
		<a href="/brd/register_s1"><button>글쓰기</button></a>
	</c:if>
	<a href="/index.jsp"><button>메인 페이지로</button></a>
</body>
</html>
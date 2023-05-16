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
			<c:forEach var="bvo" items="${list }">
				<tr onclick="location.href='/brd/detail?bno=${bvo.bno }'">
					<td>${bvo.bno }</td>
					<td>${bvo.title }</td>
					<td>${bvo.writer }</td>
					<td>${bvo.regdate }</td>
					<td>${bvo.readcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${ses.id ne null }">
		<a href="/brd/register_s1"><button>글쓰기</button></a>
	</c:if>
	<a href="/index.jsp"><button>메인 페이지로</button></a>
</body>
</html>
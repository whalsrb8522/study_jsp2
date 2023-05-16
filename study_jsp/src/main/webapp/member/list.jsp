<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List page</title>
</head>
<style>
	td {
		padding: 3px 20px;
	}
</style>
<body>
	<h1>List Page</h1>

	<table border="1" style="border-collapse: collapse;">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="mvo" items="${list }">
				<tr onclick="location.href='/mem/detail?id=${mvo.id }'">	
					<td>${mvo.id }</td>
					<td>${mvo.name }</td>
					<td>
						<f:parseDate value="${mvo.regdate }" var="regdate" pattern="yyyy-MM-dd HH:mm:ss"/>
						<f:formatDate value="${regdate}" pattern="yy.MM.dd HH:mm"/>
					</td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/index.jsp"><button>메인 페이지로</button></a>
</body>
</html>

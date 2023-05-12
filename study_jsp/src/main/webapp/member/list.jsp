<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자 페이지 (회원 목록)</h1>

	<table border="1" style="border-collapse: collapse;">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>가입일</td>
		</tr>
		<c:forEach var="mvo" items="${list }">
			<tr>
				<td>${mvo.id }</td>
				<td>${mvo.name }</td>
				<td>${mvo.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/index.jsp"><button>메인 페이지로</button></a>
</body>
</html>
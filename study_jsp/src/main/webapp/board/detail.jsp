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
	<h1>Detail Page</h1>
	
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<th>글번호</th>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${bvo.regdate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bvo.readcount}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${bvo.content}</td>
		</tr>
	</table>
	<c:if test="${ses.id  eq bvo.writer}">
		<a href="/brd/modify_s1?bno=${bvo.bno }"><button type="button">modify</button></a>
		<a href="brd/delete?bno=${bvo.bno }"><button type="button">delete</button></a>
	</c:if>
	<a href="/brd/list"><button type="button">list</button></a>
</body>
</html>
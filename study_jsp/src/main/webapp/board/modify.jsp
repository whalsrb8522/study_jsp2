<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Modify Page</h1>
	
	<form action="/brd/modify_s2">
		<input type="text" name="bno" value="${bvo.bno }" hidden="">
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>글번호</th>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${bvo.title }"></td>
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
				<th>내용</th>
				<td><textarea name="content" rows="" cols="">${bvo.content }</textarea>
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<a href="brd/delete?bno=${bvo.bno }"><button type="button">삭제</button></a>
		<a href="/brd/detail?bno=${bvo.bno }"><button type="button">취소</button></a>		
	</form>

</body>
</html>
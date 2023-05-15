<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	
	<form action="/mem/sign_in_s2" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		
		<button type="submit">로그인</button>
		<a href="/index.jsp"><button type="button">취소</button></a>
	</form>
</body>
</html>
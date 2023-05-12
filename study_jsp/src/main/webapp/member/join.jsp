<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
</head>
<body>
	<h1>Join Page</h1>

	<form action="/mem/register" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>phone</td>
				<td><input type="text" name="phone" placeholder="숫자만 입력해주세요."></td>
			</tr>
		</table>
		
		<button type="submit">가입완료</button>
		<a href="/index.jsp"><button type="button">취소</button></a>
	</form>
</body>
</html>
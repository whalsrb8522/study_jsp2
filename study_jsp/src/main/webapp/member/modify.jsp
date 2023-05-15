<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
</head>
<body>
	<form action="/mem/modify_s2" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" value="${mvo.id }" readonly></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="name" value="${mvo.name }"></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="email" value="${mvo.email }"></td>
			</tr>
			<tr>
				<td>phone</td>
				<td><input type="text" name="phone" placeholder="숫자만 입력해주세요." value="${mvo.phone }"></td>
			</tr>
			<tr>
				<td>regdate</td>
				<td><input type="text" name="regdate" value="${mvo.regdate }"></td>
			</tr>
			<tr>
				<td>lastlogin</td>
				<td><input type="text" name="lastlogin" value="${mvo.lastlogin}"></td>
			</tr>
		</table>
		
		<button type="submit">회원수정</button>
		<a href="/mem/delete?id=${mvo.id }"><button type="button">회원탈퇴</button></a>
		<a href="/index.jsp"><button type="button">취소</button></a>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<body>
	<h1>Index Page</h1>
	
	<!-- 비교 표현식, eq : equals, ne : not equals -->
	<c:choose>
		<c:when test="${ses.id ne null }">
			${ses.name }님 안녕하세요. <br>
			계정 생성일 : ${ses.regdate } <br>
			마지막 접속 : ${ses.lastlogin }<br>
			<a href="/mem/logout"><button class="btn btn-sm btn-primary">logout</button></a>		
			<a href="/mem/modify_s1"><button class="btn btn-sm btn-primary">modify</button></a>
			<c:if test="${ses.auth }">
				<a href="/mem/list"><button class="btn btn-sm btn-primary">list</button></a>
			</c:if>
		</c:when>
		<c:when test="${ses.id eq null }">
			<a href="/mem/sign_in_s1"><button class="btn btn-sm btn-primary">Sign in</button></a>
			<a href="/mem/sign_up_s1"><button class="btn btn-sm btn-primary">Sign up</button></a>
		</c:when>
	</c:choose>
	
	<a href="/brd/page"><button class="btn btn-sm btn-primary">board</button></a>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<script>
		const msg_login = `<c:out value="${msg_login }" />`;
		const msg_delete = `<c:out value="${msg_delete }" />`;
		
		console.log(msg_login);
		console.log(msg_delete);
		
		if (msg_login === '0') {
			alert("로그인 실패");
		}
		
		if (msg_delete === '0') {
			alert("회원 삭제 실패");
		} else if (msg_delete === '1') {
			alert("회원 삭제 성공");
		}
	</script>
</body>
</html>

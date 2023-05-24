<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<h1>Board List Page</h1>
	
	<!-- 검색 라인 -->
	<form action="/brd/page">
		<div>
			<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
			<select name="type">
				<option value="t" ${typed eq 't' ? 'selected':''}>title</option>
				<option value="c" ${typed eq 'c' ? 'selected':''}>content</option>
				<option value="w" ${typed eq 'w' ? 'selected':''}>writer</option>
				<option value="tc" ${typed eq 'tc' ? 'selected':''}>title + content</option>
				<option value="tw" ${typed eq 'tw' ? 'selected':''}>title + writer</option>
				<option value="cw" ${typed eq 'cw' ? 'selected':''}>content + writer</option> 
			</select>
			<input type="text" name="keyword" placeholder="Search...">
			<input type="hidden" name=pageNo value="${pgh.pgvo.pageNo}">
			<input type="hidden" name=qty value="${pgh.pgvo.qty}">
 			<button type="submit" class="btn btn-sm btn-primary position-relative">
 				SEARCH
 					<c:if test="${pgh.pgvo.keyword != null && pgh.pgvo.keyword != '' }">
		 				<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
		 					${pgh.totalCount }
		    				<span class="visually-hidden">unread messages</span>
		  				</span>
	  				</c:if>
 			</button>
		</div>
	</form>	

	<table class="table">
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
		<a href="/brd/page?pageNo=${pgh.startPage - 1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ◁ </a>
	</c:if>
	
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
	</c:forEach>
	
	<c:if test="${pgh.next }">
		<a href="/brd/page?pageNo=${pgh.endPage + 1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
	</c:if>
	
	<br>
	
	
	<c:if test="${ses.id ne null }">
		<input type="button" class="btn btn-sm btn-primary"	onclick="location.href='/brd/page?type=w&keyword=${ses.id}'" value="내가 쓴 글">
		<a href="/brd/register_s1"><button class="btn btn-sm btn-primary">글쓰기</button></a>
	</c:if>
	<a href="/index.jsp"><button class="btn btn-sm btn-primary">메인 페이지로</button></a>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
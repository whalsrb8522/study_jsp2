<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>

	<h1>Detail Page</h1>
	
	<table class="table table-bordered">
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
			<td>
				<c:if test="${bvo.image ne '' && bvo.image ne null }">
					<img alt="" src="/_fileUpload/${bvo.image }">
				</c:if>
				${bvo.content}
			</td>
		</tr>
	</table>
	<c:if test="${ses.id  eq bvo.writer}">
		<a href="/brd/modify_s1?bno=${bvo.bno }"><button type="button" class="btn btn-sm btn-primary">modify</button></a>
		<a href="brd/delete?bno=${bvo.bno }"><button type="button" class="btn btn-sm btn-primary">delete</button></a>
	</c:if>
	<a href="/brd/page"><button type="button" class="btn btn-sm btn-primary">list</button></a>
	
	<!-- 댓글 -->
	<!-- ㄴ댓글 수정 (수정/삭제 버튼을 댓글 뒤쪽에 표시 -->
	
	<!-- ㄴ댓글 등록 -->
	<c:if test="${ses.id ne null }">
		<div>
			<h3>Comment Line</h3> <br>
			<input type="text" id="cmtWriter" value="${ses.id }" readonly="readonly">
			<input type="text" id="cmtText" placeholder="Add comment">
			<button type="button" id="cmtAddBtn" class="btn btn-sm btn-primary">댓글등록</button>
		</div>
	</c:if>
	
	<!-- ㄴ댓글 표시 (list) -->
	<div class="accordion" id="accordionExample">
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					cno, writer
				</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
				<div class="accordion-body">
					content, regdate					
				</div>
			</div>
		</div>
	</div>
	
    <script type="text/javascript">
    	const bnoVal = `<c:out value="${bvo.bno }" />`;
    </script>
    <script type="text/javascript" src="/resources/board_detail.js"></script>
    <script type="text/javascript">
    	printCommentLlist(bnoVal);
    </script>
</body>
</html>
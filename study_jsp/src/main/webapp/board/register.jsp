<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board register page</h1>
	
	<form action="/brd/register_s2" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>title</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>writer</td>
					<td><input type="text" name ="writer" value="${ses.id }" readonly="readonly"></td>
				</tr>
				<tr>
					<td>content</td>
					<td><textarea rows="" cols="" name="content"></textarea></td>
				</tr>
				<tr>
					<td>imageType</td>
					<td>
						<input type="file" id="file" name="image" 
						accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif">
					</td>
				</tr>
			</tbody>
		</table>
		<button type="submit">register</button>
		<a href="/brd/page"><button type="button">취소</button></a>
	</form>
</body>
</html>
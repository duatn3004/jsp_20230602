<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 등록 페이지</h1>
<form action="/brd/insert" method="post" enctype="multipart/form-data">
제목 : <input type="text" name="title"><br><br>
작성자 : <input type="text" name="writer" value="${ses.id }" readonly="readonly"><br><br>
내용 : <textarea rows="3" cols="30" name="content"></textarea><br><br>
첨부파일 : <input type="file" id="file" name="image_file" accept="
image/png, image/jpg, image/jpeg, image/bmp, image/gif">
<button type="submit">등록</button>
</form>
</body>
</html>
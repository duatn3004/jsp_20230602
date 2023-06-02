<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>Product Detail Page</h1>
	<table class="table table-striped">
	
		<tr>
			<th>번호</th>
			<td>${pvo.pno }</td>
		</tr>	
		<tr>
			<th>상품명</th>
			<td>${pvo.pname }</td>
		</tr>	
		<tr>
			<th>가격</th>
			<td>${pvo.price }</td>
		</tr>	
		<tr>
			<th>등록일자</th>
			<td>${pvo.regdate }</td>
		</tr>	
		<tr>
			<th>세부내용</th>
			<td>${pvo.madeby }</td>
		</tr>	
	

	</table>
	<a href="modify.pd?pno=${pvo.pno }"><button type="button">modify</button></a>
	<a href="remove.pd?pno=${pvo.pno }"><button type="button">delete</button></a>
</body>
</html>
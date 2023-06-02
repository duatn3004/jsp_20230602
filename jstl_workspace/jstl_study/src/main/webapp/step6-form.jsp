<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- get방식 : 쿼리스트림을 달고 URL상에서 이동하는 방식(기본) -->
<!-- post방식 : 데이터를 숨겨서 가는 방식 보안상, 내용이 많을 경우 -->
	<form action="step6-action.jsp" method="post">
	주문자 : <input type="text" value="이름" name="name"><br>
	<input type="checkbox" name="food" value="삼겹살">삼겹살<br>
	<input type="checkbox" name="food" value="짜장면">짜장면<br>
	<input type="checkbox" name="food" value="설렁탕">설렁탕<br>
	<input type="checkbox" name="food" value="카레">카레<br>
	<input type="checkbox" name="food" value="연어덮밥">연어덮밥<br>
	<button type="submit">전송</button>
	</form>
</body>
</html>
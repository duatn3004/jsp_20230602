<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//post 방식의 한글처리 (인코딩 설정)
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
	String food[] = {"삼겹살","짜장면","설렁탕","카레","연어덮밥"};
	request.setAttribute("f", food);
	--%>
	
	주문자 : ${param.name }
	<hr>
	<c:forEach items="${paramValues.food }" var="fname" varStatus="order">
	${order.count }.${fname }<br>
	</c:forEach>
	주문하신 메뉴 나왔습니다.
	
	
	
	
</body>
</html>
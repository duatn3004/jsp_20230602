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
	<%
		String food[] = {"사과","배","귤","감","바나나"};
		request.setAttribute("f", food);
	%>
	
	<!--  forEach : 반복문
		items : 대상배열, collection
		var : 요소를 저장할 변수
		varStatus : 변수 => 변수.count(개수), 변수.index(주소)
	 -->
	 <c:forEach items="${requestScope.f }" var="fname" varStatus="order">
	 	count : ${order.count }
	 	index : ${order.index }
	 	${fname }<br>
	 </c:forEach>
</body>
</html>
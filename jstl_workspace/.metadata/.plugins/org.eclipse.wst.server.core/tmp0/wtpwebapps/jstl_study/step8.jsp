<%@page import="model.personVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
	ArrayList<personVO> list = new ArrayList<personVO>();
	list.add(new personVO("정은", 26));
	list.add(new personVO("수정", 25));
	list.add(new personVO("길순", 35));
	list.add(new personVO("길동", 15));
	list.add(new personVO("애기", 5));
	request.setAttribute("list", list);
	%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>연령대</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list }" var="listname" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${listname.name }</td>
				<td>${listname.age }</td>
				<td>
				${(listname.age >=20)? "성인":"미성년"}
				</td>
			</tr>

			</c:forEach>
		</tbody>
	</table>
</body>
</html>
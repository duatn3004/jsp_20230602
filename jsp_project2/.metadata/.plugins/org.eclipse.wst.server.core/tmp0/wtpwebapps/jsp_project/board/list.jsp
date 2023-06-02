<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<style>
body {
	background-color: #FDE8E7;
}
</style>
<body>
	<h1>Board List Page</h1>
	<!-- 검색라인 -->
	<form action="/brd/page" method="post">
		<div>
			<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
			<select name="type">
			<!-- selected : 현재 내가 선택한 값 -->
				<option ${typed == null ? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't' ? 'selected':'' }>title</option>
				<option value="c" ${typed eq 'c' ? 'selected':'' }>content</option>
				<option value="w" ${typed eq 'w' ? 'selected':'' }>writer</option>
				<option value="tc" ${typed eq 'tc' ? 'selected':'' }>title or content</option>
				<option value="tw" ${typed eq 'tw' ? 'selected':'' }>title or writer</option>
				<option value="cw" ${typed eq 'cw' ? 'selected':'' }>content or writer</option>
			</select>
			<input type="text" name="keyword" placeholder="Search">
			<input type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }">
			<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
			<button type="submit">Search</button>
		</div>
	</form>


	<table class="table table-striped">
		<thead>
			<tr>
				<th>bno</th>
				<th>title</th>
				<th>writer</th>
				<th>reg_date</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="list">
				<tr>
					<td>${list.bno }</td>
					<td>
					<c:if test="${list.image_file ne null}">
						<img alt="없음" src="/_fileUpload/th_${list.image_file }">
					</c:if>
					<a href="/brd/detail?bno=${list.bno }">${list.title }</a>
					</td>
					<td>${list.writer }</td>
					<td>${list.reg_date }</td>
					<td>${list.cnt }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/"><button>index</button></a>
	<br>
	<!-- 페이지네이션 위치 -->
	<!-- 컨트롤러에서 page 정보를 싣고 와야함. -->
	<!-- 1~endpage까지 숫자 반복 foreach -->

	<!-- 이전페이지 -->
	<c:if test="${pgh.prev }">
		<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">◁</a>
	</c:if>
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
	</c:forEach>

	<!-- 다음페이지 -->
	<c:if test="${pgh.next }">
		<a href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">▷</a>
	</c:if>
</body>
</html>
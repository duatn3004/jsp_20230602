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
<body>
	<h1>게시판 페이지</h1>
	<!-- 검색라인 -->
	<form action="/brd/page" method="post">
		<div>
			<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
			<select name="type">
			<!-- selected : 현재 내가 선택한 값 -->
			<option ${typed == null ? 'selected':'' }>선택</option>
			<option value="t" ${typed eq 't' ? 'selected':'' }>제목</option>
			<option value="c" ${typed eq 'c' ? 'selected':'' }>내용</option>
			<option value="w" ${typed eq 'w' ? 'selected':'' }>작성자</option>
			<option value="tc" ${typed eq 'tc' ? 'selected':'' }>제목/내용</option>
			<option value="tw" ${typed eq 'tw' ? 'selected':'' }>제목/작성자</option>
			<option value="cw" ${typed eq 'cw' ? 'selected':'' }>내용/작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색">
			<input type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }">
			<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
			<button type="submit">검색</button>
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
					<td><a href="/brd/detail?bno=${list.bno }">${list.title }</a></td>
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
	<!-- 컨트롤러에서 page 정보를 싣고 와야함 -->
	<!-- 1~endpage까지 숫자 반복 foreach -->

	<!-- 이전페이지 -->
	<c:if test="${pgh.prev }">
		<a
			href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">◀</a>
	</c:if>
	<!-- 페이징/검색 -->
	<c:forEach begin="${pgh.startPage}" end="${pgh.endPage }" var="i">
		<a
			href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i }
			| </a>
	</c:forEach>
	<!-- 다음페이지 -->
	<c:if test="${pgh.next }">
		<a
			href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">▶</a>
	</c:if>
</body>
</html>
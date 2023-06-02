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
	<h1>Detail Page</h1>
	<div>
	<img alt="없음" src="/_fileUpload/${bvo.image_file }">
	</div>
	
	
	<table class="table table-striped">
		<tbody>
			<tr>
				<th>번호</th>
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
				<th>내용</th>
				<td>${bvo.content }</td>
			</tr>		
			<tr>
				<th>등록일</th>
				<td>${bvo.reg_date }</td>
			</tr>		
			<tr>
				<th>조회수</th>
				<td>${bvo.cnt }</td>
			</tr>		
		
		</tbody>

	</table>
	<a href="/brd/modify?bno=${bvo.bno }"><button type="button">수정</button></a>
	<a href="/brd/remove?bno=${bvo.bno }&image_file=${bvo.image_file}"><button type="button">삭제</button></a>
	<a href="/brd/page?bno=${bvo.bno }"><button type="button">리스트</button></a>

<div>
		<br>
		<h3>comment line</h3>
		<!-- 댓글 작성 라인 -->
		<input type="text" id="cmtWriter" value="${ses.id }"
			readonly="readonly"><br> <input type="text" id="cmtText"
			placeholder="Add Comment">
		<button type="button" id="cmtAddBtn">댓글등록</button>
	</div>
	<br>

	<!-- 댓글 표시 라인 -->
	<div class="accordion" id="accordionExample">
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<button class="accordion-button" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapseOne"
					aria-expanded="true" aria-controls="collapseOne">cno,
					wirter</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show"
				aria-labelledby="headingOne" data-bs-parent="#accordionExample">
				<div class="accordion-body">content,reg_date</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`;
	</script>
	<script src="/resources/board_detail.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>

</body>
</html>
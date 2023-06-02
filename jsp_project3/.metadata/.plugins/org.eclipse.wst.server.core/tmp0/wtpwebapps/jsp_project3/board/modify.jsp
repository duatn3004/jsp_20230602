<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	function showConfirmation() {
		alert('수정이 완료되었습니다.');
	}
</script>

</head>
<body>
<h1>Modify Page</h1>
	<form action="/brd/edit" method="post">
		bno : <input type="text" name="bno" value="${bvo.bno }"
			readonly="readonly"><br> title : <input type="text"
			name="title" value="${bvo.title }"><br> writer : <input
			type="text" name="writer" value="${ses.id }" readonly="readonly"><br>
		content :
		<textarea rows="3" cols="30" name="content">${bvo.content }</textarea>
		<br><br>
		<button type="submit" onclick="showConfirmation()">등록</button>
	</form>
</body>
</html>
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
	<form action="/brd/edit" method="post" enctype="multipart/form-data">
		bno : <input type="text" name="bno" value="${bvo.bno }"readonly="readonly"><br> 
		title : <input type="text"name="title" value="${bvo.title }"><br> 
		writer : <input type="text" name="writer" value="${ses.id }" readonly="readonly"><br>
		content :<textarea rows="3" cols="30" name="content">${bvo.content }</textarea>
		image_file : <img alt="없음" src="/_fileUpload/th_${bvo.image_file }"> 
		<input type="hidden" name="image_file" value="${bvo.image_file }">
		<input type="file" name="new_file">
		<br>
		<button type="submit" onclick="showConfirmation()">등록</button>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="edit.pd" method="post"><br>
		<input type="hidden" name="pno" value="${pvo.pno }" >
		상품번호 : <input type="text" name="pno" value="${pvo.pno }" disabled="disabled"><br> 
		상품명 : <input type="text" name="pname" value="${pvo.pname }"><br>
		가격 : <input type="text" name="price" value="${pvo.price }"><br>
		등록일 : <input type="text" name="regdate" value="${pvo.regdate }" disabled="disabled"><br>
		세부내용 : <input type="text" name="madeby" value="${pvo.madeby }"><br>
		
		<button type="submit">완료</button>
	</form>
</body>
</html>
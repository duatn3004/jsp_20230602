<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify page</title>
</head>
<body>
<h1>modify page</h1>
	<form action="/mem/edit" method="post">
	id : <input type="text" name="id" value="${ses.id}" placeholder="ID" readonly="readonly"> <br>
		password : <input type="password" name="password" value="${ses.password}"> <br>
		email : <input type="text" name="email" value="${ses.email}" placeholder="123@naver.com"><br> 
		age : <input type="text" name="age" value="${ses.age}"><br>
		<input type="hidden" name="reg_date" value="${ses.reg_date }"><br>
		<input type="hidden" name="last_login" value="${ses.last_login }">
		<button type="submit">수정완료</button>
	</form>
</body>
</html>
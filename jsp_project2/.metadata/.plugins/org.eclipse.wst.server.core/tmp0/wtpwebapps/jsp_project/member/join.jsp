<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
</head>
<body>
<h1>Join Page</h1>
<form action="/mem/register" method="post">
		id : <input type="text" name="id" placeholder="ID"> <br>
		password : <input type="password" name="password"> <br>
		email : <input type="text" name="email" placeholder="123@naver.com"> <br>
		age : <input type="text" name="age"><br>
		<button type="submit">가입완료</button>
	</form>
</body>
</html>
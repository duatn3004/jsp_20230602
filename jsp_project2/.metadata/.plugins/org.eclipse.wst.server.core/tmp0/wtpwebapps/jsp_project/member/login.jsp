<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h1>LOGIN PAGE</h1>
	<form action="/mem/login_auth" method="post">
		ID : <input type="text" name="id"><br>
		<br> PW : <input type="text" name="password"><br>
		<br>
		<button type="submit">login</button>
	</form>
</body>
</html>
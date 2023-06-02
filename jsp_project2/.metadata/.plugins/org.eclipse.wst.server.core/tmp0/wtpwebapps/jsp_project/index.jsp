<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<style>
	body {
		background-color: #FDE8E7;
		text-align: center;
	}
</style>
<body>
	<img alt="헬로티비" src="/image/hellokitty_v1.png" >
	<h1>INDEX PAGE</h1>
	<c:if test="${ses.id ne null }">
	<b>${ses.id }</b>님이 login 하였습니다.<br>
	계정생성일 : ${ses.reg_date }<br>
	마지막 접속 : ${ses.last_login }<br><br>
		<a href="/mem/modify"><button>회원정보수정</button></a>
		<a href="/mem/delete"><button>회원탈퇴</button></a>
		<a href="/mem/logout"><button>logout</button></a>
		
		<br>
		<a href="/brd/register"><button>게시글 작성</button></a>
		<a href="/brd/page"><button>게시글 리스트</button></a>
	</c:if>
	<c:if test="${ses.id eq null }">
		<a href="/mem/login"><button>login</button></a>
	</c:if>
	<a href="/mem/join"><button>join</button></a>
	<a href="/mem/list"><button>list</button></a>


</body>

<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}" />`;
	console.log(msg_login);
	if (msg_login === '0') {
		alert('로그인 정보가 올바르지 않습니다.');
	}
</script>
</html>
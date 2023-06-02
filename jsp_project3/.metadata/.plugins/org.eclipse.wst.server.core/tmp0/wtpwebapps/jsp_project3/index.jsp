<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE</title>
</head>
<body>
	<h1>INDEX PAGE</h1>
	<c:if test="${ses.id ne null }">
	${ses.id }님이 login 하였습니다.<br>
	계정생성일 : ${ses.reg_date }<br>
	마지막접속 : ${ses.last_login }<br>	<br>
		<a href="/mem/logout"><button>로그아웃</button></a>
		<a href="/mem/list"><button>아이디 리스트</button></a>
		<a href="/mem/modify"><button>회원정보수정</button></a>
		<a href="/mem/delete"><button>회원탈퇴</button></a>
		<br><br>
		<a href="/brd/register"><button>게시글 작성</button></a>
		<a href="/brd/page"><button>게시글 리스트</button></a>

	</c:if>
	<c:if test="${ses.id eq null }">
		<a href="/mem/login"><button>로그인</button></a>
		<a href="/mem/join"><button>회원가입</button></a>
	</c:if>
</body>

<script type="text/javascript">
const msg_login = `<c:out value="${msg_login}" />`;
console.log(msg_login);
if(msg_login == '0'){
	alert('로그인 정보가 올바르지 않습니다.');
}
</script>
</html>
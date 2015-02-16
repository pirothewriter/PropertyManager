<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
</head>
<script type="text/javascript">
	function doLogin() {
		if(frm.j_username.value == "") {
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(frm.j_password.value == "") {
			alert("패스워드를 입력해주세요");
			return false;
		}
		
		frm.submit();
	}
</script>
<body>
<div id="header">
	티켓몬스터 자산관리 시스템입니다.
</div>
<div id="login_form">
	<form id="login" name="frm" action="j_spring_security_check" method="post">
		아이디 : <input type="text" id="username" class="username" name="j_username"><br>
		비밀번호 : <input type="password" id="password" class="password" name="j_password"><br>
		<input type="submit" onClick="doLogin()" value="로그인">
	</form>
</div>
</body>
</html>

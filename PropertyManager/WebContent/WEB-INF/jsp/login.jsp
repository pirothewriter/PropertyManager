<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--jquery 라이브러리 들여오기  -->
<script src="/js/jquery-1.10.2.js"></script>
<!-- jquery ui css -->
<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">

<decorator:head />
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
<div align="center">
<h1>티몬 자산관리 시스템</h1>
<div style="top:250px;width:500px;" id="login_form">
	<form id="login" class="form" name="frm" action="j_spring_security_check" method="post">
		<p align="left">아이디</p><input class="form-control" type="text" id="username" class="username" name="j_username"><br>
		<p align="left">비밀번호</p><input class="form-control" type="password" id="password" class="password" name="j_password"><br>
		<p align="right"><input type="submit" class="btn btn-primary"onClick="doLogin()" value="로그인"></p>
	</form>
	<p align="left" style="color: red;'">* 최초 비밀번호는 AD 계정명과 동일합니다</p>
</div>
</div>
</body>
</html>

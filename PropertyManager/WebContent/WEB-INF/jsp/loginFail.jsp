<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인 실패!</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		var isClicked = alert("아이디나 비밀번호가 잘못되었습니다!\n다시 시도해주세요!");
		if(isClicked == true)
			document.location.href="/login.tmon";		
	})
</script>
<body>
</body>
</html>

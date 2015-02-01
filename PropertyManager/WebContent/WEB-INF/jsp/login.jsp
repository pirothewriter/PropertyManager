<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$("login").submit(function(event){
		event.preventDefault();
		$.ajax({
			type:'POST',
			cache:false,
			url:'checkIsMember.tmon',
			data:$(this).serialize(),
			success:function(msg){
				if(msg == 'LOGGED_IN')
					alert("로그인 되었습니다!");
				else if(msg == 'NO_MEMBER'){
					alert("잘못된 아이디나 비밀번호입니다!");
					location.reload(true);
				}
				return false;
			},
			error:function(msg){
				alert('처리중 오류가 발생했습니다!');
				return false;
			}
		});
	});
})

	
</script>
<body>
<div id="header">
	티켓몬스터 자산관리 시스템입니다.
</div>
<div id="login_form">
	<form id="login">
		아이디 : <input type="text" id="username" class="username"><br>
		비밀번호 : <input type="password" id="password" class="password"><br>
		<input type="submit" value="로그인">
	</form>
</div>
</body>
</html>

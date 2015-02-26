<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>비밀번호 변경</title>
<!--jquery 라이브러리 들여오기  -->
<script src="/js/jquery-1.10.2.js"></script>
<!-- jquery ui css -->
<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
	$(document).ready(function(){
		$("#cancel").click(function(){
			document.location.href="/";
		});
		
		$("#changePw").submit(function(){
			if($("#currentPassword").val() == "") {
				alert("현재 비밀번호를 입력해주세요!");
				return false;
			}
			
			if($("#toBePassword").val() == "") {
				alert("변경될 비밀번호를 입력해주세요!");
				return false;
			}
			
			if($("#checkPassword").val() == "") {
				alert("변경될 비밀번호와 동일한 비밀번호를 입력해주세요!");
				return false;
			}
			
			if($("#nowPassword").val() != $("#currentPassword").val()) {
				alert("현재 비밀번호가 일치하지 않습니다!");
				return false;
			}
			
			if($("#currentPassword").val() == $("#toBePassword").val()) {
				alert("현재 비밀번호와 변경될 비밀번호가 같을 수 없습니다");
				return false;
			}
			
			if($("#toBePassword").val() != $("#checkPassword").val()) {
				alert("변경할 비밀번호와 비밀번호 확인이 서로 다릅니다.\n비밀번호를 확인해주세요!");
				return false;
			}
			
			$.ajax({
				type : "POST",
				url : "/changingPassword.tmon",
				data : {adAccount : $("#adAccount").val(),
						toBePassword : $("#toBePassword").val()},
				success : function(msg) {
					if(msg == "SUCCESS") {
						alert("비밀번호가 변경되었습니다!");
					} else {
						alert("비밀번호 변경에 실패하였습니다!");
					}
				},
				error : function(msg) {
					alert("서버 오류입니다!");
				}
			});
			
			document.location.href="/";
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<form class="form-inline" id="changePw">
			<input type="hidden" id="adAccount" name="adAccount" value="${adAccount}" />
			<input type="hidden" id="nowPassword" name="nowPassword" value="${password}" />
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">비밀번호 변경</td>
					</tr>
					<tr>
						<th>현재 비밀번호</th>
						<td><input type="password" name="currentPassword" id="currentPassword"></td>
					</tr>
					<tr>
						<th>변경할 비밀번호</th>
						<td><input type="password" name="toBePassword" id="toBePassword"></td>
					</tr>
					<tr>
						<th>변경할 비밀번호 확인</th>
						<td><input type="password" name="checkPassword" id="checkPassword"></td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="btn btn-primary">승인</button>
			<button type="button" class="btn btn-default" id="cancel">취소</button>
		</form>
	</div>
</body>
</html>
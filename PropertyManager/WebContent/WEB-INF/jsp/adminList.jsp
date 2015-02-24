<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사원정보</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("button[excute='revokeAdmin']").click(function(){
			var revokeIt = confirm("관리자계정을 해제하겠습니까?");
			if(revokeIt == true){
				$.ajax({
					type : "GET",
					data : {adAccount : $(this).val()},
					url : "/revokeAdmin.tmon",
					cache : false,
					success : function(msg){
						if(msg == "SUCCESS"){
							alert("관리자 권한이 해제되었습니다!");
							location.reload(true);
						} else {
							alert("서버 오류입니다!");
							return false;
						}
					}
				})
			}
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="list">
			<table id="memberTable" class="table table-condensed table-bordered">
			<tbody>
				<tr>
					<th>사원명</th>
					<th>소속부서(大)</th>
					<th>소속부서(小)</th>
					<th>AD 계정</th>
					<th>내선번호</th>
					<th>관리자계정 해제</th>
				</tr>
				 <c:forEach var="member" items="${members}" varStatus="status">
				 <tr>
				    <td>${member.memberName }</td>
				    <td>${member.upperDivision }</td>
				    <td>${member.lowerDivision }</td>
				    <td>${member.adAccount }</td>
				    <td>${member.officePhoneNumber }</td>
				    <td><button type="button" excute="revokeAdmin" class="btn btn-danger" id="revoke${member.adAccount }" value="${member.adAccount }">관리자계정 해제</button>
				</tr>
				</c:forEach>
			</tbody> 
			</table>
		</div>
	</div>
</body>
</html>

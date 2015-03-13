<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>대상 사원 검색</title>
<!--jquery 라이브러리 들여오기  -->
<script src="/js/jquery-1.10.2.js"></script>
<!-- jquery ui css -->
<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		if($("table input").length == 0){
			alert("잘못된 사원명입니다! 다시 확인해주세요");
			self.close();
		}
		
		$("#confirmMember").click(function(){
			if($("table input:checked").length == 0){
				alert("사원을 선택해주세요!");
				return false;
			}
			
			var selectedRow = $("table input:checked").parent().parent();
			
			var selectedMemberName = selectedRow.children()[2];
			var selectedMemberAdAccount = selectedRow.children()[1];
			var selectedMemberDivision = selectedRow.children()[3];			
			
			$(opener.document).find("#targetMemberName").val(selectedMemberName.childNodes[0].value);
			$(opener.document).find("#targetMemberAccount").val(selectedMemberAdAccount.childNodes[0].value);
			$(opener.document).find("#targetMemberDivision").val(selectedMemberDivision.childNodes[0].value);
			self.close();
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<table class="table">
			<tbody>
				<tr>
					<th>선택</th>
					<th>AD 계정</th>
					<th>사원명</th>
					<th>소속부서(大)</th>
				</tr>
				<c:forEach var="member" items="${members}" varStatus="status">
				 <tr>
				 	<td><input name="membersChecked" type="radio" value='${member.adAccount }'></td>
				    <td><input type=hidden class='adAccount' value='${member.adAccount}'>${member.adAccount}</td>
				    <td><input type=hidden class='memberName' value='${member.memberName}'>${member.memberName}</td>
				    <td><input type=hidden class='upperDivision' value='${member.upperDivision}'>${member.upperDivision}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="button" id="confirmMember" class="btn btn-primary">확인</button>
	</div>
</body>
</html>
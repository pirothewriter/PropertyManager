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
</script>
</head>
<body>
	<div id="wrapper">
		<div id="list">
			<form action="/selectMember.tmon" method="post" name="allMembers">
			<table id="memberTable">
			<tbody>
				<tr>
					<th>사원번호</th>
					<th>사원명</th>
					<th>소속부서(大)</th>
					<th>소속부서(小)</th>
					<th>AD 계정</th>
					<th>내선번호</th>
				</tr>
				 <c:forEach var="member" items="${members}" varStatus="status">
				 <tr>
				    <td>${member.memberId }</td>
				    <td>${member.memberName }</td>
				    <td>${member.upperDivision }</td>
				    <td>${member.lowerDivision }</td>
				    <td>${member.adAccount }</td>
				    <td>${member.officePhoneNumber }</td>
				</tr>
				</c:forEach>
			</tbody> 
			</table>
			<button type="button" id="formSubmit">선택</button>
			</form>
		</div>
	</div>
</body>
</html>
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
		$(document).ready(function(){
			$(".modifyMember[id^='memberId']").on("click", function(){
				var value = this.value;
				document.location.href="/modifyMember.tmon?memberId=" + value;
			});
			
			$("#insertProperty").on("click", function(){
				document.location.href="/insert.tmon";
			});
			
			$("#insertMember").on("click", function(){
				document.location.href="/memberinsert.tmon";
			});
		})
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="seacher">
			AD 계정 <input name = "adAccout">
			사 원 명<input name = "name">
		</div>
		<div id="list">
			<form action="/memberInfo.tmon" method="get" name="members">
			<table id="memberTable">
			<tbody>
				<tr>
					<th>사원번호</th>
					<th>사원명</th>
					<th>소속부서(大)</th>
					<th>소속부서(小)</th>
					<th>AD 계정</th>
					<th>내선번호</th>
					<th>보유자산보기</th>
					<th>사원정보수정</th>
				</tr>
				 <c:forEach var="member" items="${members}" varStatus="status">
				 <tr>
				    <td>${member.memberId }</td>
				    <td>${member.memberName }</td>
				    <td>${member.upperDivision }</td>
				    <td>${member.lowerDivision }</td>
				    <td>${member.adAccount }</td>
				    <td>${member.officePhoneNumber }</td>
				    <td><button type="submit" name='memberId' class="showDetailInfo" value="${member.memberId }">보기</button></td>
				    <td><button type="button" class="modifyMember" id="memberId${member.memberId }" value="${member.memberId }">수정</button></td>
				</tr>
				</c:forEach>
			</tbody> 
			</table>
			</form>
		</div>
		<div id="test">
		<button type="button" id="insertProperty">자산등록</button>
		<button type="button" id="insertMember">사원등록</button>
		</div>
	</div>
</body>
</html>
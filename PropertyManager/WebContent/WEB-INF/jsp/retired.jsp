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
			$(".view_log").click(function(){
				var memberId = this.value;
				var popupUrl = "/personalLog.tmon?memberId=" + memberId;
				var popupOption = "width=400, heignt=300, resizable=false, scrollbars=false";
				
				window.open(popupUrl, "", popupOption);
			});
			
			$("#back_to_list").on("click", function(){
				document.location.href="/showMembers.tmon";
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
			<table id="memberTable">
			<tbody>
				<tr>
					<th>사원번호</th>
					<th>사원명</th>
					<th>소속부서(大)</th>
					<th>소속부서(小)</th>
					<th>AD 계정</th>
					<th>내선번호</th>
					<th>자산기록보기</th>
				</tr>
				 <c:forEach var="member" items="${retiredMembers}" varStatus="status">
				 <tr>
				    <td>${member.memberId }</td>
				    <td>${member.memberName }</td>
				    <td>${member.upperDivision }</td>
				    <td>${member.lowerDivision }</td>
				    <td>${member.adAccount }</td>
				    <td>${member.officePhoneNumber }</td>
				    <td><button type="button" class="view_log" value="${member.memberId }">보기</button></td>
				</tr>
				</c:forEach>
			</tbody> 
			</table>
			<%@ include file="pagenation.jsp" %>
		</div>
		<div id="navi">
		<button type="button" id="back_to_list">목록으로</button>
		</div>
	</div>
</body>
</html>
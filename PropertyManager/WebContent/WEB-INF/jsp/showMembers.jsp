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
	// 카테고리 정보를 불러오기 위한 작업
	function getUpperDivisionSearcherInfos(){
		$("#searcherUpper").append("<option value=''>소속부서(大)</option>");
		<c:forEach var="division" items="${upperDivisionList}" varStatus="status">
		$("#searcherUpper").append("<option value=${division }>${division}</option>");
		</c:forEach>
	}
	
	function getLowerDivisionSearcherInfos(upperDivision){
		$("#searcherLower").append("<option value=''>소속부서(小)</option>");
		<c:forEach var="division" items="${lowerDivisionList}" varStatus="status">
		$("#searcherLower").append("<option value=${division }>${division}</option>");
		</c:forEach>
	}
	
	$(document).ready(function(){
		getUpperDivisionSearcherInfos();
		getLowerDivisionSearcherInfos();
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div id="seacher">
			<select id = "searcherUpper" name = "upperDivision"></select>
			<select id = "searcherLower" name = "lowerDivision"></select>
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
					<th>상세보기</th>
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
				</tr>
				</c:forEach>
			</tbody> 
			</table>
			</form>
		</div>
	</div>
</body>
</html>
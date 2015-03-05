<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>자산실사</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#nthListForSearch option:last").prop("selected", "selected")
		
		$("#searcher").submit(function(event){
			event.preventDefault();
			var url = "/inspection.tmon";
			var params = "?";
			
			if($("#searchAdAccout").val() != "")
				params += "adAccount=" + encodeURI(encodeURIComponent($("#searchAdAccout").val())) + "&";
			
			if($("#searchName").val() != "")
				params += "memberName=" + encodeURI(encodeURIComponent($("#searchName").val())) + "&";
			
			document.location.href = url + params;
		});
		
		$("#initializer").click(function(){
			document.location.href="inspection.tmon";
		});
		
		$("#insertInspection").click(function(){
			document.location.href="inspectionInsert.tmon";
		});
		
		$("#endNth").click(function(){
			var isEnd = confirm("정말로 ${lastestNth}차 실사를 종료하시겠습니까?");
			if(isEnd == true){
				$.ajax({
					type : "GET",
					cache : false,
					url : "/endNth.tmon",
					data : {'nth' : '${lastestNth}'},
					success : function(msg) {
						if(msg == "SUCCESS") {
							alert("실사가 종료되었습니다!");
							location.reload(true);
						} else {
							alert("예기치못한 장애로 실사종료에 실패했습니다!");
							return false;
						}
					}, error : function(msg) {
						alert("서버 장애입니다!");
						return false;
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div style="overflow: hidden; margin-bottom: 30px;">
		<div id="seacher" class="form-group" align="left" style="width: 70%; float: left;">
			<form class="form-inline" name="searcher" id="searcher">
				<label>회차</label>
				<select name="nth" id="nthListForSearch" class="form-control">
					<c:forEach var="nths" items="${nthList }" varStatus="status">
					<option value="${nths.nth }">${nths.nth }차</option>
					</c:forEach>
				</select>
				<label>AD 계정</label> <input class="form-control" name = "adAccout" id="searchAdAccout">
				<label>사원명</label><input class="form-control" name = "nameOfMember" id="searchName">
				<button class="btn btn-default" type="submit" id="searcherSubmit">검색</button>
				<button class="btn btn-danger" type="button" id="initializer">초기화</button>
			</form>
		</div>
		<div align="right" style="width:30%; padding-right:10px;float: left;">
			<button type="button" class="btn btn-primary" id="insertInspection">실사결과 입력</button>
		</div>
		</div>
		<div align="left" style="float:left"><span class="label label-success" style="font-size: 18px;">${lastestNth}차 실사</span></div>
		<div align="right"><button type="button" id="endNth" class="btn btn-danger">${lastestNth}차 실사 종료</button></div>
		<div id="list">
		<form action="/memberInfo.tmon" method="get" name="members">
			<table id="memberTable" class="table table-condensed table-bordered">
			<tbody>
				<tr>
					<th>자산실사차수</th>
					<th>자산번호</th>
					<th>자산명</th>
					<th>소유자</th>
					<th>소유자 AD계정</th>
					<th>실소유자</th>
					<th>실소유자 AD계정</th>
					<th>자산실사일</th>
				</tr>
				 <c:forEach var="inspections" items="${inspection}" varStatus="status">
				 <tr>
				    <td>${inspection.nth}차</td>
				    <td>${inspection.propertyNumber}</td>
				    <td>${inspection.propertyName}</td>
				    <td>${inspection.memberName}</td>
				    <td>${inspection.adAccount}</td>
				    <td>${inspection.reamMemberName}</td>
				    <td>${inspection.realADAccount}</td>
				    <td>${inspection.inspectionDate}</td>
				</tr>
				</c:forEach>
			</tbody> 
			</table>
			<%@ include file="pagenation.jsp" %>
		</form>
		</div>
	</div>
</body>
</html>

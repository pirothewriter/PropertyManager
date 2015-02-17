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
			$("#selectUpper").on("change", function(){
				$("#selectUpper").attr("name", "upperDivision");
				$("#inputDirectUpperDivision").attr("disabled", "disabled");
				$("#inputDirectUpperDivision").removeAttr("name");
				
				if($("#selectUpper").val() == ''){
					$("#selectLower option").remove();
					$("#selectLower").append("<option value=''>부서명(小)</option>");
				} else {
					$.ajax({
						type : "POST",
						url : "/loadLowDivision.tmon",
						dataType : "json",
						data : {"upperDivision" : $("#selectUpper").val()},
						success : function(data){
							$("#selectLower option").remove();
							$("#selectLower").append("<option value=''>부서명(小)</option>");
							$.each(data, function(index, element){
								$("#selectLower").append("<option value='" + element.categoryName + "'>" + element.categoryName + "</option>");
							});
						}
					});
				}
			});
			
			$("#selectLower").on("change", function(){
				$("#selectLower").attr("name", "lowerDivision");
				$("#inputDirectLowerDivision").attr("disabled", "disabled");
				$("#inputDirectLowerDivision").removeAttr("name");
			});
			
			$("#searcherSubmit").on("click", function(event){
				event.preventDefault();
				var url = "/retired.tmon";
				var params = "?";
				
				if($("#selectUpper").val() != "")
					params += "upperDivision=" + encodeURI(encodeURIComponent($("#selectUpper").val())) + "&";
				
				if($("#selectLower").val() != "")
					params += "lowerDivision=" + encodeURI(encodeURIComponent($("#selectLower").val())) + "&";
				
				if($("#searchAdAccout").val() != "")
					params += "adAccount=" + encodeURI(encodeURIComponent($("#searchAdAccout").val())) + "&";
				
				if($("#searchName").val() != "")
					params += "nameOfMember=" + encodeURI(encodeURIComponent($("#searchName").val())) + "&";
				
				document.location.href = url + params;
			});
			
			$("#initializer").on("click", function(){
				document.location.href="retired.tmon";
			});
			
			$(".view_log").click(function(){
				var adAccount = this.value;
				var popupUrl = "/personalLog.tmon?adAccount=" + adAccount;
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
		<div id="seacher" class="form-group">
				<span class="col-md-1" style="width:70%;">
					<form class="form-inline" name="searcher">
						<label>부서명(大)</label> <select class="form-control" id="selectUpper" name="upperDivision">
						<option value=''>부서명(大)</option>
						<c:forEach var="category" items="${upperDivisions }" varStatus="status">
						<option value="${category.categoryName }">${category.categoryName }</option>
						</c:forEach>
						</select>
						<label>부서명(小)</label> <select id="selectLower" class="form-control" name="lowerDivision">
						<option value=''>부서명(小)</option>
						</select>
						<label>AD 계정</label> <input class="form-control" name = "adAccout" id="searchAdAccout">
						<label>사원명</label><input class="form-control" name = "nameOfMember" id="searchName">
						<button class="btn btn-default" type="button" id="searcherSubmit">검색</button>
						<button class="btn btn-danger" type="button" id="initializer">초기화</button>
					</form>
				</span>
			</div>
			<div id="list">
			<table id="memberTable" class="table table-condensed table-bordered">
				<tbody>
						<tr>
							<th>사원명</th>
							<th>소속부서(大)</th>
							<th>소속부서(小)</th>
							<th>AD 계정</th>
							<th>내선번호</th>
							<th>자산기록보기</th>
						</tr>
						 <c:forEach var="member" items="${retiredMembers}" varStatus="status">
						 <tr>
						    <td>${member.memberName }</td>
						    <td>${member.upperDivision }</td>
						    <td>${member.lowerDivision }</td>
						    <td>${member.adAccount }</td>
						    <td>${member.officePhoneNumber }</td>
						    <td><button type="button" class="view_log btn btn-default" value="${member.adAccount }">보기</button></td>
						</tr>
						</c:forEach>
				</tbody> 
			</table>
				<%@ include file="pagenation.jsp" %>
			</div>
			<div id="navi">
			<button type="button" id="back_to_list" class="btn btn-default">목록으로</button>
			</div>
	</div>
</body>
</html>
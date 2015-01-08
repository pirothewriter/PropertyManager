<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 정보 수정</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	function checkIntegrity(){
		var checker = true;
		
		for(var index = 0; index < $("form input").length; index++){
			if($("form input")[index].value == '')
				checker = false;
		}
		
		return checker;
	}
	
	$(document).ready(function(){
		callLowerDivision();
		
		$("form").on("submit", function(event){
			var checker = checkIntegrity();
			var num_regx = /^[0-9]+$/;
			
			if(checker == false){
				alert("값을 채워주세요!");
				return false;
			}
			
			else if(! num_regx.test($("input")[0].value)){
				alert("사원번호는 숫자만 들어갈 수 있습니다!");
				return false;
			}
			
			else if(! num_regx.test($("input")[5].value)){
				alert("내선번호는 숫자만 들어갈 수 있습니다!");
				return false;
			}
			
			else{
				event.preventDefault();
				$.ajax({
					type : "POST",
					cache : false,
					url : 'modifyingMember.tmon',
					data : $(this).serialize(),
					success : function(msg){
						if(msg == 'SUCCESS'){
							alert("등록되었습니다!");
							document.location.href="/showMembers.tmon";
						} else {
							alert("오류입니다. 다시 시도해주세요!");
						}
					}
				});
			}
		});
		
		$("#retire").on("click", function(event){
			var isRetire = confirm("퇴사처리 하시겠습니까? 퇴사처리를 하면 할당된 자산들은 전부 회수됩니다");
			if(isRetire == true){
				event.preventDefault();
				$.ajax({
					type : "GET",
					cache : false,
					url : "/retireMember.tmon",
					data : "memberId=${member.memberId}",
					success : function(msg){
						if(msg == 'SUCCESS'){
							alert("정상적으로 퇴사처리되었습니다!");
						} else {
							alert("오류입니다!")
						}
						document.location.href="/showMembers.tmon";
					}
				});
			}
		});
		
		$("#cancel").on("click", function(){
			document.location.href="/showMembers.tmon";
		});
		
		$("#selectUpper").on("change", function(){
			if($("#selectUpper").val() == 'directInput'){
				$("#inputDirectUpperDivision").removeAttr("disabled");
				$("#selectUpper").removeAttr("name");
				$("#inputDirectUpperDivision").attr("name", "upperDivision");
				$("#inputDirectUpperDivision").val("");
				
				$("#selectLower option").remove();
				$("#selectLower").val('directInput');
				$("#selectLower").append("<option value='directInput'>직접입력</option>");
				
				$("#inputDirectLowerDivision").removeAttr("disabled");
				$("#selectLower").removeAttr("name");
				$("#inputDirectLowerDivision").attr("name", "lowerDivision");
				$("#inputDirectLowerDivision").val("");
			} else {
				$("#selectUpper").attr("name", "upperDivision");
				$("#inputDirectUpperDivision").attr("disabled", "disabled");
				$("#inputDirectUpperDivision").removeAttr("name");
				$("#inputDirectUpperDivision").val("직접입력");
				
				if($("#selectUpper").val() == ''){
					$("#selectLower option").remove();
					$("#selectLower").append("<option value=''>부서명(小)</option>");
					$("#selectLower").append("<option value='directInput'>직접입력</option>");
				} else {
					callLowerDivision();
				}
			}
		});
		
		$("#selectLower").on("change", function(){
			if($("#selectLower").val() == 'directInput'){
				$("#inputDirectLowerDivision").removeAttr("disabled");
				$("#selectLower").removeAttr("name");
				$("#inputDirectLowerDivision").attr("name", "lowerDivision");
				$("#inputDirectLowerDivision").val("");
			} else {
				$("#selectLower").attr("name", "lowerDivision");
				$("#inputDirectLowerDivision").attr("disabled", "disabled");
				$("#inputDirectLowerDivision").removeAttr("name");
				$("#inputDirectLowerDivision").val("직접입력");
			}
		});
	})
	
	function callLowerDivision(){
		$.ajax({
			type : "POST",
			url : "/loadLowerDivision.tmon",
			dataType : "json",
			data : {"upperDivision" : $("#selectUpper").val()},
			success : function(data){
				$("#selectLower option").remove();
				$("#selectLower").append("<option value=''>부서명(小)</option>");
				$.each(data, function(index, element){
					if(element.categoryName == '${member.lowerDivision}'){
						$("#selectLower").append("<option value='" + element.categoryName + "' selected>" + element.categoryName + "</option>");
					} else {
						$("#selectLower").append("<option value='" + element.categoryName + "'>" + element.categoryName + "</option>");
					}
				});
				
				$("#selectLower").append("<option value='directInput'>직접입력</option>");
			}
		});
	}
</script>
</head>

<body>
	<div id="wrapper">
		<div id="list">
			<form method="post" name="inputForm">
			사원번호 : <input type="text" name="memberId" value="${member.memberId }" readonly><br>
			사 원 명 : <input type="text" name="memberName" value="${member.memberName }"><br>
			부서명(大) : <select id="selectUpper" name="upperDivision">
			<option value=''>부서명(大)</option>
			<c:forEach var="category" items="${upperCategory }" varStatus="status">
			<c:choose>
			<c:when test="${category.categoryName eq member.upperDivision}">
				<option value="${category.categoryName }" selected>${category.categoryName }</option>
			</c:when>
			<c:otherwise>
				<option value="${category.categoryName }">${category.categoryName }</option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			<option value="directInput">직접입력</option>
			</select>
			<input type="text" id="inputDirectUpperDivision" disabled value="직접입력"><br>
			부서명(小) : <select id="selectLower" name="lowerDivision">
			<option value=''>부서명(소)</option>
			<c:forEach var="category" items="${lowerCategory }" varStatus="status">
			<option value="${category.categoryName }">${category.categoryName }</option>
			</c:forEach>
			<option value="directInput">직접입력</option>
			</select>
			<input type="text" id="inputDirectLowerDivision" disabled value="직접입력"><br>
			AD계정 : <input type="text" name="adAccount" value="${member.adAccount }"><br>
			내선번호 : <input type="text" name="officePhoneNumber" value="${member.officePhoneNumber }"><br>
			<br><b>사원번호는 변경할 수 없습니다.</b><br>
			<button type="submit" id="formSubmit">수정</button>
			<button type="button" id="retire">퇴사처리</button>
			<button type="button" id="cancel">취소</button>
			</form>
		</div>
	</div>
</body>
</html>
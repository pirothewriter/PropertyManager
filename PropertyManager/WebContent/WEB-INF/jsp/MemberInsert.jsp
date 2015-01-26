<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 정보 등록</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
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
					$.ajax({
						type : "POST",
						url : "/loadLowerDivision.tmon",
						dataType : "json",
						data : {"upperDivision" : $("#selectUpper").val()},
						success : function(data){
							$("#selectLower option").remove();
							$("#selectLower").append("<option value=''>부서명(小)</option>");
							$.each(data, function(index, element){
								$("#selectLower").append("<option value='" + element.categoryName + "'>" + element.categoryName + "</option>");
							});
							
							$("#selectLower").append("<option value='directInput'>직접입력</option>");
						}
					});
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
		
		$("#inputForm").on("submit", function(event){
			var num_regx = /^[0-9]+$/;
			
			else if($("form input[name=memberName]").val() == ''){
				alert("사원명을 입력해주세요!");
				return false;
			}
			
			else if($("form input[name=adAddress]") == ''){
				alert("AD 계정을 입력해주세요!");
				return false;
			} 
			
			else if($("form input[name=officePhoneNumber]") == ''){
				alert("내선번호를 입력해주세요!");
				return false;
			}
			
			else if($("#selectUpper").val() == ''){
				alert("부서명(大)을 선택해주세요!");
				return false;
			}
			
			else if($("#selectLower").val() == ''){
				alert("부서명(小)을 선택해주세요!");
				return false;
			}
			
			else if($("#selectUpper").val() == 'directInput' && ($("#inputDirectUpperDivision").val() == "직접입력" || $("#inputDirectUpperDivision").val() == "")){
				alert("부서명을 정확히 입력해주세요!");
				return false;
			}
			
			else if($("#selectLower").val() == 'directInput' && ($("#inputDirectLowerDivision").val() == "직접입력" || $("#inputDirectLowerDivision").val() == "")){
				alert("부서명을 정확히 입력해주세요!");
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
					url : 'insertingMember.tmon',
					data : $(this).serialize(),
					success : function(msg){
						if(msg == 'SUCCESS'){
							alert("등록되었습니다!");
							location.reload(true);
						} else {
							alert("중복된 사원번호입니다. 다시 입력해주세요!");
						}
					}
				});
			}
		});
		
		$('#btn_submit').on("click", function() {
	        var data = new FormData();
	        $.each($('#attachFile')[0].files, function(i, file) {
	            data.append('file-' + i, file);
	        });
	 
	        $.ajax({
	            url: '/uploadMembers.tmon',
	            type: "post",
	            dataType: "text",
	            data: data,
	            processData: false,
	            contentType: false,
	            success: function(msg, textStatus, jqXHR) {
	                if(msg == 'NOT CSV'){
	                	alert("csv파일만 업로드 가능합니다!");
	                	return false;
	                } else if(msg == 'NO FILE'){
	                	alert("파일을 선택해주세요!");
	                	return false;
	                } else if(msg == 'SUCCESS'){
	                	alert("등록 성공!");
	                	location.reload(true);
	                } else {
	                	alert("서버 에러");
	                	location.reload(true);
	                }
	            },
	            error: function(jqXHR, textStatus, errorThrown) {}
	        });
	    });
		
		$("#btn_download_form").click(function(event){
			event.preventDefault();
			window.location.href = "csv/memberinsert.csv";
		});
	})
</script>
</head>

<body>
	<div id="wrapper">
		<div id="list">
			<form id="inputForm" method="post" name="inputForm">
			사 원 명 : <input type="text" name="memberName"><br>
			부서명(大) : <select id="selectUpper" name="upperDivision">
			<option value=''>부서명(大)</option>
			<c:forEach var="category" items="${upperCategory }" varStatus="status">
			<option value="${category.categoryName }">${category.categoryName }</option>
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
			AD계정 : <input type="text" name="adAccount"><br>
			내선번호 : <input type="text" name="officePhoneNumber"><br>
			<button type="submit" id="formSubmit">입력</button>
			</form>
		</div>
		<div id="csvForm">
			<button type="button" id="btn_download_form">양식 다운로드</button><br>
			<form id="submitForm" enctype="multipart/form-data">
        		<input name="attachFile" id="attachFile" type="file" /><br/>
        		<button type="button" id="btn_submit">upload</button>
    		</form>
		</div>
	</div>
</body>
</html>
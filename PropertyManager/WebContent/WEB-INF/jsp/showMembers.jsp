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
					callLowerDivision();
				}
			});
			
			$("#selectLower").on("change", function(){
				$("#selectLower").attr("name", "lowerDivision");
				$("#inputDirectLowerDivision").attr("disabled", "disabled");
				$("#inputDirectLowerDivision").removeAttr("name");
			});
			
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
			
			$("#showRetired").on("click", function(){
				document.location.href="/retired.tmon";
			});
			
			$('#btn_submit').on("click", function() {
		        var data = new FormData();
		        $.each($('#attachFile')[0].files, function(i, file) {
		            data.append('file-' + i, file);
		        });
		 
		        $.ajax({
		            url: '/uploadMappedProperty.tmon',
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
		                } else if(msg == "INCORRECT FORM"){
		                	alert("잘못된 양식의 csv파일입니다!");
		                	location.reload(true);
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
				window.location.href = "csv/properties.csv";
			});
			
			$("#searcherSubmit").on("click", function(event){
				event.preventDefault();
				var url = "/showMembers.tmon";
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
				document.location.href="showMembers.tmon";
			});
		})
	});
	
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
			}
		});
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="seacher">
			<form name="searcher">
				부서명(大) : <select id="selectUpper" name="upperDivision">
				<option value=''>부서명(大)</option>
				<c:forEach var="category" items="${upperDivisions }" varStatus="status">
				<option value="${category.categoryName }">${category.categoryName }</option>
				</c:forEach>
				</select>
				부서명(小) : <select id="selectLower" name="lowerDivision">
				<option value=''>부서명(小)</option>
				</select>
	
				AD 계정 <input name = "adAccout" id="searchAdAccout">
				사 원 명<input name = "nameOfMember" id="searchName">
				<button type="button" id="searcherSubmit">검색</button>
				<button type="button" id="initializer">초기화</button>
			</form>
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
			<%@ include file="pagenation.jsp" %>
			</form>
		</div>
		<div id="test">
		<button type="button" id="insertProperty">자산등록</button>
		<button type="button" id="insertMember">사원등록</button>
		<button type="button" id="showRetired">퇴사자 보기</button>
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
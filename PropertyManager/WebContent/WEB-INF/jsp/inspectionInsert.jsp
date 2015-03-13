<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자산 실사 결과입력</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#barcodeInput").submit(function(){
			if($("#barcode").val() == ""){
				alert("값을 입력해주세요!");
				return false;
			}
			
			else {
				var propertyNumber = $("#barcode").val();
				var nth = $("#nowNth").val();
				callPropertyInfo(nth, propertyNumber);
			}
		});
		
		$("#searchMember").submit(function(){
			event.preventDefault();
			if($("#searchMemberName").val() == ""){
				alert("사원명을 입력해주세요");
				return false;
			} else {
				var memberName = $("#searchMemberName").val();
				memberName = encodeURI(memberName);
				var popupUrl = "/getMemberByMemberName.tmon?memberName="+encodeURI(memberName);
				var popupOption = "width=400, height=400, toolbar=no, location=no, status=no, menubar=no, resizable=no";
				window.open(popupUrl, "", popupOption);
			}
		});
		
		$("#insertInspectionForm").submit(function(){
			var isSave = confirm("해당 자산들의 실사내용을 저장하시겠습니까?");
			if(isSave == true) {
				$.ajax({
					type : "POST",
					cache : false,
					url : 'saveInspectedData.tmon',
					data : $(this).serialize(),
					success : function(msg){
						if(msg == 'SUCCESSS'){
							alert("성공했습니다!");
							location.reload(true);
						} else {
							alert("예기치 못한 오류입니다!");
							return false;
						}
					}, error : function(){
						alert("서버 오류입니다!");
						return false;
					}
				});
			}
		});
	});
	
	function callPropertyInfo(nth, propertyNumber){
		event.preventDefault();
		$.ajax({
			cache:false,
			data : {nth : nth, propertyNumber : propertyNumber},
			type : 'GET',
			url : 'getPropertyInfomationForInspection.tmon',
			success : function(msg){
				if(msg == "ALREADY_CHECKED"){
					alert("이미 ${nth}차 자산실사에 조사된 자산입니다!");
					$("#barcode").val('');
					return false;
				} else if(msg == "NOT_EXIST"){
					alert("시스템에 등록되지 않은 자산입니다\n자산을 시스템에 등록한 후 다시 시도해주세요!");
					$("#barcode").val('');
					return false;
				} else if(msg == "") {
					alert("알수 없는 에러입니다. 관리자에게 문의해주세요!");
					$("#barcode").val('');
					return false;
				} else {
					var inspectData = jQuery.parseJSON(msg);
					
					if(inspectData.memberName == undefined)
						inspectData.memberName = "";
					if(inspectData.adAccount == undefined)
						inspectData.adAccount = "";
					
					var isAlreadyChecked = false;
					for(var index = 0; index < $("#inspectTable tbody input[type=hidden]").length; index++){
						if($("#inspectTable tbody input[type=hidden]")[index].value == propertyNumber)
							isAlreadyChecked = true;
					}
					
					if(isAlreadyChecked == false)
						$("#inspectTable tbody").append("<tr><td><input type='checkbox' name='checked' checked value="+ propertyNumber + "></td><td><input type='text' name='propertyNumber[]' value='" + propertyNumber + "' class='form-control' readonly></td><td><input type='text' name='propertyName[]' value='" + inspectData.propertyName + "' class='form-control' readonly></td><td><input type='text' name='memberName[]' value='" + inspectData.memberName + "' class='form-control' readonly></td><td><input type='text' name='adAccount[]' value='"+ inspectData.adAccount +"' class='form-control' readonly></td></tr>");
					
					$("#barcode").val('');
					
					if($("input[name=adAccount]:last").val() != $("#targetMemberAccount").val()){
						$("input[name=propertyNumber]:last").css("color", "red");
						$("input[name=propertyName]:last").css("color", "red");
						$("input[name=memberName]:last").css("color", "red");
						$("input[name=adAccount]:last").css("color", "red");
					}
					
					window.stop();
					return false;
				}
			}, error : function(msg) {
				alert("서버 오류입니다!");
				return false;
			}
		});
	}
</script>
</head>
<body>
	<div id="searchTargetMember" style="width:50%; padding-bottom:75px; float:left">
			<form id="searchMember">
				<label class="label label-default" style="float:left; font-size:18px; margin-right:10px;">대상 사원 검색</label>
				<input type="text" name="memberName" id="searchMemberName" class="form-control" style="width:25%; float:left">
				<button class="btn btn-default" style="float:left;" type="submit">검색</button> 
			</form>
		</div>
		<div id="barcodeArea" style="width:50%; padding-bottom:75px; float:left">
			<form id="barcodeInput">
				<label class="label label-default" style="float:left; font-size:18px; margin-right:10px;">바코드 입력</label>
				<input type="text" name="propertyNumber" id="barcode" class="form-control" style="width:25%; float:left;">
				<button class="btn btn-default" style="float:left;" type="submit">입력</button>
			</form>
		</div>
	<div id="inspectionArea">
		<div><h4>자산실사 입력</h4></div>
		<form class="form-inline" name="insertInspectionForm" id="insertInspectionForm">
			<input type="hidden" id="nowNth" name="nth" value="${nth}">
			<table id="memberTable" class="table table-condensed">
				<tbody>
					<tr>
						<th>사원명</th>
						<td><input type="text" name="targetMemberName" id="targetMemberName" class='form-control' readonly></td>
						<th>AD 계정명</th>
						<td><input type="text" name="targetMemberAdAccount" id="targetMemberAccount" class="form-control" readonly></td>
						<th>소속부서(大)</th>
						<td><input type="text" name="targetMemberDivision" id="targetMemberDivision" class="form-control" readonly></td>
					</tr>
				</tbody>
			</table>
			<table id="inspectTable" class="table table-condensed">
				<tbody>
					<tr>
						<th>선택</th>
						<th>자산번호</th>
						<th>자산명</th>
						<th>소유자명</th>
						<th>소유자 AD계정</th>
					</tr>
				</tbody>
			</table>
			<button class="btn btn-primary" type="submit">저장</button>
		</form>
	</div>
</body>
</html>
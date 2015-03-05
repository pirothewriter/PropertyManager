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
				$.ajax({
					cache : false,
					type : "GET",
					url : 'getMemberByMemberName.tmon',
					data : {memberName : memberName},
					success : function(msg){
						if(msg == "NO_MEMBER"){
							alert("해당 이름을 가진 사원이 없습니다!");
							return false;
						} else {
							var result = jQuery.parseJSON(msg);
						}
					}, error : function(msg){
						alert("서버 오류입니다!");
						return false;
					}
				});
			}
		});
		
		$("#insertInspectionForm").submit(function(){
			var isSave = confirm("해당 자산들의 실사내용을 저장하시겠습니까?");
			if(isSave == true) {
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
						$("#inspectTable tbody").append("<tr><td><input type='checkbox' name='checked' checked value="+ propertyNumber + "></td><td><input type='text' name='propertyNumber' value='" + propertyNumber + "' class='form-control' readonly></td><td><input type='text' name='propertyName' value='" + inspectData.propertyName + "' class='form-control' readonly></td><td><input type='text' name='memberName' value='" + inspectData.memberName + "' class='form-control' readonly></td><td><input type='text' name='adAccount' value='"+ inspectData.adAccount +"' class='form-control' readonly></td></tr>");
					
					$("#barcode").val('');
					window.stop();
					return false;
				}
			}, error : function(msg) {
				alert("서버 오류입니다!");
				return false;
			}
		});
	}
	
	function layer_open(el){

		var temp = $('#' + el);
		var bg = temp.prev().hasClass('bg');	//dimmed 레이어를 감지하기 위한 boolean 변수

		if(bg){
			$('.layer').fadeIn();	//'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
		}else{
			temp.fadeIn();
		}

		// 화면의 중앙에 레이어를 띄운다.
		if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
		else temp.css('top', '0px');
		if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
		else temp.css('left', '0px');

		temp.find('a.cbtn').click(function(e){
			if(bg){
				$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
			}else{
				temp.fadeOut();
			}
			e.preventDefault();
		});

		$('.layer .bg').click(function(e){	//배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
			$('.layer').fadeOut();
			e.preventDefault();
		});

	}				
</script>

<style type="text/css">
	.layer {display:none; position:fixed; _position:absolute; top:0; left:0; width:100%; height:100%; z-index:100;}
		.layer .bg {position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50);}
		.layer .pop-layer {display:block;}

	.pop-layer {display:none; position: absolute; top: 50%; left: 50%; width: 410px; height:auto;  background-color:#fff; border: 5px solid #3571B5; z-index: 10;}	
	.pop-layer .pop-container {padding: 20px 25px;}
	.pop-layer p.ctxt {color: #666; line-height: 25px;}
	.pop-layer .btn-r {width: 100%; margin:10px 0 20px; padding-top: 10px; border-top: 1px solid #DDD; text-align:right;}

	a.cbtn {display:inline-block; height:25px; padding:0 14px 0; border:1px solid #304a8a; background-color:#3f5a9d; font-size:13px; color:#fff; line-height:25px;}	
	a.cbtn:hover {border: 1px solid #091940; background-color:#1f326a; color:#fff;}
</style>

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
	<a href="#" class="btn-example" onclick="layer_open('layer2');return false;">예제-2 보기</a>
	<div class="layer">
		<div class="bg"></div>
		<div id="layer2" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<!--content //-->
					<table class="table" id="selectMember">
						<tbody>
							<tr>
								<th>선택</th>
								<th>사원명</th>
								<th>AD계정</th>
								<th>부서명(大)</th>
							</tr>
						</tbody>
					</table>
					<!--// content-->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
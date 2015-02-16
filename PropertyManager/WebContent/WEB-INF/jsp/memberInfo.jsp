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
		$("#back_to_list").on("click", function(){
			document.location.href="/showMembers.tmon";
		});
		
		$("#add_property").on("click", function(){
			document.location.href = "/urgentProperty.tmon?adAccount=" + '${memberInfo.adAccount}';
		});
		
		$("#propertyInfo form").on("submit", function(event){
			var isRelease = confirm("자산을 회수하시겠습니까?");
			if(isRelease == true){
				event.preventDefault();
				$.ajax({
					type : "POST",
					cache : false,
					url : 'releasing.tmon',
					data : $(this).serialize(),
					success : function(msg){
						alert("회수되었습니다!");
						location.reload(true);
					}
				});
			}
		});
		
		$(".view_log").click(function(){
			var propertyNumber = this.value;
			var popupUrl = "/equipmentLog.tmon?propertyNumber=" + propertyNumber;
			var popupOption = "width=400, heignt=300, resizable=false, scrollbars=false";
			
			window.open(popupUrl, "", popupOption);
		});
		
		$("#personalLog").on("click", function(){
			var adAccount = '${memberInfo.adAccount }';
			var popupUrl = "/personalLog.tmon?adAccount=" + adAccount;
			var popupOption = "width=400, heignt=300, resizable=false, scrollbars=false";
			
			window.open(popupUrl, "", popupOption);
		});
		
		$("#add_property_by_barcode").on("click", function(){
			var adAccount = '${memberInfo.adAccount }';
			var popupUrl = "/barcodeInput.tmon?adAccount=" + adAccount;
			var popupOption = "width=500, heignt=200, resizable=false, scrollbars=false";
			
			window.open(popupUrl, "", popupOption);
		});
	})
</script>
</head>
<body>
<div id="wrapper">
	<div id="memberInfo">
		<table>
			<tbody>
				<tr>
					<td><b>사원명 : </b></td><td>${memberInfo.memberName }</td>
					<td><b>AD계정 : </b></td><td>${memberInfo.adAccount }</td>
				</tr>
				<tr>
					<td><b>소속부서(大) : </b></td><td>${memberInfo.upperDivision }</td>
					<td><b>소속부서(小) : </b></td><td>${memberInfo.lowerDivision }</td>
					<td><b>내선번호 : </b></td><td>${memberInfo.officePhoneNumber }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="navi">
		<button type="button" id="back_to_list">목록으로</button>	
		<button type="button" id="add_property">자산추가</button>
		<button type="button" id="add_property_by_barcode">바코드 입력</button>
		<button type="button" id="personalLog">개인장비 이력보기</button>
	</div>
	<div id="propertyInfo">
		<form method="post" name="releaseForm">
			<input type="text" name="adAccount" value="${memberInfo.adAccount }" style="visibility:hidden; ">
			<table>
				<tbody>
				<tr>
					<th>선택</th>
					<th>자산번호</th>
					<th>자산명</th>
					<th>대분류</th>
					<th>소분류</th>
					<th>자산정보1</th>
					<th>자산정보2</th>
					<th>자산입고일(IT유닛 입고당일)</th>
					<th>자산입고일(재무팀 월말)</th>
					<th>자산제조사</th>
					<th>자산판매사</th>
					<th>자산구매단가</th>
					<th>자산지급일</th>
					<th>과거이력</th>
				</tr>
				<c:forEach var="property" items="${propertyInfo}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="propertyNumber" value="${property.propertyNumber }"></td>
					<td>${property.propertyNumber }</td>
					<td>${property.name }</td>
					<td>${property.upperCategory }</td>
					<td>${property.lowerCategory }</td>
					<td>${property.infomation1 }</td>
					<td>${property.infomation2 }</td>
					<td>${property.incommingItUnit }</td>
					<td>${property.incommingFinance }</td>
					<td>${property.productor }</td>
					<td>${property.seller }</td>
					<td>${property.price }</td>
					<td>${property.urgentDate }</td>
					<td><button type="button" class="view_log" value="${property.propertyNumber }">보기</button>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<button type="submit" id="release_properties">자산회수</button>
		</form>
	</div>
</div>
</body>
</html>
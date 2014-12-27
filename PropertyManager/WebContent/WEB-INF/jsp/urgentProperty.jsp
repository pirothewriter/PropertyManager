<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>자산부여</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	function getCheckedValue(){
		var checkedPropertyNumber;
		for(var index = 0; index < $("input[name='check_property']").length; index++){
			if($("input[name='check_property']")[index].checked == true)
				checkedPropertyNumber = $("input[name='check_property']")[index].value; 
		}
		
		return checkedPropertyNumber;
	}
	
	$(document).ready(function(){
		$("form").on("submit", function(event){
			var urgentIt = confirm("해당 자산들을 추가하시겠습니까?");
			if(urgentIt == true) {
				event.preventDefault();
				$.ajax({
					type : "POST",
					cache : false,
					url : 'mapping.tmon',
					data : $(this).serialize(),
					success : function(msg){
						alert("등록되었습니다!");
						document.location.href = "/memberInfo.tmon?memberId=${memberId }";
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
		
		$("#cancelUrgenting").on("click", function(){
			document.location.href="/memberInfo.tmon?memberId=${memberId}";
		});
	})
</script>
</head>
<body>
	<div id="wrapper">
		<div id="searcher">
		</div>
		<form method="post" name="mappingForm">
			<div id="ownerlessProperties">
				<input type="text" name="memberId" value="${memberId }" style="visibility:hidden; ">
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
						<th>자산이력보기</th>
					</tr>
					<c:forEach var="property" items="${ownerlessEquipment}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="propertyNumber" value='${property.propertyNumber }'>
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
						<td><button type="button" class="view_log" value="${property.propertyNumber }">보기</button>
					</tr>
					</c:forEach>
					</tbody>
				</table>
				<button type="submit" id="submitMapping">추가</button>
				<button type="button" id="cancelUrgenting">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
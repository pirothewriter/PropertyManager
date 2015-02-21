<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>바코드입력</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>
	<div id="wrapper">
		<div id="barcodeArea">
			<form id="barcodeInput">
				<input type="hidden" id="adAccount" name="adAccount" value="${adAccount }">
				바코드 입력 : <input type="text" name="propertyNumber" id="barcode">
				<button class="btn btn-default" type="submit">입력</button>
			</form>
		</div>
		<div id="properties">
			<form id="urgentMultipleProperties">
				<input type="hidden" id="adAccount" name="adAccount" value="${adAccount }">
				<table id="toUrgentProperties" class="table">
					<tbody>
						<tr>
							<th>선택</th>
							<th>자산번호</th>
						</tr>
					</tbody>
				</table>
				<button class="btn btn-primary" type="submit">자산추가</button>
			</form>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		var adAccount = '${adAccount}';
		
		$("#barcodeInput").submit(function(){
			if($("#barcode").val() == ""){
				alert("값을 입력해주세요!");
				return false;
			}
			
			else {
				var propertyNumber = $("#barcode").val();
				callPropertyInfo(propertyNumber);
			}
		});
		
		$("#urgentMultipleProperties").submit(function(event){
			if($("#toUrgentProperties input").length == 0) {
				alert("선택된 자산이 없습니다!");
				return false;
			} else {
				var urgentIt = confirm("해당 자산들을 추가하시겠습니까?");
				if(urgentIt == true){
					event.preventDefault();
					$.ajax({
						type : "POST",
						cache : false,
						url : 'mapping.tmon',
						data : $(this).serialize(),
						success : function(msg){
							alert("등록되었습니다!");
							window.close();
						}
					});
				}
			}
		});
	})
	
	function callPropertyInfo(propertyNumber){
		$.ajax({
			cache:false,
			data : $("#barcodeInput").serialize(),
			type : 'POST',
			url : 'getPropertyInfomation.tmon',
			success : function(msg){
				if(msg == "NO_EXIST"){
					alert("잘못된 자산번호이거나 이미 할당된 자산번호입니다.\n자산번호를 다시금 확인해주세요");
				} else if(msg == 'EXIST') {
					var isAlreadyChecked = false;
					for(var index = 0; index < $("#toUrgentProperties input").length; index++){
						if($("#toUrgentProperties input")[index].value == propertyNumber)
							isAlreadyChecked = true;
					}
					
					if(isAlreadyChecked == false)
						$("#toUrgentProperties tbody").append("<tr><td><input type='checkbox' name='propertyNumber' checked value="+ propertyNumber + "></td><td>"+ propertyNumber + "</td></tr>");
					else 
						alert("이미 추가된 자산입니다!");
					
					$("#barcode").val('');
				}
				
				window.stop();
				return false;
			},
			error : function(msg) {
				alert("오류가 발생하였습니다!");
				return false;
			}
		});
	}
</script>
</body>
</html>
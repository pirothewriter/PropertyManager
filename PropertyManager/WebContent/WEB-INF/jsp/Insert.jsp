<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>자산정보 입력</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="js/glDatePicker.min.js"></script>

<script type="text/javascript">
	function getPropertyHeadNumber(upperCategory, lowerCategory){
		if(upperCategory == 'monitor' || upperCategory == 'desktop' || upperCategory == 'notebook')
			$("input[name='propertyHeadNumber']").val('PCM');
		else if(upperCategory == 'network')
			$("input[name='propertyHeadNumber']").val('NET');
		else if(upperCategory == 'server')
			$("input[name='propertyHeadNumber']").val('SVR');
		else if(upperCategory == 'phone'){
			if(lowerCategory == 'SIP')
				$("input[name='propertyHeadNumber']").val('SIP');
			else
				$("input[name='propertyHeadNumber']").val('IPT');
		}
		else if(upperCategory =='etc')
			$("input[name='propertyHeadNumber']").val('ETC');
	}
	
	function getLowerCategory(val){
		if(val == "monitor"){
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").append("<option value='work'>업무용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='sub'>서브용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='office'>사무용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='develop'>개발용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='design'>디자인용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='etc'>기타</option>");
			$("select[name=propertyLowerCategory]").attr("readonly",false);
			$("select[name=propertyLowerCategory]").attr("disabled",false);
		}
		
		else if(val == "desktop"){
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").append("<option value='office'>사무용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='design'>디자인용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='develop'>개발용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='etc'>기타</option>");
			$("select[name=propertyLowerCategory]").attr("readonly",false);
			$("select[name=propertyLowerCategory]").attr("disabled",false);
		}
		
		else if(val == "phone"){
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").append("<option value='IPT'>IPT</option>");
			$("select[name=propertyLowerCategory]").append("<option value='IPT'>IPT_IPCC</option>");
			$("select[name=propertyLowerCategory]").append("<option value='SIP'>SIP</option>");
			$("select[name=propertyLowerCategory]").append("<option value='etc'>기타</option>");
			$("select[name=propertyLowerCategory]").attr("readonly",false);
			$("select[name=propertyLowerCategory]").attr("disabled",false);
		}
		
		else if(val == "notebook"){
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").append("<option value='SPR'>망분리</option>");
			$("select[name=propertyLowerCategory]").append("<option value='office'>업무용</option>");
			$("select[name=propertyLowerCategory]").append("<option value='etc'>기타</option>");
			$("select[name=propertyLowerCategory]").attr("readonly",false);
			$("select[name=propertyLowerCategory]").attr("disabled",false);
		}
		
		else if(val == ""){
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").attr("readonly",false);
			$("select[name=propertyLowerCategory]").attr("disabled",false);
		}
		
		else{
			$("select[name='propertyLowerCategory'] option").remove();
			$("select[name=propertyLowerCategory]").append("<option value=''>소분류</option>");
			$("select[name=propertyLowerCategory]").append("<option value='etc'>기타</option>");
			$("select[name=propertyLowerCategory]").attr("disabled",false);
			$("select[name=propertyLowerCategory]").attr("readonly",false);
		}
	}
	
	function checkIntegrity(){
		var checker = true;
		
		for(var index = 0; index < $("form input").length; index++){
			if($("form input")[index].value == null)
				checker = false;
		}
		
		if($("select")[0].value == '' || $("select")[1].value == '')
			checker = false;
		
		return checker;
	}
	
	$(document).ready(function(){
		$("select[name='propertyUpperCategory']").change(function(){
			getLowerCategory($("select[name='propertyUpperCategory']").val());
			getPropertyHeadNumber($("select[name='propertyUpperCategory']").val(), $("select[name='propertyLowerCategory']").val())
		});
		$("select[name='propertyLowerCategory']").change(function(){
			getPropertyHeadNumber($("select[name='propertyUpperCategory']").val(), $("select[name='propertyLowerCategory']").val())
		});
		
		$("#datepicker_it" ).glDatePicker({
			cssName: 'flatwhite',
			dateFormat: 'yy-mm-dd',
			allowMonthSelect: true,
		    allowYearSelect: true,
		    onClick: function(target, cell, date, data) {
		    	var month = date.getMonth()+1;
		        target.val(date.getFullYear() + '-' + month + '-' + date.getDate());
		        if(data != null) {
		            alert(data.message + '\n' + date);
		        }
		    }
			});
		
		$("#datepicker_finance" ).glDatePicker({
			cssName: 'flatwhite',
			dateFormat: 'yy-mm-dd',
			allowMonthSelect: true,
		    allowYearSelect: true,
		    onClick: function(target, cell, date, data) {
		    	var month = date.getMonth()+1;
		        target.val(date.getFullYear() + '-' + month + '-' + date.getDate());
		        if(data != null) {
		            alert(data.message + '\n' + date);
		        }
		    }
	        });
		
		$("#formSubmit").on("click", function(){
			var checker = checkIntegrity();
			var num_regx = /^[0-9]+$/;
			if(checker == false){
				alert("값을 채워주세요!");
			}
			
			else if(! num_regx.test($("input")[1].value)){
				alert("자산번호 뒷자리는 숫자만 들어갈 수 있습니다!");
			}
			
			else if(! num_regx.test($("input")[9].value)){
				alert("자산단가는 숫자만 들어갈 수 있습니다!");				
			}
			
			else{
				document.inputForm.submit();
			}
		});
	})
</script>
</head>
<body>
	<div id="wrapper">
		<div id="list">
			<form action="/inserting.tmon" method="post" name="inputForm">
			자산번호 : <input type="text" name="propertyHeadNumber" readonly><input type="text" name="propertyNumber"><br>
			자산구분 : 
			<select name="propertyUpperCategory">
				<option value="">대분류</option>
				<option value="monitor">모니터</option>
				<option value="desktop">데스크탑</option>
				<option value="notebook">노트북</option>
				<option value="network">네트워크장비</option>
				<option value="server">서버장비</option>
				<option value="phone">전화기</option>
				<option value="etc">기타장비</option>
			</select>
			<select name="propertyLowerCategory">
				<option value="">소분류</option>
			</select>
			<br>
			자산명 : <input type="text" name="propertyName"><br>
			자산정보1 : <input type="text" name="propertyInformation1"><br>
			자산정보2 : <input type="text" name="propertyInformation2"><br>
			자산 입고일 : <input type="text" id="datepicker_it" name="propertyIncommingITUnit"><br>
			(IT유닛 입고 당일)<br>
			자산 입고일 : <input type="text" id="datepicker_finance" name="propertyIncommingFinance"><br>
			(재무팀 월말)<br>
			<p>
			자산 제조사 : <input type="text" name="propertyProducted"><br>
			자산 판매사 : <input type="text" name="propertySeller"><br>
			자산 구매 단가 : <input type="text" name="propertyPrice"><br> 
			
			유저정보 : <input type="text" name="propertyUser" value="티켓몬스터" disabled><p>
			<button type="button" id="formSubmit">입력</button>
			</form>
		</div>
	</div>
</body>
</html>
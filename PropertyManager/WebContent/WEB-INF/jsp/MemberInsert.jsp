<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	function checkIntegrity(){
		var checker = true;
		
		for(var index = 0; index < $("form input").length; index++){
			if($("form input")[index].value == '')
				checker = false;
		}
		
		return checker;
	}
	
	$(document).ready(function(){
		$("#inputForm").on("submit", function(event){
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
			사원번호 : <input type="text" name="memberId"><br>
			사 원 명 : <input type="text" name="memberName"><br>
			부서명(大) : <input type="text" name="upperDivision"><br>
			부서명(小) : <input type="text" name="lowerDivision"><br>
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
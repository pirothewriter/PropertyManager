<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>자산목록</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

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
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").append("<option value='work'>업무용</option>");
			$("#selectLower").append("<option value='sub'>서브용</option>");
			$("#selectLower").append("<option value='office'>사무용</option>");
			$("#selectLower").append("<option value='develop'>개발용</option>");
			$("#selectLower").append("<option value='design'>디자인용</option>");
			$("#selectLower").append("<option value='etc'>기타</option>");
			$("#selectLower").attr("readonly",false);
			$("#selectLower").attr("disabled",false);
		}
		
		else if(val == "desktop"){
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").append("<option value='office'>사무용</option>");
			$("#selectLower").append("<option value='design'>디자인용</option>");
			$("#selectLower").append("<option value='develop'>개발용</option>");
			$("#selectLower").append("<option value='etc'>기타</option>");
			$("#selectLower").attr("readonly",false);
			$("#selectLower").attr("disabled",false);
		}
		
		else if(val == "phone"){
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").append("<option value='IPT'>IPT</option>");
			$("#selectLower").append("<option value='IPT'>IPT_IPCC</option>");
			$("#selectLower").append("<option value='SIP'>SIP</option>");
			$("#selectLower").append("<option value='etc'>기타</option>");
			$("#selectLower").attr("readonly",false);
			$("#selectLower").attr("disabled",false);
		}
		
		else if(val == "notebook"){
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").append("<option value='SPR'>망분리</option>");
			$("#selectLower").append("<option value='office'>업무용</option>");
			$("#selectLower").append("<option value='etc'>기타</option>");
			$("#selectLower").attr("readonly",false);
			$("#selectLower").attr("disabled",false);
		}
		
		else if(val == ""){
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").attr("readonly",false);
			$("#selectLower").attr("disabled",false);
		}
		
		else{
			$("#selectLower option").remove();
			$("#selectLower").append("<option value=''>소분류</option>");
			$("#selectLower").append("<option value='etc'>기타</option>");
			$("#selectLower").attr("disabled",false);
			$("#selectLower").attr("readonly",false);
		}
	}

	$(document).ready(function(){
		$("#selectUpper").change(function(){
			getLowerCategory($("#selectUpper").val());
			getPropertyHeadNumber($("#selectUpper").val(), $("#selectLower").val())
		});
		$("#selectLower").change(function(){
			getPropertyHeadNumber($("#selectUpper").val(), $("#selectLower").val())
		});
		
		$(".view_log").click(function(){
			var propertyNumber = this.value;
			var popupUrl = "/equipmentLog.tmon?propertyNumber=" + propertyNumber;
			var popupOption = "width=400, heignt=300, resizable=false, scrollbars=false";
			
			window.open(popupUrl, "", popupOption);
		});
		
		$("#initializer").on("click", function(){
			document.location.href="showAllProperties.tmon";
		});
		
		$("#searcherSubmit").submit(function(event){
			event.preventDefault();
			var url = "/showAllProperties.tmon?";
			var params = "";
			
			if($("#selectUpper").val() != "")
				params += "upperCategory=" + encodeURI(encodeURIComponent($("#selectUpper").val())) + "&";
			
			if($("#selectLower").val() != "")
				params += "lowerCategory=" + encodeURI(encodeURIComponent($("#selectLower").val())) + "&";
			
			document.location.href = url + params;
		});
	})
</script>
</head>
<body>
	<div id="wrapper">
		<div id="searcher">
			<form name="searcher" id="propertySearcher" role="form" class="form-inline">
				<label>대분류</label><select class="form-control" id="selectUpper" name="upperCategory" class="form-control">
					<option value=''>분류(大)</option>
					<option value="monitor">모니터</option>
					<option value="desktop">데스크탑</option>
					<option value="notebook">노트북</option>
					<option value="network">네트워크장비</option>
					<option value="server">서버장비</option>
					<option value="phone">전화기</option>
					<option value="etc">기타장비</option>
				</select>
				<label>소분류</label><select class="form-control" id="selectLower" name="lowerCategory" class="form-control">
				<option value=''>분류(小)</option>
				</select>
				<label>자산번호</label><input type="text" id="propertyNumber" name="propertyNumber" class="form-control" />
				<button type="submit" class="btn btn-default" id="searcherSubmit">검색</button>
				<button type="button" class="btn btn-danger" id="initializer">초기화</button>
			</form>
		</div>
		<div id="propertiesList">
				<table class="table">
					<tbody>
					<tr>
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
						<th>소유자</th>
						<th>자산이력보기</th>
					</tr>
					<c:forEach var="property" items="${properties }" varStatus="status">
					<tr>
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
						<td>${property.user }</td>
						<td><button type="button" class="view_log btn btn-default" value="${property.propertyNumber }">보기</button>
					</tr>
					</c:forEach>
					</tbody>
				</table>
				<%@ include file="pagenation.jsp" %>
			</div>
	</div>
</body>
</html>

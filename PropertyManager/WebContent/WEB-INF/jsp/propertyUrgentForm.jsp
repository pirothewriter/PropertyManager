<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>전산 자산 지급 확인서</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" media="print" />
<link rel="stylesheet" href="/css/print.css" type="text/css" media="print" />
<link rel="stylesheet" href="/css/printform.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="/js/bootstrap.js"></script>
<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<link href="/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function(){
		var printIt = confirm("자산 인수서를 출력하시겠습니까?");
		if(printIt == true){
			window.print();
		}
		
		window.close();
	});
</script>
</head>
<body>
<div class="printForm">
	<div class="page-header">
		<span style="text-align: center;"><strong><h1>전산 자산 지급 확인서</h1></strong></span>
	</div>
	<div id="memberInfoTable">
		<table class="table table-bordered" style="width:30%;">
			<tbody>
				<tr>
					<th style="width:30%;">소속</th><td>${memberInfo.upperDivision }</td>
				</tr>
				<tr>
					<th style="width:30%;">이름</th><td>${memberInfo.memberName }<font id="greyCharacter">(${memberInfo.officePhoneNumber })</font></td>
				</tr>
				<tr>
					<th style="width:30%;">연락처</th><td style="width:40%"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="context">
		<p>상기 본인은 귀사에서 제공한 전산 자산에 대해 이상 없이 수령/반납 하였음을 확인하며, 아래의 사항을 준수할 것을 서약합니다.</p>
		<ol>
			<li>본인은 전산 자산 사용에 대하여 <strong>‘회사가 제공한 기본자산’과 ‘업무상 필요하다고 회사가 인정하여 제공한 자산’</strong>에 대해 관리의 책임을 다하겠습니다.</li>
			<li>1번 항목의 자산에 대해 임의적인 교체나 훼손 시 회사는 책임을 물을 수 있으며, <strong>퇴사시 담당자에게 이상 없이 반납</strong>하겠습니다</li>
			<li>
				<p>회사는 수집 정보에 대한 상기 인의 사전 동의 없이는 개인 정보를 공개하지 않으며, 수집된 정보는 아래와 같이 이용하고 있습니다.</p>
				<ul>
					<li>수집 정보 : 이름, 부서, 연락처</li>
					<li>이용 목적 : 개인정보보호 관련 법령 준수를 위한 개인정보취급자의 개인정보보호 서약서 보존</li>
				</ul>
				<p>보안사고 발생시 회사는 사고 발생의 원인 및 법원, 기타 기관에 소명을 하여야 할 의무가 있으므로 수집일로부터 1년간 상기 인의 개인정보를 보유할 수 있습니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용할 것입니다.</p>
			</li>
		</ol>
		<p>회사에 제공하는 개인정보 사용에 대하여 동의합니다.</p>
		<p>본인은 위의 사항들을 세심히 읽어 보았음을 확인하고 서명합니다.</p>
	</div>
	<div id="admit" align="right">
		<p id="date">${nowDate }</p>
		<p>서명 : ${memberInfo.memberName } (인)</p>
	</div>
	<div id="propertiesTable">
		<table class="table table-bordered">
			<tbody>
				<tr id="propertiesTableHeadRow">
					<th style="width:15%">날짜</th>
					<th style="width:15%">분류</th>
					<th style="width:25%">자산번호</th>
					<th style="width:25%">모델명</th>
					<th style="width:10%">수령확인</th>
					<th style="width:10%">반납확인</th>
				</tr>
				<c:forEach varStatus="status" var="property" items="${properties }">
				<tr>
					<td>${property.urgentDate }</td>
					<td>${property.upperCategory }</td>
					<td>${property.propertyNumber }</td>
					<td>${property.name }</td>
					<td></td>
					<td></td>
				</tr>
				</c:forEach>
				<c:forEach varStatus="status" var="index" begin="${numberOfProperties }" end="7" step="1">
				<tr>
					<td> </td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<p align="center">(위와 같이 제공한 전산 자산에 대해 이상 없이 수령/반납 하였음을 확인합니다.)</p>
	</div>
	<div id="corp" align="right">
		<p style="font-size:13px;"><strong>주식회사 티켓몬스터 귀중</strong></p>
		<p><img src="../image/tmonlogo.png" width="100px;" height="25px;"></p>
	</div>
</div>
</body>
</html>
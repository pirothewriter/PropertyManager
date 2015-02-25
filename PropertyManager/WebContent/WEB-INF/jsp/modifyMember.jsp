<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 정보 수정</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet"
	type="text/css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	function checkIntegrity() {
		var checker = true;

		for (var index = 0; index < $("form input").length; index++) {
			if ($("form input")[index].value == '')
				checker = false;
		}

		return checker;
	}

	$(document)
			.ready(
					function() {
						callLowerDivision();
						
						$("#grantAdmin").click(function(){
							var isGrant = confirm("관리자 권한을 부여하겠습니까?");
							if(isGrant == true){
								$.ajax({
									type : "POST",
									cache : false,
									url : 'grantAdmin.tmon',
									data : "adAccount=${member.adAccount}",
									success : function(msg) {
										if(msg == 'SUCCESS') {
											alert("등록되었습니다!");
											document.location.href="/showMembers.tmon";
										} else {
											alert("서버 오류입니다!");
											location.reload(true);
										}
									}
								});
							} else {
								return false;
							}
						});

						$("form")
								.submit(
										function(event) {
											var checker = checkIntegrity();
											var num_regx = /^[0-9]+$/;

											if (checker == false) {
												alert("값을 채워주세요!");
												return false;
											}

											else if (!num_regx
													.test($("input")[4].value)) {
												alert("내선번호는 숫자만 들어갈 수 있습니다!");
												return false;
											}

											else {
												event.preventDefault();
												$
														.ajax({
															type : "POST",
															cache : false,
															url : 'modifyingMember.tmon',
															data : $(this)
																	.serialize(),
															success : function(
																	msg) {
																if (msg == 'SUCCESS') {
																	alert("등록되었습니다!");
																	document.location.href = "/showMembers.tmon";
																} else {
																	alert("오류입니다. 다시 시도해주세요!");
																}
															}
														});
											}
										});

						$("#retire")
								.on(
										"click",
										function(event) {
											var isRetire = confirm("퇴사처리 하시겠습니까? 퇴사처리를 하면 할당된 자산들은 전부 회수됩니다");
											if (isRetire == true) {
												event.preventDefault();
												$
														.ajax({
															type : "GET",
															cache : false,
															url : "/retireMember.tmon",
															data : "adAccount=${member.adAccount}",
															success : function(
																	msg) {
																if (msg == 'SUCCESS') {
																	alert("정상적으로 퇴사처리되었습니다!");
																} else {
																	alert("오류입니다!")
																}
																document.location.href = "/showMembers.tmon";
															}
														});
											}
										});

						$("#cancel").on("click", function() {
							document.location.href = "/showMembers.tmon";
						});

						$("#selectUpper")
								.on(
										"change",
										function() {
											if ($("#selectUpper").val() == 'directInput') {
												$("#inputDirectUpperDivision")
														.removeAttr("disabled");
												$("#selectUpper").removeAttr(
														"name");
												$("#inputDirectUpperDivision")
														.attr("name",
																"upperDivision");
												$("#inputDirectUpperDivision")
														.val("");

												$("#selectLower option")
														.remove();
												$("#selectLower").val(
														'directInput');
												$("#selectLower")
														.append(
																"<option value='directInput'>직접입력</option>");

												$("#inputDirectLowerDivision")
														.removeAttr("disabled");
												$("#selectLower").removeAttr(
														"name");
												$("#inputDirectLowerDivision")
														.attr("name",
																"lowerDivision");
												$("#inputDirectLowerDivision")
														.val("");
											} else {
												$("#selectUpper").attr("name",
														"upperDivision");
												$("#inputDirectUpperDivision")
														.attr("disabled",
																"disabled");
												$("#inputDirectUpperDivision")
														.removeAttr("name");
												$("#inputDirectUpperDivision")
														.val("직접입력");

												if ($("#selectUpper").val() == '') {
													$("#selectLower option")
															.remove();
													$("#selectLower")
															.append(
																	"<option value=''>부서명(小)</option>");
													$("#selectLower")
															.append(
																	"<option value='directInput'>직접입력</option>");
												} else {
													callLowerDivision();
												}
											}
										});

						$("#selectLower")
								.on(
										"change",
										function() {
											if ($("#selectLower").val() == 'directInput') {
												$("#inputDirectLowerDivision")
														.removeAttr("disabled");
												$("#selectLower").removeAttr(
														"name");
												$("#inputDirectLowerDivision")
														.attr("name",
																"lowerDivision");
												$("#inputDirectLowerDivision")
														.val("");
											} else {
												$("#selectLower").attr("name",
														"lowerDivision");
												$("#inputDirectLowerDivision")
														.attr("disabled",
																"disabled");
												$("#inputDirectLowerDivision")
														.removeAttr("name");
												$("#inputDirectLowerDivision")
														.val("직접입력");
											}
										});
					})

	function callLowerDivision() {
		$.ajax({
			type : "POST",
			url : "/loadLowerDivision.tmon",
			dataType : "json",
			data : {
				"upperDivision" : $("#selectUpper").val()
			},
			success : function(data) {
				$("#selectLower option").remove();
				$("#selectLower").append("<option value=''>부서명(小)</option>");
				$.each(data, function(index, element) {
					if (element.categoryName == '${member.lowerDivision}') {
						$("#selectLower").append(
								"<option value='" + element.categoryName + "' selected>"
										+ element.categoryName + "</option>");
					} else {
						$("#selectLower").append(
								"<option value='" + element.categoryName + "'>"
										+ element.categoryName + "</option>");
					}
				});

				$("#selectLower").append(
						"<option value='directInput'>직접입력</option>");
			}
		});
	}
</script>
</head>

<body>
	<div id="wrapper">
		<div id="list">
			<form role="form" method="post" name="inputForm" class="form-inline">
				<table class="table table-striped">
					<tr>
						<th>사원명</th>
						<td><input class="form-control" type="text" name="memberName"
							value="${member.memberName }"></td>
					</tr>
					<tr>
						<th>부서명(大)</th>
						<td><select class="form-control" id="selectUpper"
							name="upperDivision">
								<option value=''>부서명(大)</option>
								<c:forEach var="category" items="${upperCategory }"
									varStatus="status">
									<c:choose>
										<c:when
											test="${category.categoryName eq member.upperDivision}">
											<option value="${category.categoryName }" selected>${category.categoryName }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.categoryName }">${category.categoryName }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<option value="directInput">직접입력</option>
						</select> <input type="text" class="form-control"
							id="inputDirectUpperDivision" disabled value="직접입력"></td>
					</tr>
					<tr>
						<th>부서명(小)</th>
						<td><select id="selectLower" class="form-control"
							name="lowerDivision">
								<option value=''>부서명(소)</option>
								<c:forEach var="category" items="${lowerCategory }"
									varStatus="status">
									<option value="${category.categoryName }">${category.categoryName }</option>
								</c:forEach>
								<option value="directInput">직접입력</option>
						</select> <input type="text" id="inputDirectLowerDivision"
							class="form-control" disabled value="직접입력"></td>
					</tr>
					<tr>
						<th>AD계정</th>
						<td><input type="text" name="adAccount" class="form-control"
							value="${member.adAccount }"></td>
					</tr>
					<tr>
						<th>내선번호</th>
						<td><input type="text" name="officePhoneNumber"
							class="form-control" value="${member.officePhoneNumber }"></td>
					</tr>
				</table>
				<button type="submit" class="btn btn-success" id="formSubmit">수정</button>
				<button type="button" class="btn btn-danger" id="retire">퇴사처리</button>
				<button type="button" class="btn btn-warning" id="grantAdmin">관리자권한 부여</button>
				<button type="button" class="btn btn-default" id="cancel">취소</button>
			</form>
		</div>
	</div>
</body>
</html>
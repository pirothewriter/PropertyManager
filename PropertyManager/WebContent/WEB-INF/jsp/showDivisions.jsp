<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>부서정보</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="wrapper">
		<div id = "divisionList">
			<table>
				<tbody>
					<tr>
						<th>부서(大)</th>
						<th>부서코드</th>
						<th>부서(小)</th>
						<th>부서코드</th>
					</tr>
					<c:forEach var="upperDivision" items="${upperDivisions }" varStatus="status">
					<tr>
						<td rowspan="${upperDivision.lowerCategories }">${upperDivision.categoryName }</td>
						<td rowspan="${upperDivision.lowerCategories }">${upperDivision.categoryCode }</td>
						<c:forEach var="lowerDivision" items="${lowerDivisions }" varStatus="status">
						<c:if test="${lowerDivision.upperCategory == upperDivision.categoryCode }">
							<td>${lowerDivision.categoryName }</td>
							<td>${lowerDivision.categoryCode }</td>
							</tr>
							<tr>
						</c:if>
					</c:forEach>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
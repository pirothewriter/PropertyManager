<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>자산정보</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<link href="css/glDatePicker.flatwhite.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
</script>
</head>
<body>
	<div id="wrapper">
		<table class='table'>
			<tbody>
				<tr>
					<th>사용자</th>
					<th>지급일시</th>
					<th>회수일시</th>
				</tr>
				<c:forEach var="log" items="${equipmentLog}" varStatus="status">
				 <tr>
				    <td>${log.adAccount }</td>
				    <td>${log.urgentDate }</td>
				    <td>${log.withdrawDate }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8"%>

<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><decorator:title /></title>

<!--jquery 라이브러리 들여오기  -->
<script src="/js/jquery-1.10.2.js"></script>
<!-- jquery ui 라이브러리 들여오기  -->
<script src="/js/jquery-ui-1.10.4.js"></script>
<!-- bootstrap js 라이브러리 들여오기 -->
<script src="/js/bootstrap.js"></script>

<decorator:head />
<!-- jquery ui css -->
<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<!-- navbar 생성 -->
	<nav class="navbar navbar-static-top navbar-default" role="navigation" style='border-bottom-style: solid; border-bottom-color: #666; padding-top: 10px; padding-bottom: 10px;'>
	<div class="col-md-1" style="width:600px;"><b><h1>티몬 자산관리 시스템</h1></b></div>
	<div style="padding-top:20px;"class="col-md=1" align="right">
		<a href="<c:url value='/j_spring_security_logout' />"><button type="button" id="logout_button" class="btn btn-warning">로그아웃</button></a>
	</div>
	</nav>
	<!-- navbar 끝 -->
	<decorator:body />
</body>
<script type="text/javascript">	
</script>

</html>
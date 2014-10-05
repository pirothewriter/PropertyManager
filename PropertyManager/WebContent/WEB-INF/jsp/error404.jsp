<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script src="/js/jquery-1.10.2.js"></script>
<script src="/js/jquery-ui-1.10.4.js"></script>
<script src="/js/bootstrap.js"></script>


<link href="/css/jquery-ui-1.10.4.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.css" rel="stylesheet">

<style>
#centeringIndicator {
	width: 100%;
	height: 100%;
	text-align: center;
}

#cell {
	porision: relative;
	top: 50%;
}

body {
	font-family: "맑은 고딕", "Helvetica Neue", Helvetica, Arial, sans-serif;
}

h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6 {
	font-weight: 700;
}

h4 {
	font-size: 14px;
}

.ui-datepicker-trigger {
	padding-bottom: 8px;
	padding-left: 7px;
}

.navbar-brand {
	float: left;
	height: 50px;
	padding: 12px 15px;
	font-size: 32px;
	line-height: 20px;
	font-weight: 900;
}
}
</style>
<title>Page Not Found(HTTP Status Code : 404)</title>
</head>
<body>
	<div id="centeringIndicator">
		<div id="cell">
			<div class="jumbotron">
				<img src="../image/bg_error.png">
				<h2>요청하신 페이지를 찾을 수가 없습니다.</h2>
				<p>
				<center>
					<button
						style="margin-top: 50px; margin-left: auto; margin-right: auto;" class="btn btn-primary btn-lg" role="button" onclick="javascript:history.back()">뒤로
					</button>
				</center>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
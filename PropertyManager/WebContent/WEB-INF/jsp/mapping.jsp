<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>처리중입니다..</title>
<script type="text/javascript"> 
$(document).on("ready", function(){
	var message = '${msg}';
	var returnUrl = '${url}'; 
	alert(message); 
	if(returnUrl == 'back')
		javascript:history.back();
	else
		document.location.href=returnUrl;
});
</script>
</head>
<body> 
</body>
</html> 
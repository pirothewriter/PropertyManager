<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function loadOtherPage(index){
		$.ajax({
			type : "get",
			cache : false,
			success:function(msg){
				var nowLocation = document.location.href;
				nowLocation = nowLocation.split('?')[0];
				var url = nowLocation + "?page=" + index;
				document.location.href= url;
			}
		});
	}
	
	$(document).ready(function(){
		$("#solePageSet").change(function(){
			var viewSolePage = $("#solePageSet").val();
			if(viewSolePage != ""){
				$.session.set("viewSolePage", viewSolePage);
				location.reload(true);
			}
		});
	})
</script>
<div id="pagenation">
	<c:forEach var="index" begin="${startPage}" end="${endPage }" >
	<span><a href="javascript:;" onclick="loadOtherPage(${index});">${index}</a></span>
	</c:forEach>
	<select name="viewSolePage" id="solePageSet">
		<option value="">페이지 보기 설정</option>
		<option value="10">10개씩 보기</option>
		<option value="20">20개씩 보기</option>
		<option value="30">30개씩 보기</option>
		<option value="40">40개씩 보기</option>
		<option value="50">50개씩 보기</option>
	</select>
</div>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ajax TagUpload</title>
</head>
<body>
	
		<c:forEach var="t" items="${tag}" varStatus="loop">
		<script type="text/javascript">
	$(function(){
		$("#tagDeleteButton-${loop.index}").click(function(){
		$.ajax({
			type : "post",
			url : "/tagDelete",
			dataType: "application/JSON",
			data :{
			a:	$("#tagDel-${loop.index}").serialize() , 
			b:	$("#GameKey").serialize()
			},
		success: $(function(){
			
			$('#tagUpload2').load("/ajax/TagUpload2?id=${g.key.id}")
		})
		});
		$(function(){
			$('#tagUpload').load("/ajax/TagUpload?id=${g.key.id}")
		});
	});
});
</script>
		 <a href="/search?tag=${t}">${t}</a><br>
		<input type="hidden"id="tagDel-${loop.index}" name="tagDel" value="${t}">
	
		<input type="submit" style=" width: 100%;" id="tagDeleteButton-${loop.index}" value="削除">
		<br>
		</c:forEach>
	
</body>
</html>

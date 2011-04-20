<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ajax TagUpload</title>
		<script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
		<link href="/css/docs2.css" rel="StyleSheet" type="text/css" />
		<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
		<link href="/css/prettify.css" rel="StyleSheet" type="text/css"  />
		<link href="/css/button.css" rel="StyleSheet" type="text/css" />
		<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script src="/js/jquery.validate.min.js"></script>
		<script src="/js/prettify.js"></script>
		
</head>
<body>
	<table border="0" style="word-break:break-all">
	<tr>
		<c:forEach var="t" items="${g.tags}" varStatus="loop">
		<script type="text/javascript">
		$(function(){
			$("#tagDeleteButton-${loop.index}").click(function(){
				
				var a = $("#tagDel-${loop.index}").serialize()
				var b = 	$("#GameKey").serialize()
				var data = {a:a, b:b};
				
				$.ajax({
				type : "post",
				url : "/tagDelete",
				data :data,
				success: setTimeout(function(){
					$('#tagUpload2').load("/ajax/TagUpload2?id=${g.key.id}")
					$('#tagUpload').load("/ajax/TagUpload?id=${g.key.id}")
				},250)
				});
			});

		});
</script>
		<td width="250" align="center"><input type="hidden"id="GameKey" name="GameKey" value="${f:h(g.key)}"><br>
		 <a id="t" style="font-size: 20px;">${t.name}</a><br>
		<input type="hidden"id="tagDel-${loop.index}" name="tagDel" value="${t.name}">
		<button type="submit" class="button delete"　id="tagDeleteButton-${loop.index}"><span id="tagDeleteButton-${loop.index}" style="color: white;font-size: 20;width: 70;height:20; position: relative;right: 0;bottom: 1;">　<fmt:message key="button.delete"/>　</span></button>
		</td>
		<c:if test="${loop.count mod 3 == 0}"></tr><tr></c:if>
		</c:forEach>
	</tr>
	</table>
</body>
</html>

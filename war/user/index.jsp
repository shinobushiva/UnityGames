<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>user Index</title>
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<c:if test="${name == 'myName' }">
	<script type="text/javascript">
		$(function() {
			$("#page").super_load("/showUser/myPage?name=me");
		});
	</script>
</c:if>
<c:if test="${name == 'otherName' }">
	<script type="text/javascript">
		$(function() {
			$("#page").super_load("/showUser/myPage?name=${other}");
		});
	</script>
</c:if>
</head>
<body style="border: 1px;">
<%@ include file="/share/header.jsp"%>

	<div id="page" style="margin-top: 30px;"></div>


</body>
</html>

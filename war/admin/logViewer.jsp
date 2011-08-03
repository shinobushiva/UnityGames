<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>admin Campaign</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/view.css" rel="StyleSheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>
	<div class="demo">
		<form action="logDL">
			<p>
				Date: <input type="text" id="datepicker" name="logDate"> <input
					type="submit" value="DL" />
			</p>
		</form>
	</div>
	<!-- End demo -->
	<div class="demo-description" style="display: none;"></div>
	<!-- End demo-description -->
	<div>
		<c:forEach var="l" items="${logText}">
			<div>
				<a href="logDL?fileName=${l.fileName}">${l.fileName}</a>
			</div>
		</c:forEach>
	</div>


</body>
</html>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ajax CommentLoad</title>
		<link href="/css/docs2.css" rel="StyleSheet" type="text/css" />
		<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
		<link href="/css/prettify.css" rel="StyleSheet" type="text/css"  />
		<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script src="/js/jquery.validate.min.js"></script>
		<script src="/js/prettify.js"></script>
</head>
<body>

<c:forEach var="c" items="${c}" >
		<div align="center">
		${c.comment}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate  value="${c.date}" pattern="hh:mm MM/dd/yyyy"/><br>
		</div>
		</c:forEach>
</body>

</html>

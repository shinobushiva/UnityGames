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
</head>
<body>
	<form action="campaignRegistration">
		<div>
			タイトル<input type="text" name="title" />
		</div>
		<div>
			<c:forEach var="g" items="${GameList}" varStatus="loop">
				<input type="checkbox" name="gameArray" value="${g.key.id}">${g.gameName}(${g.key.id})
			</c:forEach>
		</div>
		<div><input type="submit"/></div>
	</form>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Contact</title>
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
</head>
<body>
<%@ include file="/share/header.jsp"%>
<div align="center">
<h1><fmt:message key="twitterAccount" /></h1>
<a href="http://twitter.com/#!/UGames">@UGames</a>
<h1><fmt:message key="mail" /></h1>
<h2><fmt:message key="mailaddress" />unitygamesug@gmail.com</h2>

<br>

</div>
<%@ include file="/share/footer.jsp"%>
</body>
</html>

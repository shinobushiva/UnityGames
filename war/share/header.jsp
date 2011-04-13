<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns:og="http://ogp.me/ns#"  xmlns:mixi="http://mixi-platform.com/ns#">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	if (request.getParameter("name") != null) {
%>
<title><%=request.getParameter("name")%></title>
<%
	}else{
%>
<title>header</title>
<%
	}
%>
<meta name="mixi-check-robots" content="noimage" />

	
<link href="/css/lavalamp.css" rel="StyleSheet" type="text/css" />
<link href="/css/css.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
<script src="/js/jquery.spasticNav.js"></script>
<script src="/js/jquery.updnWatermark.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/jquery.validate.messages_jp.js"></script>
<script src="/js/cmxform.js"></script>
</head>

<body>
<table  align="center" style=" position: relative;right: 10%; " border="0"><tr><td>

<a href="/unitygames/"><img src="/images/logo/Logo.png" width="250px" style="position: absolute; top: 30;"></a>


 <ul id="nav1">
        <li id="selected"><a href="/unitygames/"><fmt:message key="home" /></a></li>
        <li><a href="/howto"><fmt:message key="howto" /></a></li>
        <li><a href="/tutorial"><fmt:message key="tutorial" /></a></li>
        <li><a href="/unitygames/upload"><fmt:message key="upload" /></a></li>
    </ul>
    

  <script type="text/javascript">
  $('#nav1').spasticNav();
        </script>

</td></tr></table>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>admin test Test</title>
<%@ include file="/share/js.jsp"%>
</head>
<body>

<script type="text/javascript">

$(function(){
	var url="http://localhost:8888/api/game/all?callback={callback}";
	$.getJSONP(url,function(data){
		console.log(eval(data.gameData));
	})
	
});

</script>

</body>
</body>
</html>

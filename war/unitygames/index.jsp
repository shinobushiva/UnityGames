<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<link href="/css/views.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="unitygames.top" />
</title>
<script type="text/javascript">
	$(function() {
		$("#selectView").val("${viewType}");
		$("#View").load("view?view=${viewType}");
		$("#selectView").change(function() {
			var data = $(this).serialize();
			$.ajax({
				type : "post",
				data : data,
				success : function() {
					$("#View").load("view?" + data)
				}
			})
		})
	});
</script>
</head>
<body>

	<%@ include file="/share/header.jsp"%>
	<div id="campaign" style="margin-top: 20px; margin-bottom: 20px;"></div>

	<div id="PageLoad">

		<select name="view" id="selectView">
			<option value="0">
				<fmt:message key="NewEntry" />
			</option>
			<option value="1" id="OldEntry">
				<fmt:message key="OldEntry" />
			</option>
			<option value="2" id="MostAccess">
				<fmt:message key="MostAccess" />
			</option>
			<option value="3" id="LeastAccess">
				<fmt:message key="LeastAccess" />
			</option>
			<option value="4" id="MostComment">
				<fmt:message key="MostComment" />
			</option>
			<option value="5" id="LeastComment">
				<fmt:message key="LeastComment" />
			</option>
		</select>
		<div id="View"></div>

	</div>
	<%@ include file="/share/footer.jsp"%>

</body>

</html>

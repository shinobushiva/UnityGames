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
<link href="/css/views.css" rel="stylesheet" type="text/css"/>
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/style1.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<script language="javascript" type="text/javascript"
	src="/js/jquery.easing.min.js"></script>
<script language="javascript" type="text/javascript"
	src="/js/jSiderNews.js"></script>
<title><fmt:message key="unitygames.top" /></title>
<script type="text/javascript">
	$(function() {
		$("#selectView").val("${viewType}");
		$("#View").load("/view?view=${viewType}",null,function(){
			initPagination();
		});
		$("#campaign").css("visibility", "hidden");
		
		$("#campaign").load("/campaignList", null, function() {
			$("#campaign").css("visibility", "visible");
			var buttons = {
				previous : $('#lofslidecontent45 .lof-next'),
				next : $('#lofslidecontent45 .lof-previous')
			};

			$obj = $('#lofslidecontent45').lofJSidernews({
				interval : 4000,
				direction : 'opacitys',
				easing : 'easeInOutExpo',
				duration : 1200,
				auto : true,
				maxItemDisplay : 4,
				navPosition : 'horizontal', // horizontal
				navigatorHeight : 50,
				navigatorWidth : 50,
				mainWidth : 980,
				buttons : buttons
			});
		});
		$("#selectView").change(function() {
			var data = $(this).serialize();
			$.ajax({
				type : "post",
				data : data,
				success : function() {
					$("#View").load("/view?" + data)
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
			<option value="Default">
				<fmt:message key="NewEntry" />
			</option>
			<option value="OldEntry" id="OldEntry">
				<fmt:message key="OldEntry" />
			</option>
			<option value="MostAccess" id="MostAccess">
				<fmt:message key="MostAccess" />
			</option>
			<option value="LeastAccess" id="LeastAccess">
				<fmt:message key="LeastAccess" />
			</option>
			<option value="MostComment" id="MostComment">
				<fmt:message key="MostComment" />
			</option>
			<option value="LeastComment" id="LeastComment">
				<fmt:message key="LeastComment" />
			</option>
		</select>
		<div id="View"></div>
		<div class="Pagination" align="center"></div>
<div id="Searchresult" align="center"></div>
<div class="Pagination" align="center" style="margin: 10px;"></div>
	</div>
	<%@ include file="/share/footer.jsp"%>

</body>

</html>

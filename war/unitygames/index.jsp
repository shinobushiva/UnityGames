<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<link href="/css/view.css" rel="StyleSheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="unitygames.top" /></title>
<script type="text/javascript">
	$(function() {
		$("#View").load("/view?view=default");
		$("#campaign").css("visibility", "hidden");
		$("#campaign").load("/campaignList", null, function() {
			var tid; //timeoutID

			initMovingBoxes(function() {
				$("#campaign").css("visibility", "visible");
			}, function() {
				clearTimeout(tid);
				tid = setTimeout(function() {
					forwardFunc();
				}, 2500);
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
	<%@ include file="/share/search.jsp"%>
	<div id="campaign" style="margin-top: 20px; margin-bottom: 20px;"></div>

	<div id="PageLoad">

		<select name="view" id="selectView">
			<option value="NewEntry" selected="selected">投稿日時が新しい順</option>
			<option value="OldEntry">投稿日時が古い順</option>
			<option value="MostAccess">アクセス数が多い順</option>
			<option value="LeastAccess">アクセス数が少ない順</option>
			<option value="MostComment">コメントが多い順</option>
			<option value="LeastComment">コメントが少ない順</option>
		</select>
		<div id="View"></div>
	</div>
	<%@ include file="/share/footer.jsp"%>

</body>

</html>

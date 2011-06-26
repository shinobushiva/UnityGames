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
<script type="text/javascript">
	$(function() {
		$("select").change(function() {
			$("select option").each(function() {
				$("." + $(this).val()).hide();
			});
			$("select option:selected").each(function() {
				$("." + $(this).val()).show();
				
			if(	$(this).is("#all")){
				$("div").show();
			}
			});
			
		});
	});
</script>


</head>
<body>
	<form action="campaignRegistration">
		<div>
			タイトル<input type="text" name="title" />
		</div>
		<div id="select">
			<select><option var="all" id="all" selected="selected">全部</option>
				<option var="">タグなし</option>
				<c:forEach var="tag" items="${tagName}">
					<option var="${tag}">${tag}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<c:forEach var="g" items="${GameList}" varStatus="loop">
				<div style="margin: 5px;" id="${g.key.id}">
					<input type="checkbox" name="gameArray" value="${g.key.id}">${g.gameName}(ug${g.key.id})
					<c:forEach var="t" items="${g.fixTags}">
				${t.name}
				<script type="text/javascript">
					$(function() {
						$("#${g.key.id}").addClass("${t.name}");
					});
				</script>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
		<div>
			<input type="submit" />
		</div>
	</form>
</body>
</html>

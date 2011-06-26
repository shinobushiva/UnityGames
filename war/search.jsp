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
<link href="/css/view.css" rel="StyleSheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<title>Search</title>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>

	<table align="center">
	<tr>
		<c:forEach var="g" items="${GameList}" varStatus="loop">
			<td>
				<div style="width: 500px; padding-left: 10px; padding-right: 10px;">
					<script type="text/javascript">
						$(function() {
							$("#tabs-${g.key.id}").tabs();
						});
						$(function() {

							$("#out-${g.key.id}").hide();
							$("#change-${g.key.id}").click(
									function() {
										$("#change-${g.key.id}").css(
												"position", "relative");
										$("#change-${g.key.id}").css("top",
												"-5px");
										$("#out-${g.key.id}").show();
										$("#br-${g.key.id}").hide();
									});
						});
					</script>

					<div align="right">
						<c:if test="${not empty g.hpURL}">
							<a style="font-size: x-small; color: red;"><fmt:message
									key="outside" /> </a>
						</c:if>
						<fmt:message key="entryDay" />
						：
						<fmt:formatDate value="${g.date}" pattern="MM/dd HH:mm" />
						<br>
					</div>

					<div class="gameView">
						<div class="gameViewHead">
							<div style="width: 350px; float: left;" class="bounded">
								<%@ include file="/share/patternDistinction.jsp"%>


								<a href="${url}" class="title"> ${g.gameName} </a>
							</div>
							<div style="float: right;">
								<ul>
									<li class="noListPoint"><fmt:message key="access" />：${g.access}</li>
									<li class="noListPoint"><fmt:message key="comment" />：${g.comment}</li>
								</ul>
							</div>
							<div style="clear: both;"></div>
						</div>
						<div style="margin-top: 0.5em;" class="bounded">
							<span style="color: red;"><fmt:message key="registerTag"/></span>
							<c:forEach var="ft" items="${g.fixTags}">
								<a href="/search?tag=${ft.name}"
									style="font-size: 18px; font-weight: 900;">${ft.name}</a>

							</c:forEach>
						</div>
						<div
							style="float: left; width: 150px; height: 170px;padding-left:3px; padding-right: 3px; margin-top: 1em;">
							<a href="${url}"><img src="${thUrl}" width="150" height="150"
								class="image" /> </a>

						</div>


						<div
							style="float: left; width: 330px; height: 170px; padding-left: 5px;">
							<div id="tabs-${g.key.id}" style="height:170px;">
								<ul>
									<li style=""><a href="#tab1-${g.key.id}"><span><fmt:message
													key="explanation" /> </span> </a>
									</li>
									<li style=""><a href="#tab2-${g.key.id}"><span><fmt:message
													key="operation" /> </span> </a>
									</li>

								</ul>

								<div id="tab1-${g.key.id}">
									<a style="font-size: 15px; font: bold;">${g.contents}</a>

								</div>
								<div id="tab2-${g.key.id}">
									<a style="font-size: 15px; font: bold;">${g.operations}</a>
								</div>
							</div>
						</div>
						<div style="clear: both;">
							<%--	変更		--%>
							<%-- 		<div align="right">
								<form action="/unitygames/change" method="post">
									<a id="change-${g.key.id}" style="font-size: x-small;"><fmt:message
											key="change.delete" /> </a><a id="out-${g.key.id}"
										style="position: relative; top: -5px;"><input
										type="password" name="Pass" style="width: 40px;"><input
										type="hidden" name="key" value="${f:h(g.key)}">
										<button type="submit">
											<img id="sub" src="/images/red.gif" />
										</button> </a>
								</form>
								<div id="br-${g.key.id}"></div>
							</div>
							--%>
						</div>
					</div>

				</div>
			</td>
			<c:if test="${loop.count mod 2 == 0}">
	</tr>
	<tr>
		</c:if>

		</c:forEach>
	</tr>
</table>
<%@ include file="/share/footer.jsp" %>
</body>
</html>

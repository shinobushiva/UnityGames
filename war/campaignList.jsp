<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<link href="/css/css.css" rel="StyleSheet" type="text/css" />
<%@ include file="/share/js.jsp"%>

<title><fmt:message key="unitygames.top" /></title>
<script type="text/javascript">
	$(function() {
		//	$("#campaign").super_load("/campaignList");
		$("#slider").easySlider({
			auto : true,
			continuous : true
		});
	});
</script>
</head>
<body>

	<c:forEach var="c" items="${campaigns}">
		<div>
			<span class="campaign">~キャンペーン中~</span><span class="campaignTitle">${c.title}</span>
		</div>
		<div id="container">



			<div id="content">

				<div id="slider">
					<ul>
						<li><c:forEach var="g" items="${c.games}" varStatus="loop">
								<div style="display: inline-block;">
									<c:choose>
										<c:when test="${empty g.thumbNailURL}">
											<c:set var="thUrl"
												value="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}" />

										</c:when>
										<c:when test="${not empty g.thumbNailURL}">
											<c:set var="thUrl" value="${g.thumbNailURL}" />

										</c:when>
									</c:choose>
									<c:choose>
										<c:when test="${empty g.hpURL}">
											<c:set var="url" value="/unitygames/game?id=${g.key.id}" />


										</c:when>
										<c:when test="${not empty g.hpURL}">
											<c:set var="url" value="${g.hpURL}" />

										</c:when>
									</c:choose>
									<div>
										<a href="${url}"><img src="${thUrl }" width="150"
											height="150" class="image" /> </a>
									</div>
									<div style="text-align: center;font-size: 15px;" class="bounded">
										<a href="${url}" >${g.gameName}</a>
									</div>
								</div>
								<c:if test="${loop.count mod 5 == 0}">
						</li>
						<li></c:if>
	</c:forEach>
	</li>
	</ul>
	</div>
	</div>
	</div>
	</c:forEach>
</body>
</html>


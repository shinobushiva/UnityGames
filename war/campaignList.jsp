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
<script type="text/javascript" src="/js/jquery.movingboxes.js"
	charset="utf-8"></script>

<link type="text/css" href="/css/campaign.css" media="screen"
	charset="utf-8" rel="stylesheet" />
<script type="text/javascript" src="/js/campaign.js"></script>
<script type="text/javascript">
$("#test").click(function(){
	
	console.log($.movingBoxes.el);
	//$.movingBoxes.goForward();
	
});

</script>
</head>
<body>
<button id="test">test</button>
	<c:forEach var="c" items="${campaigns}">
	
		<div id="wrapper">
			<!-- Slider #2 (images of slider #1 reversed) -->
			<div id="slider">

				<c:forEach var="g" items="${c.games}">
					<%--サムネイルの保存パターンを判別 --%>
					<c:choose>
						<c:when test="${empty g.thumbNailURL}">
							<c:set var="thUrl"
								value="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}" />

						</c:when>
						<c:when test="${not empty g.thumbNailURL}">
							<c:set var="thUrl" value="${g.thumbNailURL}" />

						</c:when>
					</c:choose>
					<%--内部か外部サイトかを判別 --%>
					<c:choose>
						<c:when test="${empty g.hpURL}">
							<c:set var="url" value="/unitygames/game?id=${g.key.id}" />


						</c:when>
						<c:when test="${not empty g.hpURL}">
							<c:set var="url" value="${g.hpURL}"/>

						</c:when>
					</c:choose>
					<div>
						<a href="${url}">
						 <img src="${thUrl }" alt="picture"/><div style="text-align: center">${g.gameName}</div>
						</a>
					</div>
				</c:forEach>

			</div><div style="margin-left: 30px;">
<div style="display: inline-block;"><img src="/images/campaign.png"/></div><div style="display:inline-block;" class="campaignTitle">${c.title}</div>
		</div>
		</div>
		<!-- end wrapper -->
	</c:forEach>

</body>
</html>


<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%--サムネイルの保存パターンを判別 --%>
<c:choose>
	<c:when test="${empty g.thumbNailURL}">
		<c:set var="thUrl"
			value="/unitygames/thumbNail?id=${f:h(g.key.id)}" />

	</c:when>
	<c:when test="${not empty g.thumbNailURL}">
		<c:set var="thUrl" value="${g.thumbNailURL}" />

	</c:when>
</c:choose>
<%--内部か外部サイトかを判別 --%>
<c:choose>
	<c:when test="${empty g.hpURL}">
		<c:set var="url" value="/unitygames/game/ug${g.key.id}" />


	</c:when>
	<c:when test="${not empty g.hpURL}">
		<c:set var="url" value="${g.hpURL}" />

	</c:when>
</c:choose>
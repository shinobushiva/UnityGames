<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>


<div>
	<script type="text/javascript">
		
	</script>
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
							<c:set var="url" value="${g.hpURL}" />

						</c:when>
					</c:choose>
					<div>
						<a href="${url}"> <img src="${thUrl}" alt="picture" />
							<div style="text-align: center">${g.gameName}</div> </a>
					</div>
				</c:forEach>

			</div>
			<div style="margin-left: 30px;">
				<div style="display: inline-block;">
					<img src="/images/campaign.png" />
				</div>
				<div style="display: inline-block;" class="campaignTitle">${c.title}</div>
			</div>
		</div>
		<!-- end wrapper -->
	</c:forEach>
</div>



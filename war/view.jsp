<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div align="center" class="viewCenter">
	<c:forEach var="g" items="${GameList}" varStatus="loop">
		<div class="gameBox">
			<script type="text/javascript">
				$(function() {
					$("#tabs-${g.key.id}").tabs();
				});
				$(function() {

					$("#out-${g.key.id}").hide();
					$("#change-${g.key.id}").click(function() {
						$("#change-${g.key.id}").css("position", "relative");
						$("#change-${g.key.id}").css("top", "-5px");
						$("#out-${g.key.id}").show();
						$("#br-${g.key.id}").hide();
					});
				});
			</script>

			<div align="right">
				<c:if test="${not empty g.hpURL}">
					<a class="outside"><fmt:message key="outside" /> </a>
				</c:if>
				<fmt:message key="entryDay" />
				：
				<fmt:formatDate value="${g.date}" pattern="MM/dd HH:mm" />
				<br>
			</div>

			<div class="gameView">
				<div class="gameViewHead">
					<div class="bounded gameName">
						<%@ include file="/share/patternDistinction.jsp"%>


						<a href="${url}" class="title"> ${g.gameName} </a>
					</div>
					<div class="floatRight">
						<ul>
							<li class="noListPoint"><fmt:message key="access" />：${g.access}</li>
							<li class="noListPoint"><fmt:message key="comment" />：${g.comment}</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="bounded tags">
					<span class="fontRed"><fmt:message key="registerTag" /> </span>
					<c:forEach var="ft" items="${g.fixTags}">
						<a href="/search?tag=${ft.name}" class="tagsName">${ft.name}</a>

					</c:forEach>
				</div>
				<div class="gameURL">
					<a href="${url}"><img src="${thUrl}" width="150px"
						height="150px" class="image" /> </a>

				</div>


				<div class="detail">
					<div id="tabs-${g.key.id}" class="tabsHeight">
						<ul>
							<li><a href="#tab1-${g.key.id}"><fmt:message
										key="explanation" /> </a>
							</li>
							<li><a href="#tab2-${g.key.id}"><fmt:message
										key="operation" /> </a>
							</li>

						</ul>

						<div id="tab1-${g.key.id}">
							<a class="contents">${g.contents}</a>

						</div>
						<div id="tab2-${g.key.id}">
							<a class="operations">${g.operations}</a>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>

		</div>
		<c:if test="${loop.count mod 2 == 0}">
</div>
<div align="center" class="viewCenter">
	</c:if>

	</c:forEach>
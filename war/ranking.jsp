<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ranking</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/ranking.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<script src="/js/jquery.easing.min.js"></script>
<script type="text/javascript">
	function pageselectCallback(page_index, jq) {
		var new_content = jQuery(
				'#hiddenresult div.result:eq(' + page_index + ')').clone();
		$('#Searchresult').empty().append(new_content);
		return false;
	}

	function initPagination() {
		var num_entries = jQuery('#hiddenresult div.result').length;
		$(".Pagination").pagination(num_entries, {
			callback : pageselectCallback,
			items_per_page : 1
		});
	}
	$(function() {
		initPagination();
		$(".tabs").tabs();
	});
</script>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<div align="center" class="viewCenter">

		<c:forEach var="rg" items="${rankingGameList}" varStatus="loop">
			<div style="text-align: left;">
				<div style="font-size: 25px; display: inline-block;">第${loop.count}位</div>
				<div style="display: inline-block; margin-left: 30px;">${rg.deltaPoint}
					Point</div>
			</div>
			<c:set var="g" value="${rg.gameRef.model}" />
			<div class="gameBox">
				<div align="right">
					<script type="text/javascript">
						$(function() {

						});
					</script>
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
					<div class="bounded tags"></div>
					<div class="gameURL">
						<a href="${url}"><img src="${thUrl}" width="85px"
							height="85px" class="image" /> </a>

					</div>


					<div class="detail">
						<div class="tabs" class="tabsHeight">
							<ul>
								<li><a href="#tab-${g.key.id}"><fmt:message
											key="explanation" /> </a>
								</li>
							</ul>
							<div
								style="text-align: left; position: absolute; left: 105px; top: 10px;"
								class="bounded">
								<span class="fontRed"><fmt:message key="registerTag" />
								</span>
								<c:forEach var="ft" items="${g.fixTags}">
									<a href="/search?tag=${ft.name}" class="tagsName">${ft.name}</a>

								</c:forEach>
							</div>
							<div id="tab-${g.key.id}">
								<div class="contents" style="text-align: left;">"${g.contents}"</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

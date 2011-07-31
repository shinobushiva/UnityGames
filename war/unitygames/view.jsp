<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
.pagination {
	font-size: 80%;
}

.pagination a {
	text-decoration: none;
	border: solid 1px #AAE;
	color: #15B;
}

.pagination a,.pagination span {
	display: inline-block;
	padding: 0.3em 0.5em;
	margin-right: 5px;
	margin-bottom: 5px;
	min-width: 1em;
	text-align: center;
}

.pagination .current {
	background: #26B;
	color: #fff;
	border: solid 1px #AAE;
}

.pagination .current.prev,.pagination .current.next {
	color: #999;
	border-color: #999;
	background: #fff;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".tabs").tabs();
		initPagination()
	});
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
</script>
<div align="center" class="viewCenter">
	<div id="hiddenresult" style="display: none;">
		<div class="result">
			<c:forEach var="g" items="${GameList}" varStatus="loop">
				<div class="gameBox">
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
							<div class="tabs" class="tabsHeight">
								<ul>
									<li><a href="#tab1-${g.key.id}"><fmt:message
												key="explanation" /> </a>
									</li>
								</ul>

								<div id="tab1-${g.key.id}">
									<div class="contents" style="text-align: left;">${g.contents}</div>

								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<c:if test="${loop.count mod 6 == 0 }">
		</div>
		<div class="result">
			</c:if>
			</c:forEach>
		</div>
	</div>
</div>
<div class="Pagination" align="center"></div>
<div id="Searchresult" align="center"></div>
<div class="Pagination" align="center" style="margin: 10px;"></div>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>user Index</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/userPage.css" rel="stylesheet" type="text/css"/>
<%@ include file="/share/js.jsp"%>

<%--ページング --%>
<script type="text/javascript">
	function pageselectCallback(page_index, jq) {
		var new_content = jQuery(
				'#hiddenresult div.result:eq(' + page_index + ')').clone();
		$('#Searchresult').empty().append(new_content);
		return false;
	}
	function initPagination() {
		var num_entries = jQuery('#hiddenresult div.result').length;
		$("#Pagination").pagination(num_entries, {
			callback : pageselectCallback,
			items_per_page : 1
		});
	}
	function initButtons() {
		var pg = $('#Pagination');
		$('#btnPrev').click(function() {
			pg.trigger('prevPage');
		});
		$('#btnNext').click(function() {
			$('#Pagination').trigger('nextPage');
		});
		$('#btnSet').click(function() {
			pg.trigger('setPage', [ 1 ]);
		});
	}

	$(document)
			.ready(
					function() {
						initPagination();
						initButtons();

						$
								.getJSONP(
										"http://api.twitter.com/1/users/show.json?id=${twitterId}&callback={callback}",
										function(e) {

											$("#twitterImage").attr("src",
													"" + e.profile_image_url);
											$("#twitterScreenName").html(
													"@" + e.screen_name);
											$("#twitterName").html("" + e.name);

										});
					});
</script>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<div class="tweetFont">
		<div>
			<a href="http://twitter.com/#!/${user}">
				<div class="twitterImageFrame">
					<img id="twitterImage" />
				</div>
				<div class="marginTopTwenty">
					<span id="twitterScreenName"></span><span id="twitterName"></span>
					<c:if test="${showUser}">
						<span> <a href="/user/${twitter.screenName}?edit=edit"><fmt:message
									key="edit" /> </a> </span>
					</c:if>
				</div> </a>
		</div>

		<hr class="hrOne clear" />

	</div>
	<div class="floatLeft">
		<div>
			<div class="Introduction">
				<fmt:message key="self.introduction" />
			</div>
			<div id="twitterDescription">${um.myself}</div>
		</div>
		<div>
			<div class="webFont marginTopTen">Web:</div>
			<div id="web" class="inlineBlock">${um.webUrl}</div>
		</div>
		<div class="gameFrame">
			<div class="management">
				<fmt:message key="game.history" />
			</div>
			<hr class="hrTwe">
			<div id="hiddenresult">
				<div class="result">
					<c:forEach var="g" items="${gameList}" varStatus="loop">
						<%@ include file="/share/patternDistinction.jsp"%>
						<div id="ug${g.key.id}" class="games">
							<a href="/unitygames/game/ug${g.key.id}"><img src="${thUrl}"
								width="80" height="80" /><br>
								<div class="bounded gameName">${g.gameName}</div> </a>
						</div>
						<c:if test="${loop.count mod 3 == 0 }">
				</div>
				<div class="result">
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div id="Pagination" align="center"></div>
			<br class="clear" />
			<div id="Searchresult">This content will be replaced when
				pagination inits.</div>
			<div align="center" class="marginTopThirty">
				<form method="get" action="#">
					<button type="button" id="btnPrev">Prev</button>

					<button type="button" id="btnNext">Next</button>
				</form>
			</div>
		</div>

	</div>
	<div class="floatLeft">
		<div class="marginLeftForty">
			<div class="tweetFont">
				<fmt:message key="tweet.other" />
			</div>
			<c:forEach var="t" items="${tweet}">

				<script type="text/javascript">
					$(function() {
						var url = "http://twitter.com/statuses/show/${t.tweetId}.json?callback={callback}";
						$.getJSONP(url, function(obj) {
							var s = "";

							s += "<div><div　style='width: 250px;'>" + obj.text
									+ "</div>";
							s += "<div>" + jQuery.timeago(obj.created_at)
									+ "</div></div>";
							$("#${t.tweetId}").html(s);
						});
					});
				</script>

				<hr />
				<div class="twitterIds" id="${t.tweetId}"></div>

			</c:forEach>
		</div>
	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

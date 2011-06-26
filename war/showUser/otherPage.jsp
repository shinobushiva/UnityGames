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
	<div style="font-size: 15px;" id="twitter">
		<div>
			<a href="http://twitter.com/#!/${user}">
				<div style="float: left; margin-left: 10px; margin-top: 10px;">
					<img id="twitterImage" style="width: 48px; height: 48px;" />
				</div>
				<div style="margin-top: 20px;">
					<span style="font-size: xx-large;" id="twitterScreenName"></span><span
						style="margin-left: 30px;" id="twitterName"></span>
					<c:if test="${showUser}">
						<span> <a href="/user/${twitter.screenName}?edit=edit"><fmt:message
									key="edit" /> </a>
						</span>
					</c:if>
				</div> </a>
		</div>

		<hr style="margin-top: 50px; width: 900px; clear: both;" />

	</div>
	<div style="float: left;">
		<div>
			<div
				style="font-size: 20px; margin-top: auto; margin-bottom: auto; display: inline-block;">
				<fmt:message key="self.introduction" />
			</div>
			<div style="margin-top: 10px; display: inline-block; width: 450px;"
				id="twitterDescription">${um.myself}</div>
		</div>
		<div>
			<div
				style="display: inline-block; font-size: 20px; margin-top: 10px;">Web:</div>
			<div id="web" style="display: inline-block;">${um.webUrl}</div>
		</div>
		<div style="width: 500px;">
			<div style="font-size: 20px; margin-top: 50px;">
				<fmt:message key="game.history" />
			</div>
			<hr style="width: 500px;">
			<div id="hiddenresult" style="display: none;">
				<div class="result">
					<c:forEach var="g" items="${gameList}" varStatus="loop">
						<%@ include file="/share/patternDistinction.jsp"%>
						<div
							style="display: inline-block; text-align: center; z-index: 1; cursor: pointer;"
							id="ug${g.key.id}">
							<a href="/unitygames/game/ug${g.key.id}"><img src="${thUrl}"
								width="80" height="80" /><br>
								<div class="bounded" style="width: 150px; text-align: center;">${g.gameName}</div>
							</a>
						</div>
						<c:if test="${loop.count mod 3 == 0 }">
				</div>
				<div class="result">
					</c:if>
					</c:forEach>
				</div>
			</div>
			<div id="Pagination" align="center"></div>
			<br style="clear: both;" />
			<div id="Searchresult">This content will be replaced when
				pagination inits.</div>
			<div align="center" style="margin-top: 30px;">
				<form method="get" action="#">
					<button type="button" id="btnPrev"
						style="width: 100px; height: 30px;">Prev</button>

					<button type="button" id="btnNext"
						style="margin-right: 50px; width: 100px; height: 30px;">Next</button>
				</form>
			</div>
		</div>

	</div>
	<div style="float: left;">
		<div style="margin-left: 40px;">
			<div style="font-size: 20px;">
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
				<div style="width: 300px; height: 50px; border: 1px;"
					id="${t.tweetId}"></div>

			</c:forEach>
		</div>
	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

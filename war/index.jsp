<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords"
	content="Unity,Unity3D,UnityGames,Game,tutorial,チュートリアル">
<meta name="description"
	content="Unity3Dゲーム投稿サイトです。ゲームを触って学ぶそんなサイト。チュートリアルも充実してます。日本のUnityゲーム投稿サイトならここ！">
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://unity-games.appspot.com/api/rss" />
<%@ include file="/share/css.jsp"%>
<link href="/css/topPage.css" rel="stylesheet" type="text/css"/>
<%@ include file="/share/js.jsp"%>
<title>UnityGames</title>
<script type="text/javascript">
	$(function() {
		$("#campaign").css("visibility", "hidden");
		$("#campaign").load("/campaignList", null, function() {
			var tid; //timeoutID
			initMovingBoxes(function() {
				$("#campaign").css("visibility", "visible");
			}, function() {
				clearTimeout(tid);
				tid = setTimeout(function() {
					forwardFunc();
				}, 2500);
			});
		});
	});
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-22824102-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
</head>
<body style="">
	<%@ include file="/share/header.jsp"%>
	<div>

		<div id="campaign"></div>
	</div>



	<div class="center">
		<div>
			<div id="search"><%@ include file="/share/search.jsp"%></div>
			<div class="inline">
				<div class="newGameLine">
					<a href="/unitygames/" class="decorationNon"> <fmt:message
							key="newGame" /><img src="/images/click.png"
						class="imagePosition" /> </a> <span><a href="/api/rss"><img
							alt="rss" src="/images/rss.png" width="32"> </a> </span>
				</div>
				<c:forEach var="g" items="${newGameList}">
					<div class="game">
						<%@ include file="/share/patternDistinction.jsp"%>
						<a href="${url}"> <img src="${thUrl}" width="150" height="150" /><br>
							<div class="bounded">${g.gameName}</div> </a>
					</div>
				</c:forEach>

				<div class="newGameLine">
					<fmt:message key="ranking" />
				</div>
				<div>
					<c:forEach var="g" items="${rankingGameList}" varStatus="loop">
						<div class="game">
							<span class="gameFont"><fmt:message key="best1" />${loop.count}<fmt:message
									key="best2" /> </span><br>
							<%@ include file="/share/patternDistinction.jsp"%>
							<a href="${url}"> <img src="${thUrl}" width="150"
								height="150" /><br>
								<div class="bounded">${g.gameName}</div> </a>
						</div>
					</c:forEach>
				</div>

			</div>

		</div>
	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

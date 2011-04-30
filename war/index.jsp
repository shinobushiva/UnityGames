<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords"
	content="Unity,Unity3D,UnityGames,Game,tutorial,チュートリアル">
<meta name="description"
	content="Unity3Dゲーム投稿サイトです。ゲームを触って学ぶそんなサイト。チュートリアルも充実してます。日本のUnityゲーム投稿サイトならここ！">
<%@ include file="/share/css.jsp"%>
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
</script>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<div>

		<div id="campaign" style="margin-top: 30px; margin-bottom: 20px;"></div>
	</div>

	<div>

		<div style="display: inline-block;">
			<div>
				<div style="margin-bottom: 20px; display: inline-block;"><%@ include
						file="/share/search.jsp"%></div>
				<a href="/login/oAuth"><div style="display: inline-block;">login</div></a>
			</div>
			<div
				style="border: solid; border-color: #e1e1e1; border-radius: 20px; float: right; width: 250px; height: 600px;">
				<div style="text-align: center;">更新情報</div>
			</div>
			<div class="newGameLine">

				ランキング<img src="/images/click.png"
					style="position: relative; top: 2px;" />
			</div>
			<c:forEach var="g" items="${rankingGameList}">
				<div style="display: inline-block; text-align: center;">
					<%@ include file="/share/patternDistinction.jsp"%>
					<a href="${url}"> <img src="${thUrl}" width="150" height="150" /><br>
						<div>${g.gameName}</div> </a>
				</div>
			</c:forEach>
			<a href="/unitygames/" style="text-decoration: none;"><div
					class="newGameLine">
					新着ゲーム<img src="/images/click.png"
						style="position: relative; top: 2px;" />
				</div> </a>
			<c:forEach var="g" items="${newGameList}">
				<div style="display: inline-block; text-align: center;">
					<%@ include file="/share/patternDistinction.jsp"%>
					<a href="${url}"> <img src="${thUrl}" width="150" height="150" /><br>
						<div>${g.gameName}</div> </a>
				</div>
			</c:forEach>

		</div>

	</div>
</body>
</html>

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
<body style="border: solid;">
	<%@ include file="/share/header.jsp"%>
	<div>

		<div id="campaign" style="margin-top: 30px; margin-bottom: 20px;"></div>
	</div>



	<div style="display: inline-block;">
		<div>
			<div
				style="margin-bottom: 20px; margin-left: 200px; display: inline-block;"
				id="search"><%@ include file="/share/search.jsp"%></div>
<div style=" display: inline-block;">
			<div
				style="border: solid; width: 250; margin-left: 125px;clear:both;">
				<c:if test="${login == 'no' }">
					<script type="text/javascript">
						$(function() {
							$("#search").css("","");

						});
					</script>
					<div>
						<div style="height: 73px;"align="center">
							<a href="/login/oAuth"><div><img src="/images/logo/twitter_logo.png"/></div><div style="font-size: 30px;color: #1F98C7;">ログイン</div></a>
						</div>
					</div>
				</c:if>
				<c:if test="${login == 'yes' }">
					<%--	普段ユーザー本人が見る情報	 --%>
					<div style="height:73px;">
						<div style="float: left;">
							<img src="${p}" />
						</div>
						<div>

							<div
								style="text-align: center; font-weight: 900; font-size: 15px;">
								${userName}</div>
							<div
								style="text-align: center; font-size: 20px; margin-top: 5px;">活動履歴(未実装)</div>
							<div style="display: inline-block; padding-left: 10px;">
								<a href="/user/?name=${userName}" style="font-size: x-small; color: #1F98C7;">情報登録/修正</a>
							</div>
							<div style="display: inline-block; margin-left: 20px;">
								<a href="/login/logOut" style="font-size: 15px; color: #1F98C7;">ログアウト</a>
							</div>
						</div>
					</div>
				</c:if>
			</div>
</div>
			<div
				style="border: solid; border-color: #e1e1e1; border-radius: 20px; float: right; width: 250px; height: 600px;">
				<div style="text-align: center;">更新情報</div>
			</div>
			<div class="newGameLine">

				ランキング<img src="/images/click.png"
					style="position: relative; top: 2px;" />
			</div>
			<div>
				<c:forEach var="g" items="${rankingGameList}">
					<div style="display: inline-block; text-align: center;">
						<%@ include file="/share/patternDistinction.jsp"%>
						<a href="${url}"> <img src="${thUrl}" width="150" height="150" /><br>
							<div class="bounded" style="width: 150px;">${g.gameName}</div> </a>
					</div>
				</c:forEach>
			</div>
			<a href="/unitygames/" style="text-decoration: none;"><div
					class="newGameLine">
					新着ゲーム<img src="/images/click.png"
						style="position: relative; top: 2px;" />
				</div> </a>
			<c:forEach var="g" items="${newGameList}">
				<div style="display: inline-block; text-align: center;">
					<%@ include file="/share/patternDistinction.jsp"%>
					<a href="${url}"> <img src="${thUrl}" width="150" height="150" /><br>
						<div class="bounded" style="width: 150px;">${g.gameName}</div> </a>
				</div>
			</c:forEach>

		</div>

	</div>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns:og="http://ogp.me/ns#"
	xmlns:mixi="http://mixi-platform.com/ns#"
	xmlns:fb="http://www.facebook.com/2008/fbml"
	xmlns:gr="http://gree.jp/ns">
<head>
<meta property="og:title" content="${g.gameName}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${g.gameName}</title>
<meta name="mixi-check-robots" content="noimage" />
<%@ include file="/share/css.jsp"%>
<link href="/css/game.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<script type="text/javascript" src="/js/jquery.createvideo.js"></script>
<script type="text/javascript" src="/js/newJS/game.js"></script>
<script type="text/javascript">
<%--　Analytics　--%>
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-22824102-2']);
_gaq.push(['_trackPageview']);

(function () {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
})();
jQuery.extend(jQuery.validator.messages, {
    required: "<br><fmt:message key='not.comment'/>"
});

<%-- Initialization --%> 
$(function () {
      gameInfo(${g.key.id}, ${jscode});
});
</script>

</head>
<body>

	<input type="hidden" id="gameKey" name="gameKey" value="${f:h(g.key)}">

	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>
	<div>
		<div class="marginTopTwenty">
			<%--  Tags --%>
			<b class="registerTag"><fmt:message key="registerTag" /> </b> <span
				id="tagUpload"></span>
		</div>
		<div class="gameName">
			<%-- Game Name --%>
			<span class="gameNameName">${g.gameName} </span>
		</div>
	</div>

	<%--	ゲーム投稿者情報	 --%>

	<div class="user">
		<div>
			<fmt:message key="contributor" />
		</div>
		<%--	Twitterアカウント	--%>
		<c:if test="${empty g.pass}">
			<script type="text/javascript">
		function(){getTwitterInfo(${twitterId});}
		</script>
			<div>
				<a class="userPage" target="Twitter">
					<div class="floatLeft">
						<img id="twitterImage" />
					</div>
					<div id="twitterName"></div> </a>
			</div>
		</c:if>
		<%--	変更パスワード入力	--%>
		<c:if test="${not empty g.pass}">
			<div class="guestFrame">
				<div class="faceImage">
					<img src="/images/face.png" />
				</div>

				<div class="guestName">&nbsp;Guest</div>
				<div class="passFrame">
					<script type="text/javascript">
						$(function() {

							$("#out-${g.key.id}").hide();
							$("#change-${g.key.id}").click(
									function() {
									changePass(${g.key.id});
									});
						});
					</script>
					<form action="/unitygames/upload/change/guest${g.key.id}"
						method="post">
						<a id="change-${g.key.id}" class="fontXsmall"><fmt:message
								key="change.delete" /> </a><a id="out-${g.key.id}" class="out"><input
							type="password" name="Pass" class="passwordBox"><input
							type="hidden" name="key" value="${f:h(g.key)}">
							<button type="submit">
								<img id="sub" src="/images/red.gif" />
							</button> </a>
					</form>
				</div>
			</div>

		</c:if>
	</div>
	<div class="SocialButtons">
		<%-- Social Buttons --%>
		<ul>
			<li class="SocialInline"><script type="text/javascript"
					src="http://apis.google.com/js/plusone.js"> {lang: 'ja'} </script>
				<g:plusone size="medium"></g:plusone>
			</li>
			<li class="SocialInlineFaceBook"><fb:like layout="button_count"></fb:like>
				<a id="fb-root"></a> <script>
			window.fbAsyncInit = function() {
				FB.init({
					appId : '214224191925233',
					status : true,
					cookie : true,
					xfbml : true
				});
			};
			(function() {
				var e = document.createElement('script');
				e.type = 'text/javascript';
				e.src = document.location.protocol
						+ '<fmt:message key="facebook"/>';
				e.async = true;
				document.getElementById('fb-root').appendChild(e);
			}());
		</script></li>
			<%--<li class="SocialInline"><a href="http://mixi.jp/share.pl"
				class="mixi-check-button"
				data-key="42bc93a615261cdd8e17e115918eb36ebf60a729"
				data-button="button-1"></a> <script type="text/javascript"
					src="http://static.mixi.jp/js/share.js"></script>
			</li>
			<li class="SocialInline"><iframe
					src="http://share.gree.jp/share?url=http%3A%2F%2Funity-games.appspot.com%2Funitygames%2Fgame%2Fug${g.key.id}&type=0&height=20"
					scrolling="no" frameborder="0" marginwidth="0" marginheight="0"
					style="border: none; overflow: hidden; width: 75px; height: 20px;"
					allowTransparency="true"></iframe>
			</li>
			--%>
			<li class="SocialInline"><a href="http://twitter.com/share"
				class="twitter-share-button" data-count="horizontal"
				data-via="UGames #UnityGames"
				data-lang="<%=request.getLocale().getLanguage()%>"></a> <script
					type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
			</li>
		</ul>

	</div>

	<div id="tabs" class="clear">
		<%-- Top Tabs --%>
		<ul>
			<li><a href="#tab1"><span><fmt:message
							key="explanation" /> </span> </a>
			</li>
			<li><a href="#tab2"><span><fmt:message
							key="operation" /> </span> </a>
			</li>
			<li><a href="#tagg"><span><fmt:message
							key="registTag" /> </span> </a>
			</li>
			<%--
			<span>
				<button id="commentToggle"
					style="line-height: 2em; display: inline-block;">コメントクラウド表示/非表示</button>
			</span>
			--%>
			<span class="detail"> <fmt:message key="entryDay" />：<fmt:formatDate
					value="${g.date}" pattern="MM/dd" /> <fmt:message
					key="LastEntryDay" />：<fmt:formatDate value="${g.lastDate}"
					pattern="MM/dd" /><br> <fmt:message key="access" />：${g.access}
				<fmt:message key="comment" />:${g.comment}</span>

		</ul>
		<div id="tab1">
			<pre>${g.contents}</pre>
		</div>
		<div id="tab2">
			<pre>${g.operations}</pre>
		</div>
		<div id="tagg">
			<input type="text" name="tag" id="tagReg">
			<button class="searchButton black" id="tagButton">
				<fmt:message key="button.regist" />
			</button>
			<div id="tagUpload2"></div>
		</div>
	</div>
	<%-- Game --%>
	<div class="gameMargin">
		<div id="game-center" style="width: ${width}px; height:${height}px;"
			align="center">
			<div id="loaded">
				<c:choose>
					<c:when test="${empty g.thumbNailURL}">
						<img src="/unitygames/thumbNail?id=${f:h(g.key.id)}"
							width="${width}" height="${height}" class="opacity" />
					</c:when>
					<c:when test="${not empty g.thumbNailURL}">
						<img src="${g.thumbNailURL}" border="1" width="${width}"
							height="${height}" class="opacity" />
					</c:when>
				</c:choose>
				<button id="load" style=" right: 0px; top: -${height/3 }px">
					<img src="/images/Start.png">
				</button>
			</div>
		</div>
	</div>

	<div class="clear">

		<div class="ui-widget-content ui-tabs-panel">

			<div>
				<span class="commentary"> <fmt:message key="code" /> </span>
				<c:if test="${g.editable}">
					<c:if test="${twitter != null}">
						<button id="edit" class="edit">
							<fmt:message key="edit" />
						</button>
					</c:if>
				</c:if>
			</div>

			<div>
				<textarea id="codeEditArea" class="codeEdit" name="codeEditArea">${g.code}</textarea>
			</div>
			<div id="code" class="codeEdit"></div>
			<div id="saveCancelButtons">
				<button id="save" class="edit">
					<fmt:message key="save" />
				</button>
				<button id="cancel" class="edit">
					<fmt:message key="cancel" />
				</button>
			</div>
		</div>

		<div class="ui-widget-content ui-tabs-panel commentFrame marginTopTen">
			<a href="#comment"><span><fmt:message key="comment" /> </span> </a>
			<div id="comment">
				<div>
					<div>
						<input type="text" id="commentInput" name="comment"
							class="required">
						<button class="searchButton black" id="commentUp">
							<fmt:message key="button.comment" />
						</button>
					</div>

					<span class="success"><fmt:message key="commented" /> </span>
				</div>
				<div id="commentLoad"></div>
			</div>
		</div>


	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

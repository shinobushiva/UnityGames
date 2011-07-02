<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${twitter.screenName}のページ</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/userPage.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<script type="text/javascript" src="/js/newJS/myPage.js"></script>

<%--ページング --%>
<script type="text/javascript">
	$(function() {
	myPageInfo(${um.userId},${twitterId});
	});
</script>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<input type="hidden" name="userId" id="ud" value="${um.userId}" />



	<div class="marginLeftFifty">
		<div>
			<div class="twitterImageFrame">
				<img id="twitterImage" />
			</div>
			<div class="inlineBlock"></div>
			<div class="marginTopTwenty">
				<span id="twitterScreenName"></span><span id="twitterName"></span> <span><a
					href="/user/${twitter.screenName}"><fmt:message key="edit.end" />
				</a> </span>
			</div>

		</div>
		<hr class="hrOne" />
		<div class="marginTopTen">
			<div class="Introduction">
				<fmt:message key="self.introduction" />
			</div>
			<div id="loadMyself">
				<span id="myself"></span><span id="myselfEdit"><textarea
						id="myselfText"></textarea><br> <span id="myself-cancel"><fmt:message
							key="cancellation" /> </span>
					<button id="myself-change">
						<fmt:message key="button.change" />
					</button> </span>
			</div>
		</div>
		<div>
			<div class="webFont">Web:</div>
			<div id="web">

				<sapn id="url"></sapn>
				<span id="reg"><input type="text" name="webUrl" id="wu"
					value="">
					<button id="web-change">
						<fmt:message key="button.change" />
					</button> <span id="web-cancel"><fmt:message key="cancellation" /> </span>
				</span>
			</div>
		</div>

		<div>
			<%-- 
		セーブデータ管理(未実装)
		<div style="overflow: auto; height: 400px;">
			<div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">ゲーム名</div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">削除</div>
			</div>
			
			<div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">○○○</div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">
					<button>削除</button>
				</div>
			</div>
		
		</div>
		<hr />
		--%>
			<div class="management">
				<fmt:message key="user.management" />
			</div>
			<hr class="hrTwe">
			<div class="gameListFrame">

				<div id="hiddenresult">
					<div class="result">
						<c:forEach var="g" items="${gameList}" varStatus="loop">
							<%@ include file="/share/patternDistinction.jsp"%>
							<div id="ug${g.key.id}" class="games">
								<a href="/unitygames/upload/change/ug${g.key.id}"> <img
									src="${thUrl}" width="80px" height="80px" /><br>
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
				<div id="Searchresult"></div>
				<div align="center" class="marginTopThirty">
					<form method="get" action="#">
						<button type="button" id="btnPrev">Prev</button>

						<button type="button" id="btnNext">Next</button>
					</form>
				</div>


			</div>
			<div class="twitterFrame">


				<div id="twitter">
					<div class="tweetFrame">
						<div class="tweetFont">
							<fmt:message key="tweet" />
						</div>
						<div>
							<div class="inline">
								<textarea id="tweet"></textarea>
							</div>
							<div id="tweetButton">
								<input type="checkbox" id="noHashtag" value="noHash">
								<fmt:message key="dont.tag" />
								<button id="tweetUpdate">
									<fmt:message key="button.tweet" />
								</button>

							</div>
						</div>
					</div>


					<div class="clear">

						<c:forEach var="t" items="${tweet}">

							<script type="text/javascript">
								$(function() {
								tweet(${t.tweetId});
								});
							</script>

							<hr />
							<div id="${t.tweetId}" class="twitterIds"></div>

						</c:forEach>
						<hr />
					</div>

				</div>

			</div>
		</div>
		<%@ include file="/share/footer.jsp"%>
</body>
</html>

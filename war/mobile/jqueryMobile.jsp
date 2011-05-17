<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>mobile Index</title>
<%@ include file="/mobile/share/jqueryMobile.jsp"%>
</head>
<body>
	<!-- home ページ -->
	<div id="home" data-role="page">
		<div data-role="header">
			<h1>UnityGames</h1>
		</div>
		<div data-role="content" align="center">
			<p>
				<img src="/mobile/images/Logo.png" width="200px">mobile
			</p>
			<ul data-role="listview" data-theme="g">

				<c:forEach var="g" items="${gameList}">
					<%@ include file="/share/patternDistinction.jsp"%>
					<li><a href="#${g.key.id}" data-transition="slide"><img src="${thUrl}" />${g.gameName}</a>
					</li>

				</c:forEach>
			</ul>

		</div>
	</div>
	<!-- /home ページ -->

	<!--説明ページ-->
	<c:forEach var="g" items="${gameList}">
		<%@ include file="/share/patternDistinction.jsp"%>
		<div id="${g.key.id}" data-role="page">
			<div data-role="header">
				<h1>${g.gameName}</h1>
			</div>
			<div data-role="content" align="center">

				<div>
					<img src="${thUrl}" width="300" height="300" />
				</div>
				<div>${g.gameName }</div>
				<div>
					<div style="margin: 10px; text-align: left;">ゲーム説明</div>
					<div>${g.contents }</div>
				</div>
				<div>
					<div style="margin: 10px; text-align: left;">操作説明</div>
					<div>${g.operations}</div>
				</div>
				<div style="margin: 10px;">プレイ数：${g.access}コメント数：${comment}</div>
				<div style="margin: 50px;">
					<a href="" data-role="button" data-theme="a">Play!!</a>
				</div>
			</div>
			<div data-role="footer">
				<h1>${g.gameName}</h1>
			</div>
		</div>
	</c:forEach>
	<!-- /説明ページ -->
</body>
</html>

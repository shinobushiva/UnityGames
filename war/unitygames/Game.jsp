<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html >
	<head>
		<title>Unity Web Player | WebPlayer</title>
		<script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
		<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
		
		<style type="text/css">
		<!--
		body {
			font-family: Helvetica, Verdana, Arial, sans-serif;
			background-color: white;
			color: black;
			text-align: center;
		}
		a:link, a:visited {
			color: #000;
		}
		a:active, a:hover {
			color: #666;
		}
		p.header {
			font-size: small;
		}
		p.header span {
			font-weight: bold;
		}
		p.footer {
			font-size: x-small;
		}
		div.content {
			margin: auto;
			width: 600px;
		}
		div.missing {
			margin: auto;
			position: relative;
			top: 50%;
			width: 193px;
		}
		div.missing a {
			height: 63px;
			position: relative;
			top: -31px;
		}
		div.missing img {
			border-width: 0px;
		}
		div#unityPlayer {
			cursor: default;
			height: 450px;
			width: 600px;
		}
		-->
		</style>
	</head>
	<body>
	<script type="text/javascript">
		<!--
		function GetUnity() {
			if (typeof unityObject != "undefined") {
				return unityObject.getObjectById("unityPlayer");
			}
			return null;
		}
		if (typeof unityObject != "undefined") {
		${play}			
		}
		-->
		</script>

	
		<p class="header"><span>Unity Web Player | </span>WebPlayer</p>
		<table class="purchase-options" align="center">
		<tr class="top"><td><h2>ゲーム説明</h2>${Contents}</td></tr>
		<tr class="bottom"><td><h2>操作内容</h2>${Operations}</td></tr>
		</table>
		<div class="content">
			<div id="unityPlayer">
				<div class="missing">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">
						<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />
					</a>
				</div>
			</div>
		</div>
		<p class="footer">&laquo; created with <a href="http://unity3d.com/unity/" title="Go to unity3d.com">Unity</a> &raquo;</p>
	</body>
</html>

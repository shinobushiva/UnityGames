<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html >
	<head>
		<title>${g.gameName}</title>
		<script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
		<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
		<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
		<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
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
			font-size: 30px;
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
<script>
$(function() {   
	  $('#tabs').tabs();   
	});  
	</script>
		 
		   
		
	
		<p class="header" align="left"><span>${g.gameName}</span></p>
		<table class="purchase-options" align="center">
		<tr class="top"><td>
		<div id="tabs"> 
		<ul>  
      <li><a href="#tab1"><span>ゲーム説明</span></a></li>  
      <li><a href="#tab2"><span>操作内容</span></a></li>  
	<div align="right"><span>投稿日：<fmt:formatDate  value="${g.date}" pattern="MM/dd" /></span><br><span>最終更新日：<fmt:formatDate  value="${g.lastDate}" pattern="MM/dd" /></span></div> 	
      </ul>   
		 
		<div id="tab1">  
		<p>${g.contents}</p>
		</div >
		<div id="tab2">
		<p>${g.operations}</p>
		</div>
		</div></td></tr>
		  
		<tr class="bottom"><td></td></tr>
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
		
	</body>
</html>

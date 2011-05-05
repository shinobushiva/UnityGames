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

</head>
<body>

	<script type="text/javascript">
		$(function() {
			$.ajax({
				type : "get",
				url : "/test",
				dataType:"json",
				data : "id=3398",
				success : function(e) {
					var html = "";
					var gms = e.gameData;
				//	alert(gms);
					
				//	for (i in gms) {

					//	var g = gms[i];

						//html += "<div>";
						html += "" + e.gameData;
					//	html += "</div>";
			//		}
					$('#Load').html(html);
				}
			})
		});
	</script>

	<div id="Load"></div>

</body>
</html>

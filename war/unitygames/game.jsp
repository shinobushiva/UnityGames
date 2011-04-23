<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns:og="http://ogp.me/ns#"
	xmlns:mixi="http://mixi-platform.com/ns#"
	xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${g.gameName}</title>
<meta name="mixi-check-robots" content="noimage" />
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<style type="text/css">
<!--
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

td.comment {
	margin-right: 100px;
	position: relative;
	width: 300px;
}
-->
</style>

<script type="text/javascript">
function GetUnity() {
	if (typeof unityObject != "undefined") {
		return unityObject.getObjectById("unityPlayer");
	}
	return null;
}
</script>

<script type="text/javascript">
	jQuery.extend(jQuery.validator.messages, {
		required : "<br><fmt:message key='not.comment'/>"
	});

	<%-- Initialization --%>
	$(function() {
		$(".success").hide();
		$("#fo").validate();
		$("#commentUp").click(function() {
			$(".error").hide();
			$(".success").show();

			$(".success").fadeOut("slow");
			var a = $("#commentInput").serialize();
			var b = $("#gameKey").serialize();
			$.ajax({
				type : "post",
				url : "/commentUp",
				data : a+"&"+b,
				success : function() {
					loadComments();
				}

			});
			$("#commentInput").val("");
		});

		$('#tabs').tabs();
		$('#contentTab').tabs();

		updateTags();
		loadComments();

		$("#reload").hide();
		$("#ttt").hide();
		$("#tt").click(function() {
			$("#ttt").show();
		});
		$("#tagButton").click(function() {

			var a = $("#tagReg").serialize();
			var b = $("#gameKey").serialize();

			$.ajax({
				type : "post",
				url : "/tagRegist",
				data : a + "&" + b,
				success : updateTags
			});

			$('#tagReg').val("");
		});

		$("#load,#reload").click(loadClicked);

	});

	function loadComments() {

		$.ajax({
			type : "post",
			url : "/ajax/commentLoad",
			data : "id=${g.key.id}",
			success : function(e) {
				var dateFormat = new DateFormat("yyyy/MM/dd HH:mm:ss");
				
				var html = "";
				var cms = e.comments;
				for (i in cms) {
					var c = cms[i];
					html += "<div>";
					html += "" + c.comment + " " +dateFormat.format(new Date(c.date));
					html += "</div>";
				}
				$('#commentLoad').html(html);
			}

		});
	}

	function updateTags() {
		$.ajax({
			type : "post",
			url : "/ajax/tagUpload",
			data : "id=${g.key.id}",
			success : function(e) {
				var html = "";
				var html2 = "";

				var i;
				var t;

				console.log(e.gameData);
				var tags = e.gameData.fixTags;
				for (i in tags) {
					t = tags[i]

					html += '<a href="/search?tag=' + t.name
							+ '"style="font-size: 2em; padding:3px;" >'
							+ t.name + '</a>';
				}

				tags = e.gameData.tags;
				for (i in tags) {
					t = tags[i]

					html += '<a href="/search?tag=' + t.name
							+ '"style="font-size: 1em; padding:3px;" >'
							+ t.name + '</a>';

					html2 += '<span style="padding:10px;">';
					html2 += '<span style="font-size: 20px;">' + t.name
							+ '</span>';
					console.log(t.key);
					html2 += '<a onclick="deleteTag(\'' + t.key.id + '\');">'
							+ '<img src="/images/delete.png">' + '</a>'
					html2 += '</span>';
				}

				$('#tagUpload').html(html);
				$('#tagUpload2').html(html2);
			}
		});
	}

	function deleteTag(tagId) {

		var a = "tagId=" + tagId;
		var b = $("#gameKey").serialize();

		$.ajax({
			type : "post",
			url : "/tagDelete",
			data : a + "&" + b,
			success : updateTags
		});
	}

	function loadClicked() {
		$("#reload").show();

		$
				.ajax({
					type : "post",
					url : "/ajax/gameLoad",
					data : "id=${g.key.id}",
					success : function(e) {
						var html = "";
						html += '<div class="content">';
						html += '<div id="unityPlayer">';
						html += '<div class="missing">';
						html += '<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">';
						html += '<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />';
						html += '</a>';
						html += '</div>';
						html += '</div>';
						html += '</div>';
						$("#loaded").html(html);
						eval(e.play);
					}
				});
	}
</script>


</head>
<body onload="prettyPrint()">

	<input type="hidden" id="gameKey" name="gameKey" value="${f:h(g.key)}">

	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>

	<div>
		<%--  Tags --%>
		<b style="font-size: x-small; color: red;"><fmt:message
				key="registerTag" /> </b> <span id="tagUpload"></span>
	</div>
	<div style="padding-top: 1em;">
		<%-- Game Name --%>
		<span style="font-size: 2.5em;">${g.gameName} </span>
	</div>

	<div align="right" style="position: relative; top: -2em;">
		<%-- Social Buttons --%>
		<a href="http://mixi.jp/share.pl" class="mixi-check-button"
			data-key="42bc93a615261cdd8e17e115918eb36ebf60a729"
			data-button="button-1"></a>
		<script type="text/javascript" src="http://static.mixi.jp/js/share.js"></script>

		<a href="http://twitter.com/share" class="twitter-share-button"
			data-count="horizontal" data-via="UGames"
			data-lang="<%=request.getLocale().getLanguage()%>"></a>
		<script type="text/javascript"
			src="http://platform.twitter.com/widgets.js"></script>

		<fb:like layout="button_count" width="0"></fb:like>
		<a id="fb-root"></a>
		<script>
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
		</script>
	</div>

	<div id="tabs">
		<%-- Top Tabs --%>
		<ul>
			<li><a href="#tab1"><span><fmt:message
							key="explanation" /> </span> </a>
			</li>
			<li><a href="#tab2"><span><fmt:message
							key="operation" /> </span> </a>
			</li>
			<div align="right">
				<span><fmt:message key="entryDay" />：<fmt:formatDate
						value="${g.date}" pattern="MM/dd" /> </span><br> <span><fmt:message
						key="LastEntryDay" />：<fmt:formatDate value="${g.lastDate}"
						pattern="MM/dd" /> </span>
			</div>
		</ul>
		<div id="tab1">
			<span>${f:h(g.contents)}</span>
		</div>
		<div id="tab2">
			<span>${f:h(g.operations)}</span>
		</div>
	</div>

	<%-- Game --%>
	<div style="margin-top: 1em; margin-bottom: 1em; text-align: right;">
		<div id="loaded">
			<c:choose>
				<c:when test="${empty g.thumbNailURL}">
					<img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"
						width="600" height="450" style="opacity: 0.3; z-index: 0;" />
				</c:when>
				<c:when test="${not empty g.thumbNailURL}">
					<img src="${g.thumbNailURL}" border="1" width="600" height="450"
						style="opacity: 0.3; z-index: 0;" />
				</c:when>
			</c:choose>
			<button id="load"
				style="background-color: transparent; border: 0; z-index: 1; position: relative; left: -400px; top: -180px">
				<img src="/images/Start.png">
			</button>
		</div>
		<div>
			<button id="reload"
				style=" position:relative; left:-512px; z-index: 1;">リロード</button>
		</div>
	</div>

	<div id="contentTab">
		<%-- Bottom Tabs --%>
		<ul>
			<li><a href="#comment"><span><fmt:message
							key="comment" /> </span> </a>
			</li>
			<li><a href="#code"><span><fmt:message key="code" />
				</span> </a>
			</li>
			<li><a href="#tagg"><span><fmt:message
							key="registTag" /> </span> </a>
			</li>

		</ul>

		<div id="comment" style="margin-left: auto; margin-right: auto;">
			<div>
				<div>
					<input type="text" id="commentInput"
						style="width: 150; height: 20;" name="comment" class="required"><br>
				</div>
				<div>
					<button class="searchButton black" id="commentUp">
						<fmt:message key="button.comment" />
					</button>
				</div>

				<span class="success"><fmt:message key="commented" /> </span>
			</div>
			<div id="commentLoad"></div>



		</div>
		<div id="code">
			<pre class="prettyprint">${g.code}</pre>
		</div>

		<div id="tagg">
			<input type="text" name="tag" style="width: 200;" id="tagReg">
			<button class="searchButton black" id="tagButton">
				<fmt:message key="button.regist" />
			</button>
			<div id="tagUpload2"></div>
		</div>
	</div>
</body>
</html>

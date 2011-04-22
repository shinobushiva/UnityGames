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
	jQuery.extend(jQuery.validator.messages, {
		required : "<br><fmt:message key='not.comment'/>"
	});

	$(function() {
		$(".success").hide();
		$("#fo").validate();
		$("#commentUp-${g.key.id}").click(function() {
			alert("click");
			$(".error").hide();
			$(".success").show();

			$(".success").fadeOut("slow");
			var a = $("#commentR").serialize();
			var b = $("#gameKey").serialize();
			var data = {
				a : a,
				b : b
			};
			$.ajax({
				type : "post",
				url : "/commentUp",
				data : data,
				success : function() {
					$('#commentLoad').load("/ajax/commentLoad?id=${g.key.id}");
				}

			});
			$("#commentR").val("");
		});

	});
</script>

<script type="text/javascript">
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

	$(function() {
		$('#tabs').tabs();
		$('#contentTab').tabs();

		updateTags();
	});

	$(function() {
		$("#ReLoad").hide();
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

		$("#Load,#ReLoad").click(function() {
			$(function() {
				$("#ReLoad").show();
				$("#Loaded").load("/ajax/gameLoad?id=${g.key.id}");
			});
		});

	});
</script>


</head>
<body onload="prettyPrint()">

	<input type="hidden" id="gameKey" name="gameKey" value="${f:h(g.key)}">

	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>
	<div>
		<%-- Game Name --%>
		<a style="font-size: 30px;">${g.gameName}</a>
	</div>

	<div align="right">
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

	<div style="padding-top: 3em;">
		<%--  Tags --%>
		<b style="font-size: x-small; color: red;"><fmt:message
				key="registerTag" /> </b> <span id="tagUpload"></span>
	</div>
	<div>
		<%-- Game --%>
		<div>
			<div id="Loaded">
				<c:choose>
					<c:when test="${empty g.thumbNailURL}">
						<img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"
							width="600" height="450"
							style="position: relative; opacity: 0.3; filter: alpha(opacity =                                                                                                                                                                                                                                                                           30); z-index: 0;" />
					</c:when>
					<c:when test="${not empty g.thumbNailURL}">
						<img src="${g.thumbNailURL}" border="1" width="600" height="450"
							style="position: relative; opacity: 0.3; filter: alpha(opacity =                                                                                                                                                                                                                                                                           30); z-index: 0;" />
					</c:when>
				</c:choose>
				<button id="Load"
					style="background-color: transparent; border: 0; z-index: 1; position: relative; left: -400px; top: -180px">
					<img src="/images/Start.png">
				</button>
			</div>
			<button id="ReLoad"
				style="position: absolute; left: 260; top: ; z-index: 1;">リロード</button>
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

		<div id="comment">
			<div id="commentLoad">
				<c:forEach var="c" items="${c}">
					<div align="center">
						${c.comment}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<fmt:formatDate value="${c.date}" pattern="hh:mm MM/dd/yyyy" />
						<br>
					</div>
				</c:forEach>
			</div>

			<div style="margin-left: auto; margin-right: auto;">
				<div>
					<input type="text" id="commentR" style="width: 150; height: 20;"
						name="comment" class="required"><br>
				</div>
				<div>
					<button class="searchButton black" id="commentUp-${g.key.id}">
						<fmt:message key="button.comment" />
					</button>
				</div>

				<span class="success"><fmt:message key="commented" /> </span>
			</div>

		</div>
		<div id="code">
			<pre class="prettyprint">${g.code}</pre>
		</div>

		<div id="tagg">
			<input type="text" name="tag" style="width: 200;" id="tagReg">
			<button class="searchButton black" id="tagButton">
				<fmt:message key="button.regist" />
			</button>
			<div id="tagUpload2">
				<%--
				<c:forEach var="t" items="${g.tags}" varStatus="loop">
					<script type="text/javascript">
						$(function() {
							$("#tagDeleteButton-${loop.index}").click(
									function() {
										deleteButtonClicked("${loop.index}",
												"${g.key.id}")
									});
						});
					</script>
					<input type="hidden" id="tagDel-${loop.index}" name="tagDel"
						value="${t.name}">
					<div>
						<a id="t" style="font-size: 20px;">${t.name}</a>
						<button type="submit" class="button delete"
							id="tagDeleteButton-${loop.index}">
							<span id="tagDeleteButton-${loop.index}"
								style="color: white; font-size: 20; width: 70; height: 20; position: relative; right: 0; bottom: 1;">
								<fmt:message key="button.delete" /> </span>
						</button>
					</div>
				</c:forEach>
				 --%>
			</div>
		</div>
	</div>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<%@ include file="/share/js.jsp"%>
<script type="text/javascript" src="/js/jquery.createvideo.js"></script>
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

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-22824102-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script type="text/javascript">
function GetUnity() {
	if (typeof unityObject != "undefined") {
		return unityObject.getObjectById("unityPlayer");
	}
	return null;
}
</script>

<script type="text/javascript">
	var showComments = false;


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
		
		$("#comments-top").hide();
		$("#comments-left").hide();
		$("#comments-right").hide();
		$("#commentToggle").click(commentToggle);
		
		updateTags();
		loadComments();
		
		toCode(eval(${g.code}));

		$.getJSONP("http://api.twitter.com/1/users/show.json?id=${twitterId}&callback={callback}",function(e){
			$("#twitterImage").attr("src",""+e.profile_image_url);
			$("#twitterName").html(""+e.screen_name);
			$("#userPage").attr("href","/user/"+e.screen_name);
			
		});
		
		$("pre").css("margin","0");
		$("pre").css("margin-left","10px");
		$("pre").css("margin-top","10px");
		$("a[rel=video]").createVideo();
		$("#edit").click(function() {
			$.ajax({
				type : "post",
				url : "/ajax/codeEdit",
				data : "id=${g.key.id}&type=edit",
				success : function(e) {
				$("#codeEditArea").val(e.code);
				$(".codeEdit").toggle();
				$(".edit").toggle();
				}});
		});
		$("#save").click(function() {
	var text = $("#codeEditArea").serialize();
			$.ajax({
				type : "post",
				url : "/ajax/codeEdit",
				data : "id=${g.key.id}&type=save&"+text,
				success : function(e) {
					$(".codeEdit").toggle();
					$(".edit").toggle();
					toCode(eval(e.code));
					$("a[rel=video]").createVideo();
				}});
		});
	});

	function commentToggle(){
		showComments = !showComments;
		$("#comments-top").slideToggle();
		$("#comments-left").slideToggle();
		$("#comments-right").slideToggle();
		loadComments();
	}
	
	function loadComments() {

		$.ajax({
			type : "post",
			url : "/ajax/commentLoad",
			data : "id=${g.key.id}",
			success : function(e) {
				var dateFormat = new DateFormat("yyyy/MM/dd HH:mm:ss");
				var html = "";
				var cms = e.comments;
			
				
				$("div").remove(".comment_floating");
				for (i in cms) {
						// funct(com,$("#comments-bottom"),i*500, 1000, 200);
					var c = cms[i];
					var com = c.comment;
				
					html += "<div>";
					html += "" + c.comment + " " +dateFormat.format(new Date(c.date));
					html += "</div>";
					if(showComments){
					
					switch(i%3) {
					case 0:funct(com,$("#comments-top"),i*500, 1000, 200); break;
					case 1:funct(com,$("#comments-left"),i*500, 200, 450); break;
					default:funct(com,$("#comments-right"),i*500, 200, 450);
					}
					}
				
				}
				$('#commentLoad').html(html);
			}

		});
	}
	function funct(com, cc, delay, width, height){

		var offset = cc.offset();
		
		var xMin = offset.left;
		var xMax = xMin+width;
		var yMin = offset.top;
		var yMax = yMin+height;
		
		
		var x = Math.floor( Math.random() * (xMax - xMin) )+xMin;
		var y = Math.floor( Math.random() * (yMax - yMin) )+yMin;

		var red = Math.floor( Math.random() * 256);
        var green = Math.floor( Math.random() * 256);
        var blue = Math.floor( Math.random() * 256);
        var col = 'rgb('+ red +','+ green +','+ blue +')';
        //var fs = Math.floor( Math.random() * (32 - 16) ) + 16;
        if(xMax -x <100){
        	x=x-100;
        }
		var fc = $("<div class='comment_floating'>"+com+"</div>");
		fc.css("position","absolute");
		fc.css("left",""+x+"px");
		fc.css("top",""+y+"px");
		fc.css('word-wrap', 'break-word');
		fc.css("background","white")
		
		fc.css('color',col);
		fc.css('width',""+(xMax-x)+"px");
		
		var func = function(){
			var offset = cc.offset();
			
			var xMin = offset.left;
			var xMax = xMin+width;
			var yMin = offset.top;
			var yMax = yMin+height;
			
			var x = Math.floor( Math.random() * (xMax-100 - xMin) )+xMin;
			var y = Math.floor( Math.random() * (yMax - yMin) )+yMin;
			
			var red = Math.floor( Math.random() * 256);
	        var green = Math.floor( Math.random() * 256);
	        var blue = Math.floor( Math.random() * 256);
	        var col = 'rgb('+ red +','+ green +','+ blue +')';

	        if(xMax -x <100){
	        	x=x-100;
	        }
	        
			fc.css("left",""+x+"px");
			fc.css("top",""+y+"px");
			fc.css('color',col);
			fc.css('width',""+(xMax-x)+"px");
			
			//fc.css('font-size', ""+fs);
			if(showComments){
				fc.delay(1000).fadeIn(2000).delay(5000).fadeOut(2000,func);
			}
		}
		fc.hide().delay(delay).fadeIn(2000).delay(5000).fadeOut(2000,func);
		cc.append(fc);
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

		//		console.log(e.gameData);
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
				//	console.log(t.key);
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
	
	function toCode(obj){
		
		var array = new Array();
		var count = 0;
		
		var result = "";
		for(var oIdx in obj){
			var o = obj[oIdx];
			if(o[0] == 'text'){
				
			var match =	o[1].match(".*(youtu(?:\.be|be\.com)/(?:.*v(?:/|=)|(?:.*/)?)([a-zA-Z0-9-_]+)).*");
			if(match){
			
		o[1] = o[1].replace(""+match[0],"<a rel='video' href='http://www.youtube.com/watch?v="+match[2]+"'></a>");
			}
				result += "<pre style='margin:10px;'>"+o[1]+"</pre>";
			}else{
				result += "<div class='"+o[0]+"' style='display:none; margin : 5px'>"+
				"<pre class='prettyprint'>"+o[1]+"</pre>"+
				"</div>";
				if($.inArray(o[0], array))
					array[count++] = o[0];
			}
		}

		console.log(array);
		
		var str = '';
		if(array.length > 0){
		str +='<div style="">使用言語：<select name="hoge">';
		for(var oIdx2 in array){
			str+='<option value="'+array[oIdx2]+'">'+array[oIdx2]+'</option>';
		}
		str+='</select></div>';
		}
		
		$("#code").html(str+result).ready(function(){
			prettyPrint();
			$("#code").find("select").change(function(){
				$("select option").each(function () {
					$("."+$(this).text()).hide();
		      	});
				$("select option:selected").each(function () {
					$("."+$(this).text()).show();
		      	});
			});
		});
		
		$("."+array[0]).show();
		
		}
	
</script>

</head>
<body>

	<input type="hidden" id="gameKey" name="gameKey" value="${f:h(g.key)}">

	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>
	<div>
		<div style="margin-top: 20px;">
			<%--  Tags --%>
			<b style="font-size: x-small; color: red;"><fmt:message
					key="registerTag" /> </b> <span id="tagUpload"></span>
		</div>
		<div
			style="padding-top: 1em; width: 720px; line-height: 2.5em; float: left; word-break: break-all;">
			<%-- Game Name --%>
			<span style="font-size: 2.5em;">${g.gameName} </span>
		</div>
	</div>

	<%--	ゲーム投稿者情報	 --%>

	<div style="float: right; margin-top: 10px;">
		<div>投稿者 (編集はここから↓)</div>
		<%--	Twitterアカウント	--%>
		<c:if test="${empty g.pass}">
			<div>
				<a id="userPage" style="" target="Twitter">
					<div style="float: left;">
						<img id="twitterImage" />
					</div>
					<div id="twitterName"
						style="float: right; text-align: left; font-weight: 900; font-size: 20px; margin-top: 10px; word-break: break-all; color: #1F98C7;">
					</div> </a>

			</div>
		</c:if>
		<%--	変更パスワード入力	--%>
		<c:if test="${not empty g.pass}">
			<div style="width: 150px;">
				<div style="float: left; display: inline-block;">
					<img src="/images/face.png" />
				</div>

				<div
					style="font-weight: 900; font-size: 20px; display: inline-block;">
					&nbsp;Guest</div>
				<div style="display: inline-block; float: right;">
					<script type="text/javascript">
						$(function() {

							$("#out-${g.key.id}").hide();
							$("#change-${g.key.id}").click(
									function() {
										$("#change-${g.key.id}").css(
												"position", "relative");
										$("#change-${g.key.id}").css("top",
												"-5px");
										$("#out-${g.key.id}").show();
									//	$("#br-${g.key.id}").hide();
									});
						});
					</script>
					<form action="/unitygames/change" method="post">
						<a id="change-${g.key.id}" style="font-size: x-small;"><fmt:message
								key="change.delete" /> </a><a id="out-${g.key.id}"
							style="position: relative; top: -5px;"><input type="password"
							name="Pass" style="width: 40px;"><input type="hidden"
							name="key" value="${f:h(g.key)}">
							<button type="submit">
								<img id="sub" src="/images/red.gif" />
							</button> </a>
					</form>
				</div>
			</div>

		</c:if>
	</div>
	<div style="float: right; padding-top: 15px;">
		<%-- Social Buttons --%>
		<ul>
			<li style="height: 25px !important; display: inline;"><fb:like
					layout="button_count"></fb:like> <a id="fb-root"></a> <script>
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
			</li>
			<li style="display: inline;"><a href="http://mixi.jp/share.pl"
				class="mixi-check-button"
				data-key="42bc93a615261cdd8e17e115918eb36ebf60a729"
				data-button="button-1"></a> <script type="text/javascript"
					src="http://static.mixi.jp/js/share.js"></script>
			</li>
			<li style="display: inline;"><iframe
					src="http://share.gree.jp/share?url=http%3A%2F%2Funity-games.appspot.com%2Funitygames%2Fgame%2Fug${g.key.id}&type=0&height=20"
					scrolling="no" frameborder="0" marginwidth="0" marginheight="0"
					style="border: none; overflow: hidden; width: 75px; height: 20px;"
					allowTransparency="true"></iframe>
			</li>
			<li style="display: inline;"><a href="http://twitter.com/share"
				class="twitter-share-button" data-count="horizontal"
				data-via="UGames #UnityGames"
				data-lang="<%=request.getLocale().getLanguage()%>"></a> <script
					type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
			</li>
		</ul>

	</div>

	<div id="tabs" style="clear: both;">
		<%-- Top Tabs --%>
		<ul>
			<li><a href="#tab1"><span><fmt:message
							key="explanation" /> </span> </a></li>
			<li><a href="#tab2"><span><fmt:message
							key="operation" /> </span> </a></li>
			<li><a href="#tagg"><span><fmt:message
							key="registTag" /> </span> </a></li>
			<%--
			<span>
				<button id="commentToggle"
					style="line-height: 2em; display: inline-block;">コメントクラウド表示/非表示</button>
			</span>
			--%>
			<span style="text-align: right; display: inline-block; float: right;">
				<fmt:message key="entryDay" />：<fmt:formatDate value="${g.date}"
					pattern="MM/dd" /> <fmt:message key="LastEntryDay" />：<fmt:formatDate
					value="${g.lastDate}" pattern="MM/dd" /><br>アクセス数：${g.access}
				コメント数:${g.comment}</span>

		</ul>
		<div id="tab1">
			<span>${g.contents}</span>
		</div>
		<div id="tab2">
			<span>${f:h(g.operations)}</span>
		</div>
		<div id="tagg">
			<input type="text" name="tag" style="width: 200;" id="tagReg">
			<button class="searchButton black" id="tagButton">
				<fmt:message key="button.regist" />
			</button>
			<div id="tagUpload2"></div>
		</div>
	</div>
	<%-- Game --%>
	<div style="margin-top: 1em; margin-bottom: 1em;">
		<%--
		<div id="comments-top" style="width: 1000px; height: 200px;">&nbsp;</div>
		 --%>
		<%-- &nbsp;は無いとつぶれる --%>
		<%--
		<div style="float: left; width: 200px;">
			&nbsp;
			<div id="comments-left" style="width: 200px;">&nbsp;</div>
		</div>
	 --%>
		<div id="game-center"
			style="margin-left: auto; margin-right: auto; width: 600px;">
			<div id="loaded">
				<c:choose>
					<c:when test="${empty g.thumbNailURL}">
						<img src="/unitygames/thumbNail?id=${f:h(g.key.id)}" width="600"
							height="450" style="opacity: 0.3; z-index: 0;" />
					</c:when>
					<c:when test="${not empty g.thumbNailURL}">
						<img src="${g.thumbNailURL}" border="1" width="600" height="450"
							style="opacity: 0.3; z-index: 0;" />
					</c:when>
				</c:choose>
				<button id="load"
					style="background-color: transparent; border: 0; z-index: 1; position: relative; left: 200px; top: -180px">
					<img src="/images/Start.png">
				</button>

			</div>
		</div>
		<%--	<div id="comments-right" style="float: left; width: 200px;">&nbsp;</div> --%>
		<%--	<div id="comments-bottom"
			style="clear: both; width: 1000px; height: 200px;">&nbsp;</div>
			--%>
	</div>

	<div style="clear: both;">
		<%-- Tabs --%>


		<div class="ui-widget-content ui-tabs-panel">
			<div>
				<span style="margin-top: 15px; font-size: 15;"> <fmt:message
						key="code" /> </span> <span style="float: right;" id="edit" class="edit">編集する</span><span
					style="float: right; display: none;" id="save" class="edit">保存する</span>
			</div>
			<div id="code" style="display: inline-block;" class="codeEdit"></div>
			<div>
				<textarea id="codeEditArea"
					style="width: 900; height: 500; display: none;" class="codeEdit"
					name="codeEditArea"></textarea>
			</div>
		</div>

		<div class="ui-widget-content ui-tabs-panel" style="margin-top: 10px;">
			<a href="#comment"><span><fmt:message key="comment" /> </span> </a>
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
		</div>


	</div>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns:og="http://ogp.me/ns#"  xmlns:mixi="http://mixi-platform.com/ns#" xmlns:fb="http://www.facebook.com/2008/fbml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${g.gameName}</title>
		<meta name="mixi-check-robots" content="noimage" />
		<script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
		<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
		<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
		<link href="/css/prettify.css" rel="StyleSheet" type="text/css"  />
		<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script src="/js/jquery.validate.min.js"></script>
		<script src="/js/prettify.js"></script>
		<style type="text/css">
		<!--
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
		td.comment{
		margin-right: 100px;
		position:relative;
		width: 300px;
		}
		-->
		</style>
	</head>
	<body onload="prettyPrint()">
	
	<jsp:include page="/share/header.jsp" >
	<jsp:param value='${g.gameName}' name='name' />
</jsp:include>
	
	<br><br><br><br><br><br><br><br><br>
	
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
		
$(function() {   
	  $('#tabs').tabs();   
	  $('#contentTab').tabs();   
	});  
	
$(function(){
	$("#ttt").hide();
	$("#tt").click(function(){
		$("#ttt").show();
	});
	$("#tagButton").click(function(){
		$.ajax({
			type : "post",
			url : "/tagRegist",
			dataType: "application/JSON",
			data :{
			a:	$("#tagReg").serialize() , 
			b:	$("#GameKey").serialize()
			},
		success: $(function(){
			$('#tagReg').val("");
			$('#tagUpload2').load("/ajax/TagUpload2?id=${g.key.id}")
		})
		});
		$(function(){
			$('#tagUpload').load("/ajax/TagUpload?id=${g.key.id}")
		});
	});

});
</script>
		 
		   
		<table border="0" align="center"><tr><td>
	
	<div align="right">
    <fb:like layout="button_count" width="0"></fb:like>
    <a id="fb-root"></a>
    <script>
      window.fbAsyncInit = function() {
        FB.init({appId: '214224191925233', status: true, cookie: true,
                 xfbml: true});
      };
      (function() {
        var e = document.createElement('script');
        e.type = 'text/javascript';
        e.src = document.location.protocol +
          '//connect.facebook.net/ja_JP/all.js';
        e.async = true;
        document.getElementById('fb-root').appendChild(e);
      }());
    </script>
<a href="http://mixi.jp/share.pl" class="mixi-check-button" data-key="42bc93a615261cdd8e17e115918eb36ebf60a729" data-button="button-1"></a><script type="text/javascript" src="http://static.mixi.jp/js/share.js"></script>
<a href="http://twitter.com/share" class="twitter-share-button" data-count="horizontal" data-via="UGames" data-lang="ja"></a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
	</div>
		
		<table class="purchase-options" align="center">
		<tr class="top"><td>
		<div id="tabs"> 
		<ul>  
      <li><a href="#tab1"><span><fmt:message key="explanation" /></span></a></li>  
      <li><a href="#tab2"><span><fmt:message key="operation" /></span></a></li>  
    <div align="right"><span><fmt:message key="entryDay" />：<fmt:formatDate  value="${g.date}" pattern="MM/dd" /></span><br><span><fmt:message key="LastEntryDay" />：<fmt:formatDate  value="${g.lastDate}" pattern="MM/dd" /></span></div> 	
      </ul>   
		 
		<div id="tab1">  
		<pre>${g.contents}</pre>
		</div >
		<div id="tab2">
		<pre>${g.operations}</pre>
		</div>
				
		
		</div></td></tr>
		  
		<tr class="bottom"><td></td></tr>
		</table></td></tr><tr><td>
		<b style="font-size: 20px;color: red;"><fmt:message key="registerTag" /></b>
		<c:forEach var="ft" items="${fixTag}">
		  <b><a href="/search?tag=${ft}">${ft}</a></b>
		</c:forEach>
		<span id="tagUpload">
		<c:forEach var="t" items="${tag}">
		 <a href="/search?tag=${t}">${t}</a>
		</c:forEach>
		</span>
		</td></tr><tr><td>
		<table border="0" align="center" class="purchase-options" >
		<div style="word-break:break-all">
		<tr><td width="200" align="left">
	
		</td><td>
		<div class="content">
			<div id="unityPlayer">
				<div class="missing">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">
						<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />
					</a>
				</div>
			</div>
			
		</div>
		</td><td class="comment"　nowrap>
			</td></tr>
		</div>
		</table></td></tr></table>
			<table border="0" class="purchase-options" align="center" style="word-break:break-all">
		<tr><td>

		<div id="contentTab"> 
		<ul>  
      <li><a href="#comment"><span><fmt:message key="comment" /></span></a></li>  
      <li><a href="#code"><span><fmt:message key="code" /></span></a></li>
	  <li><a href="#tagg"><span><fmt:message key="registTag" /></span></a></li>
   
      </ul>   
		 
		<div id="comment">  
		<script type="text/javascript">
		jQuery.extend(jQuery.validator.messages, {
	        required: "<br><fmt:message key='not.comment'/>"
		});
		
	$(function(){
		$(".success").hide();
		$("#fo").validate();  
		$("#commentUp").click(function(){
			$(".error").hide();
			$(".success").show();
			$.ajax({
				type : "post",
				url : "/commentUp",
				dataType: "application/JSON",
				data :{
				a:	$("#commentR").serialize() , 
				b:	$("#GameKey").serialize()
				},
				success: $(function(){
						$('#commentLoad').load("/ajax/commentLoad?id=${g.key.id}")
						$("#commentR").val("");
						$(".success").fadeOut("slow");
						
						
				})
				
		})
		});
		
			
	});
	</script>
	<div id="commentLoad">
		<c:forEach var="c" items="${c}" >
		<div align="center">
		${c.comment}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${c.date}" pattern="hh:mm MM/dd/yyyy"/><br>
		</div>
		</c:forEach>	
	</div>
		<form id="fo">
		<p align="center">
		<textarea id="commentR" style="width:150 ;height:20 ;" name="comment" class="required"></textarea><br><input type="submit" id="commentUp" class="submit">
		<span class="success"><fmt:message key="commented" /></span>
		</p>
		</form>
		</div >
		<div id="code">
	
		<pre class="prettyprint">${g.code}</pre>
		
		</div>
<div id="tagg">
		
		<input type="text" name="tag" style="width: 50%;" id="tagReg">
		<input type="hidden"id="GameKey" name="GameKey" value="${f:h(g.key)}"><br>
		<input type="submit" style="width: 50%;" id="tagButton"><br>
		<br>
		<div id="tagUpload2">
		<c:forEach var="t" items="${tag}" varStatus="loop">
		<script type="text/javascript">
	$(function(){
		
		$("#tagDeleteButton-${loop.index}").click(function(){
		$.ajax({
			type : "post",
			url : "/tagDelete",
			dataType: "application/JSON",
			data :{
			a:	$("#tagDel-${loop.index}").serialize() , 
			b:	$("#GameKey").serialize()
			},
			success: $(function(){
				
				$('#tagUpload2').load("/ajax/TagUpload2?id=${g.key.id}")
			})
			});
		$(function(){
			$('#tagUpload').load("/ajax/TagUpload?id=${g.key.id}")
		});
	});
});
</script>
		 <a href="">${t}</a><br>
		<input type="hidden"id="tagDel-${loop.index}" name="tagDel" value="${t}">
		<input type="submit" style="width: 30%;" id="tagDeleteButton-${loop.index}" value="削除">
		<br>
		</c:forEach>
		</div>
		
		
		</div>
		
		</div>
		
		</td></tr>
		
		</table>
		
	</body>
</html>

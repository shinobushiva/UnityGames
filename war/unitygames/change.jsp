<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<link href="/css/buttons.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
<script src="/js/jquery.updnWatermark.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/jquery.validate.messages_jp.js"></script>
<script src="/js/cmxform.js"></script>
<title><fmt:message key="change.home" /></title>

</head>
<body>
<script type="text/javascript">

jQuery(document).ready(function($) {  
    $.updnWatermark.attachAll();  
});  
$(function(){
	$("#commentForm").validate(); 
	$( "#ThumbNailChange1" ).hide();
	$( "#ThumbNailChange2" ).hide();
	$( "#GameChange1" ).hide();
	$( "#GameChange2" ).hide();
	$( "#tu" ).hide();
	$( "#G2" ).hide();
	$( "#G3" ).hide();
$( "div#tu" ).hide();
	$("#data").click(function(){
		$( "#G1" ).show();
		$( "#G2" ).hide();
		$( "#GameURL" ).val("");
		$( "#HpURL" ).val("");
		$( "#G3" ).hide();
	});
	$("#url").click(function(){
		$( "#G1" ).hide();
		$( "#G2" ).show();
		$( "#G3" ).hide();
		$( "#GameData" ).val("");
		$( "#HpURL" ).val("");
	});
	$("#hp").click(function(){
		$( "#G1" ).hide();
		$( "#G2" ).hide();
		$( "#G3" ).show();
		$( "#GameData" ).val("");
		$( "#GameURL" ).val("");
	});
	$("#tdata").click(function(){
		$( "#tf" ).show();
		$( "#tu" ).hide();
		$( "#ThumbNailURL" ).val("");
	});
	$("#turl").click(function(){
		$( "#tf" ).hide();
		$( "#tu" ).show();
		$( "#ThumbNail" ).val("");
	});
	$("#GameChange").click(function() {
		var checked = $('#GameChange').attr('checked');
      if(checked){
		$( "#GameChange1" ).show();
		$( "#GameChange2" ).show();
      }
      if(!checked){
    	$( "#GameChange1" ).hide();
  		$( "#GameChange2" ).hide();
  		$( "#GameData" ).val("");
		$( "#GameURL" ).val("");
		$( "#HpURL" ).val("");
      }
      });
	$("#ThumbNailChange").click(function() {
		var checked = $('#ThumbNailChange').attr('checked');
      if(checked){
		$( "#ThumbNailChange1" ).show();
		$( "#ThumbNailChange2" ).show();
      }
      if(!checked){
    	$( "#ThumbNailChange1" ).hide();
  		$( "#ThumbNailChange2" ).hide();
  		$( "#ThumbNail" ).val("");
		$( "#ThumbNailURL" ).val("");
	  }
      });
});
function tagCheck(){
var flag = 0;var str = $("#tag").val();var tag = str.split(",");
if(tag.length >= 4){flag = 1;}
if(flag){window.alert('<fmt:message key="fixTag.notice" />'); // 入力漏れがあれば警告ダイアログを表示
return false; // 送信を中止
}
else{return true; // 送信を実行
}}
</script>

<jsp:include page="/share/header.jsp" />
<br><br><br><br><br><br><br><br><br>
<form action="${f:url('changeUp')}" method="post" class="cmxform"  id="commentForm"  enctype="multipart/form-data" onSubmit="return tagCheck()" name="form1"">

<div style="word-break:break-all">
<table class="purchase-options" border="0" align="center" width="859">
<tr class="top"><td colspan="2">&nbsp;</td></tr>
<tr>
<td><h1><fmt:message key="change.delete" /></h1></td>

<td rowspan="6">
<h2><fmt:message key="explanation" /></h2>
<textarea  style="width: 440;height: 90;" name="Contents" title="<fmt:message key="contents.title" />">${g.contents }</textarea><br>
<h2><fmt:message key="operation" /></h2>
<textarea  style="width: 440;height: 110;" name="Operations" title="<fmt:message key="operation.title" />">${g.operations}</textarea>
<h2><fmt:message key="code" /></h2>
<textarea  style="width: 440;height: 110;" name="Code" title="<fmt:message key="code.title" />">${g.code}</textarea>
</td>
</tr><tr><td><h2><label for="GameName"><fmt:message key="gameName" /></label><input type="text" name="GameName" class="required" id="GameName" value="${g.gameName }" /></h2></td>
</tr>
<tr>
<td><h2><label for="ThumbNail"><fmt:message key="thumbNail" /><c:if test="${g.thumbNailType =='data' }">
<img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"width="50" height="50" align="right"></h2></label><br>
<fmt:message key="now" />：
<label><font color="red"><fmt:message key="thumbNail.pictureDataUp" /></font></label><br>


</c:if>

<c:if test="${g.thumbNailType =='url' }"><img src="${g.thumbNailURL}"width="50" height="50" align="right"></h2><label><fmt:message key="now" />：<font color="red"><fmt:message key="thumbNail.urlUp" /></font></label><br>
</h2></label><br>


${g.thumbNailURL}<br></c:if>
<input type="checkbox" name="ThumbNailChange" id="ThumbNailChange" value="ThumbNailChange"><fmt:message key="change" /><br>
<div id="ThumbNailChange1">
<label><input type="radio" name="ThumbNailType" value="data" id="tdata" checked><fmt:message key="thumbNail.pictureDataUp" /></label><br>
<label><input type="radio" name="ThumbNailType" value="url" id="turl"><fmt:message key="thumbNail.urlUp" /><br></label></td>
</div>
</tr><tr><td>
<div id="ThumbNailChange2">
<div id="tf"><label for="ThumbNail"><fmt:message key="thumbNail.data" /></label><input type="file" name="ThumbNail" id="ThumbNail"></div>
<div id="tu"><label for="ThumbNailURL"><fmt:message key="thumbNail.url" /></label><input type="text" name="ThumbNailURL" id="ThumbNailURL" style="width:260px" title="<fmt:message key="thumbNail.url.title" />"><br></div>
</div>
</td></tr>

<tr><td><label for="d"><h2><fmt:message key="game.select" /></h2></label><br>
<fmt:message key="now" />：
<c:if test="${g.gameType =='data' }"><label><font color="red"><fmt:message key="game.select.data" /></font><br></label></c:if>
<c:if test="${g.gameType =='url' }"><label><font color="red"><fmt:message key="game.select.url" /></font><br></label>${g.gameURL}<br></c:if>
<c:if test="${g.gameType =='hpurl' }"><label><font color="red"><fmt:message key="game.select.url.outside" /></font><br></label></td>${g.hpURL}<br></c:if>
<input type="checkbox" name="GameChange" id="GameChange" value="GameChange"><fmt:message key="change" /><br>
<div id="GameChange1">
<label><input type="radio" name="GameType" value="data" id="data" checked><fmt:message key="game.select.data" /></label><br>
<label><input type="radio" name="GameType" value="url" id="url"><fmt:message key="game.select.url" /><br></label>
<label><input type="radio" name="GameType" value="hpurl" id="hp"><fmt:message key="game.select.url.outside" /><br></label></td>
</div>
</tr><tr><td>
<div id="GameChange2">
<div id="G1"><label for="GameData"><fmt:message key="game.data" /></label>
<input type="file" name="GameFile" id="GameData"><br></div>
<div id="G2"><label for="GameURL"><fmt:message key="game.url" /></label>
<input type="text" name="GameURL" id="GameURL" style="width:260px" title="<fmt:message key="game.url.title" />"/><br></div>
<div id="G3"><label for="HpURL"><fmt:message key="game.url.outside" /></label>
<input type="text" name="HpURL" id="HpURL" style="width:260px" title="<fmt:message key="game.url.outside.title" />"/><br></div>
</div>
<div style="position: relative;top: 20;">
<b><fmt:message key="fixTag.explanation" /></b><br>

<h2><fmt:message key="fixTag" /><input type="text" name="fixTag" value="${tag.fixTag}"id="tag"></h2>
</div>
</td></tr><tr><td>
<div align="left"><h2><fmt:message key="change.delete.key" /><input type="password" name="pass"  class="required"  value="${g.pass}" style="width: 117;"></h2></div></td>
<td> <div id="wrapper" align="center"　>	
		<button class="button"  style="position: absolute;bottom: -50px;left: 700px;width: 250px; height: 80px;font-size: xx-large;"><fmt:message key="button.change" />
</button>
		<input type="hidden" name="key" value="${f:h(g.key)}"></div>
</td></tr >
<tr class="bottom"><td colspan="2">&nbsp;</td></tr>
</table>
</div>
</form>
<form action="delete" method="post">
<div id="wrapper" align="center"　>	
		<button class="button" name="delete" style="position: relative;top:-30;width:859px;font-size: xx-large;"><fmt:message key="button.delete" />
</button>
		</div>

<input type="hidden" name="key" value="${f:h(g.key)}"></form>


<fmt:message key="url.explanation" />



</body>
</html>

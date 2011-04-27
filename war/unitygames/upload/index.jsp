<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="Unity3Dゲーム投稿サイトです。ゲームを触って学ぶそんなサイト。チュートリアルも充実してます。日本のUnityゲーム投稿サイトならここ！">
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="upload.title" />
</title>

</head>
<body>

	<script type="text/javascript">
jQuery(document).ready(function($) {  
	   
	$.updnWatermark.attachAll();  
});  


$(function(){
    $("#commentForm").validate();  

$( "#tu" ).hide();
$( "#G2" ).hide();
$( "#G3" ).hide();
$( "div#tu" ).hide();
	$("#data").click(function(){
		$( "#G1" ).fadeIn("fast");
		$( "#G2" ).hide();
		$( "#GameURL" ).val("");
		$( "#HpURL" ).val("");
		$( "#G3" ).hide();
	});
	$("#url").click(function(){
		$( "#G1" ).hide();
		$( "#G2" ).fadeIn("fast");
		$( "#G3" ).hide();
		$( "#GameData" ).val("");
		$( "#HpURL" ).val("");
	});
	$("#hp").click(function(){
		$( "#G1" ).hide();
		$( "#G2" ).hide();
		$( "#G3" ).fadeIn("fast");
		$( "#GameData" ).val("");
		$( "#GameURL" ).val("");
	});
	$("#tdata").click(function(){
		$( "#tf" ).fadeIn("fast");
		$( "#tu" ).hide();
		$( "#ThumbNailURL" ).val("");
	});
	$("#turl").click(function(){
		$( "#tf" ).hide();
		$( "#tu" ).fadeIn("fast");
		$( "#ThumbNail" ).val("");
	});
});
function tagCheck(){
	var flag = 0;var str = $("#tag").val();var tag = str.split(",");
	if(tag.length >= 4){flag = 1;}
	if(flag){window.alert('<fmt:message key="fixTag.notice" />'); // 入力漏れがあれば警告ダイアログを表示
	return false; // 送信を中止
	}
	else{
		
		return true; // 送信を実行
	}}
</script>

	<%@ include file="/share/header.jsp"%>
	<div style="padding-top: 30px;">
		<form action="upload" method="post" class="cmxform" id="commentForm"
			enctype="multipart/form-data" onSubmit="return tagCheck()"
			name="form1">
			<table class="purchase-options" border="0" align="center">
				<tr class="top">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><h1>
							<fmt:message key="upload.title" />
						</h1>
					</td>

					<td rowspan="6">

						<h2>
							<fmt:message key="explanation" />
						</h2> <textarea style="width: 440; height: 90;" name="Contents"
							title="<fmt:message key="contents.title" />"></textarea><br>
						<h2>
							<fmt:message key="operation" />
						</h2> <textarea style="width: 440; height: 110;" name="Operations"
							title="<fmt:message key="operation.title" />"></textarea>
						<h2>
							<fmt:message key="code" />
						</h2> <textarea style="width: 440; height: 110;" name="Code"
							title="<fmt:message key="code.title" />"></textarea>
						<div id="wrapper" align="center">
							<button id="regist" class="button"
								style="width: 250px; height: 80px; font-size: xx-large;">
								<fmt:message key="button.regist" />
							</button>
						</div></td>
				</tr>
				<tr>
					<td><h2>
							<label for="GameName"><fmt:message key="gameName" /> </label><input
								type="text" name="GameName" id="GameName" class="required" />
						</h2>
					</td>
				</tr>
				<tr>
					<td><label for="ThumbNail"><h2>
								<fmt:message key="thumbNail" />
							</h2> </label><br> <fmt:message key="thumbNail.process" /><br> <label><input
							type="radio" name="ThumbNailType" value="data" id="tdata" checked>
							<fmt:message key="thumbNail.pictureDataUp" /> </label><br> <label><input
							type="radio" name="ThumbNailType" value="url" id="turl">
							<fmt:message key="thumbNail.urlUp" /><br> </label>
					</td>

				</tr>
				<tr>
					<td><div id="tf">
							<label for="ThumbNail"><fmt:message key="thumbNail.data" />
							</label><input type="file" name="ThumbNail" id="ThumbNail">
						</div>
						<div id="tu">
							<label for="ThumbNailURL"><fmt:message
									key="thumbNail.url" /> </label><input type="text" name="ThumbNailURL"
								id="ThumbNailURL" style="width: 260px"
								title="<fmt:message key="thumbNail.url.title" />"><br>
						</div></td>
				</tr>
				<tr>
					<td><label for="d"><h2>
								<fmt:message key="game.select" />
							</h2> </label><br> <fmt:message key="gamedata.process" /><br> <label><input
							type="radio" name="GameType" value="data" id="data" checked>
							<fmt:message key="game.select.data" /> </label><br> <label><input
							type="radio" name="GameType" value="url" id="url"> <fmt:message
								key="game.select.url" /><br> </label> <label><input
							type="radio" name="GameType" value="hpurl" id="hp"> <fmt:message
								key="game.select.url.outside" /><br> </label>
					</td>
				</tr>
				<tr>
					<td>
						<div id="G1">
							<label for="GameData"><fmt:message key="game.data" /> </label> <input
								type="file" name="GameFile" id="GameData"><br>
						</div>
						<div id="G2">
							<label for="GameURL"><fmt:message key="game.url" /> </label> <input
								type="text" name="GameURL" id="GameURL" style="width: 260px"
								title="<fmt:message key="game.url.title" />" /><br>
						</div>
						<div id="G3">
							<label for="HpURL"><fmt:message key="game.url.outside" />
							</label> <input type="text" name="HpURL" id="HpURL" style="width: 260px"
								title="<fmt:message key="game.url.outside.title" />" /><br>
						</div>
						<div>
							<h2>
								<fmt:message key="fixTag" />
								<input type="text" name="fixTag" id="tag">
							</h2>
							<span class="warning"><fmt:message key="fixTag.explanation" /> </span>
							
						</div>
						<div>
							<h2>
								<fmt:message key="change.delete.key" />
								<input type="password" name="pass" style="width: 117;"
									class="required">
							</h2>
						</div>
					</td>

				</tr>
				<tr class="bottom">
					<td colspan="2" style="padding: 15px;"><span class="warning"><fmt:message
							key="url.explanation" /></span></td>
				</tr>

			</table>
		</form>



	</div>
	<%@ include file="/share/footer.jsp"%>

</body>
</html>

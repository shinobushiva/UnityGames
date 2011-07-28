<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<link href="/css/registGame.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="upload.title" /></title>

</head>
<body>
	<script type="text/javascript">
jQuery(document).ready(function($) {  
	   
	$.updnWatermark.attachAll();  
});  


$(function(){
    $("#commentForm").validate();  
$( "#changeKey" ).hide();
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
	$("#ga2").click(function(){
		$( "#twitterKey" ).hide();
		$( "#changeKey" ).fadeIn("fast");
	});
	$("#ga1").click(function(){
		$( "#changeKey" ).hide();
		$( "#twitterKey" ).fadeIn("fast");
		$( "#adminPass" ).val("");
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
	<div class="paddingTopThiry">
		<form action="/action/upload" method="post" class="cmxform"
			id="commentForm" enctype="multipart/form-data"
			onSubmit="return tagCheck()" name="form1">
			<input type="hidden" name="ThumbNailChange" value="true" /> <input
				type="hidden" name="GameChange" value="true" />

			<table class="purchase-options" border="0">
				<tr class="top">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><h1>
							<fmt:message key="upload.title" />
						</h1></td>

					<td rowspan="6">

						<h2>
							<fmt:message key="explanation" />
						</h2> <textarea name="Contents"
							title="<fmt:message key="contents.title" />"
							class="contentsFrame"></textarea><br>
						<h2>
							<fmt:message key="operation" />
						</h2> <textarea name="Operations"
							title="<fmt:message key="operation.title" />"
							class="operationFrame"></textarea>
						<div>
							<div class="inlineBlock">
								<h2>
									<fmt:message key="code" />
								</h2>
							</div>
							<div class="inlineBlock">
								<fmt:message key="upload.howto.code" />
							</div>
							<div>
								<textarea name="Code" title="<fmt:message key="code.title" />"
									class="codeFrame"></textarea>
							</div>
						</div>
						<div>
							<div class="inlineBlock">
								<div>
									<div class="inlineBlock">
										<h2>
											<fmt:message key="screenSize" />
										</h2>
									</div>
									<div class="inlineBlock">
										<fmt:message key="screenSize.default" />
									</div>
								</div>
								<div class="inlineBlock">
									Width:<input type="text" name="gameScreenWidth" value="600"
										class="screenSize" />
								</div>
								<div class="inlineBlock">
									Height:<input type="text" name="gameScreenHeight" value="450"
										class="screenSize" />
								</div>
								<div>
									<label><input type="checkbox" name="editCode"
										value="true" class="inlineBlock" />
										<h2 class="inlineBlock">
											<fmt:message key="dont.edit" />
										</h2> </label>
								</div>
							</div>
							<div id="wrapper" class="floatRight inlineBlock">
								<button id="regist" class="button registButton">
									<fmt:message key="button.regist" />
								</button>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><h2>
							<label for="GameName"><fmt:message key="gameName" /> </label><input
								type="text" name="GameName" id="GameName" class="required" />
						</h2></td>
				</tr>
				<tr>
					<td><label for="ThumbNail"><h2>
								<fmt:message key="thumbNail" />
							</h2> </label><br> <fmt:message key="thumbNail.process" /><br> <label><input
							type="radio" name="ThumbNailType" value="data" id="tdata"
							checked="checked"> <fmt:message
								key="thumbNail.pictureDataUp" /> </label><br> <label><input
							type="radio" name="ThumbNailType" value="url" id="turl">
							<fmt:message key="thumbNail.urlUp" /><br> </label></td>

				</tr>
				<tr>
					<td><div id="tf">
							<label for="ThumbNail"><fmt:message key="thumbNail.data" />
							</label><input type="file" name="ThumbNail" id="ThumbNail">
						</div>
						<div id="tu">
							<label for="ThumbNailURL"><fmt:message
									key="thumbNail.url" /> </label><input type="text" name="ThumbNailURL"
								id="ThumbNailURL"
								title="<fmt:message key="thumbNail.url.title" />"><br>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="d"><h2>
								<fmt:message key="game.select" />
							</h2> </label><br> <fmt:message key="gamedata.process" /><br> <label><input
							type="radio" name="GameType" value="data" id="data"
							checked="checked"> <fmt:message key="game.select.data" />
					</label><br> <label><input type="radio" name="GameType"
							value="url" id="url"> <fmt:message key="game.select.url" /><br>
					</label> <label><input type="radio" name="GameType" value="hpurl"
							id="hp"> <fmt:message key="game.select.url.outside" /><br>
					</label></td>
				</tr>
				<tr>
					<td>
						<div id="G1">
							<label for="GameData"><fmt:message key="game.data" /> </label> <input
								type="file" name="GameFile" id="GameData"><br>
						</div>
						<div id="G2">
							<label for="GameURL"><fmt:message key="game.url" /> </label> <input
								type="text" name="GameURL" id="GameURL"
								title="<fmt:message key="game.url.title" />" /><br>
						</div>
						<div id="G3">
							<label for="HpURL"><fmt:message key="game.url.outside" />
							</label> <input type="text" name="HpURL" id="HpURL"
								title="<fmt:message key="game.url.outside.title" />" /><br>
						</div>
						<div>
							<h2>
								<fmt:message key="fixTag" />
								<input type="text" name="fixTag" id="tag">
							</h2>
							<span class="warning"><fmt:message
									key="fixTag.explanation" /> </span>

						</div> <%--	ゲームの管理方法	--%>
						<h2>
							<fmt:message key="management" />
						</h2> <%--Twitterアカウントでログインしている場合--%> <c:if
							test="${loginType == 'twitter' }">
							<div>
								<input type="radio" name="gameAdmin" id="ga1" checked="checked" />
								<fmt:message key="twitterAccount" />
								<input type="radio" name="gameAdmin" id="ga2" />
								<fmt:message key="password" />
							</div>

							<div id="twitterKey">
								<div class="twitterLogin">
									<div class="inline">
										<img id="loginTwitterImage" class="loginTwitterImage"
											width="40px">
									</div>
									<div class="inline">
										<span id="loginTwitterName" class="loginTwitterName"></span>
									</div>
								</div>
							</div>

							<div id="changeKey">
								<h2>
									<fmt:message key="change.delete.key" />
									<input type="password" name="pass" id="adminPass">
								</h2>
							</div>
						</c:if> <c:if test="${loginType == 'guest' }">
							<script type="text/javascript">
						$(function(){
							$("#changeKey").toggle();
						});
						</script>
							<div>
								<input type="radio" name="gameAdmin" id="ga2" checked="checked" />
								<fmt:message key="password" />
							</div>

							<div id="changeKey">
								<h2>
									<fmt:message key="change.delete.key" />
									<input type="password" name="pass" id="adminPass">
								</h2>
							</div>
						</c:if>
					</td>

				</tr>
				<tr class="bottom">
					<td colspan="2"><div style="margin: 10px;">
							<div style="margin: 10px;">SaveID : ${saveId}</div>
							<div style="margin: 10px;">LoadID : ${loadId}</div>
						</div>
					</td>
				</tr>

			</table>
		</form>
		<span class="warning"><fmt:message key="url.explanation" /> </span>


	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

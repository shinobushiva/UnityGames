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
	<div style="padding-top: 30px;">
		<form action="/action/upload" method="post" class="cmxform" id="commentForm"
			enctype="multipart/form-data" onSubmit="return tagCheck()"
			name="form1">
			<table class="purchase-options" border="0"
				style="margin-left: auto; margin-right: auto; margin-top: 25px;">
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
						</h2> <textarea style="width: 440px; height: 90px;" name="Contents"
							title="<fmt:message key="contents.title" />"></textarea><br>
						<h2>
							<fmt:message key="operation" />
						</h2> <textarea style="width: 440px; height: 110px;" name="Operations"
							title="<fmt:message key="operation.title" />"></textarea>
						<div>
							<div style="display: inline-block;">
								<h2>
									<fmt:message key="code" />
								</h2>
							</div>
							<div style="display: inline-block;">
								<fmt:message key="upload.howto.code" />
							</div>
							<div>
								<textarea style="width: 440px; height: 110px;" name="Code"
									title="<fmt:message key="code.title" />"></textarea>
							</div>
						</div>
						<div>
							<div>
								<div style="display: inline-block;">
									<h2>
										<fmt:message key="screenSize" />
									</h2>
								</div>
								<div style="display: inline-block;">
									<fmt:message key="screenSize.default" />
								</div>
							</div>
							<div style="display: inline-block;">
								Width:<input type="text" name="gameScreenWidth" value="600"
									style="width: 50px;" />
							</div>
							<div style="display: inline-block;">
								Height:<input type="text" name="gameScreenHeight" value="450"
									style="width: 50px;" />
							</div>
							<div>
								<label><input type="checkbox" name="editable"
									value="true" style="display: inline-block;" />
									<h2 style="display: inline-block;">
										<fmt:message key="dont.edit" />
									</h2> </label>
							</div>
						</div>
						<div id="wrapper" style="float: right;">
							<button id="regist" class="button"
								style="width: 250px; height: 80px; font-size: xx-large;">
								<fmt:message key="button.regist" />
							</button>
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
								id="ThumbNailURL" style="width: 260px"
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
								<%--	<div style="float: left;">
									<img
										src="${p}" />
								</div>
								<a style="color: #1F98C7;"><div
										style="text-align: left; font-weight: 900; font-size: 20px; margin-top: 10px; word-break: break-all;">
										&nbsp;${userName}</div> </a>
 --%>
								<div style="color: blue; margin-top: 10px"></div>
							</div>

							<div id="changeKey">
								<h2>
									<fmt:message key="change.delete.key" />
									<input type="password" name="pass" id="adminPass"
										style="width: 117px;">
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
									<input type="password" name="pass" id="adminPass"
										style="width: 117px;">
								</h2>
							</div>
						</c:if>
					</td>

				</tr>
				<tr class="bottom">
					<td colspan="2" style="padding-left:5px;padding-bottom: 15px;"><span class="warning"><fmt:message
								key="url.explanation" /> </span>
					</td>
				</tr>

			</table>
		</form>



	</div>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

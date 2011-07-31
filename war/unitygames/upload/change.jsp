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
<title><fmt:message key="change.home" /></title>
</head>
<body>
	<script type="text/javascript">

jQuery(document).ready(function($) {  
    $.updnWatermark.attachAll();  
});  
$(function(){
	$("#commentForm").validate(); 
	$( "#changeKey" ).hide();
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
	$("#ga2").click(function(){
		$( "#twitterKey" ).hide();
		$( "#changeKey" ).fadeIn("fast");
	});
	$("#ga1").click(function(){
		$( "#changeKey" ).hide();
		$( "#twitterKey" ).fadeIn("fast");
		$( "#adminPass" ).val("");
	});
	$("#GameChange").click(function() {
		$( "#GameChange1" ).toggle();
		$( "#GameChange2" ).toggle();
    	$( "#GameData" ).val("");
		$( "#GameURL" ).val("");
		$( "#HpURL" ).val("");
      });
	$("#ThumbNailChange").click(function() {
		
		$( "#ThumbNailChange1" ).toggle();
		$( "#ThumbNailChange2" ).toggle();
  		$( "#ThumbNail" ).val("");
		$( "#ThumbNailURL" ).val("");
      });
	$("#accessLevel").val(${g.accessLevel});
});
function tagCheck(){
var flag = 0;var str = $("#tag").val();var tag = str.split(",");
if(tag.length >= 4){flag = 1;}
if(flag){window.alert('<fmt:message key="fixTag.notice" />'); // 入力漏れがあれば警告ダイアログを表示
return false; // 送信を中止
}
else{return true; // 送信を実行
}};
</script>
	<%@ include file="/share/header.jsp"%>

	<form action="/action/upload" method="post" class="cmxform"
		id="commentForm" enctype="multipart/form-data"
		onSubmit="return tagCheck()" name="form1"">
		<input type="hidden" name="gameKey" value="${f:h(g.key)}">
		<div class="breakAll">
			<table class="purchase-options" border="0" width="859px">
				<tr class="top">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><h1>
							<fmt:message key="change.delete" />
						</h1>
					</td>

					<td rowspan="7">
						<h2>
							<fmt:message key="explanation" />
						</h2> <textarea name="Contents"
							title="<fmt:message key="contents.title" />"
							class="contentsFrame">${g.contents }</textarea><br>
						<h2>
							<fmt:message key="operation" />
						</h2> <textarea name="Operations"
							title="<fmt:message key="operation.title" />"
							class="operationFrame">${g.operations}</textarea>
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
								<textarea class="codeFrame" name="Code"
									title="<fmt:message key="code.title" />">${g.code}</textarea>
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
										Width:<input type="text" name="gameScreenWidth"
											value="${width}" class="screenSize" />
									</div>
									<div class="inlineBlock">
										Height:<input type="text" name="gameScreenHeight"
											value="${height}" class="screenSize" />
									</div>
									<div>
										<label> <c:if test="${g.editable}">
												<input type="checkbox" name="editCode" value="true"
													class="inlineBlock" checked="checked" />
											</c:if> <c:if test="${!g.editable}">
												<input type="checkbox" name="editCode" value="true"
													class="inlineBlock" />
											</c:if>
											<h2 class="inlineBlock">
												<fmt:message key="dont.edit" />
											</h2> </label>
									</div>
									<div>
										<label> <label><h2 class="inlineBlock">公開設定</h2>
												<select name="accessLevel" class="inlineBlock" id="accessLevel">
													<option value="0">全体公開</option>
													<option value="1">一部公開（新着やランキングに載せない）</option>
													<option value="2">自分のみアクセス</option>
											</select> </label>
										</label>
									</div>

								</div>
								<div id="wrapper" class="changeButonPosition inlineBlock">
									<button class="button" class="changeButton">
										<fmt:message key="button.change" />
									</button>
								</div>
							</div>
					</td>
				</tr>
				<tr>
					<td><h2>
							<label for="GameName"><fmt:message key="gameName" /> </label><input
								type="text" name="GameName" class="required" id="GameName"
								value="${g.gameName }" />
						</h2>
					</td>
				</tr>
				<tr>
					<td><h2>
							<label for="ThumbNail"><fmt:message key="thumbNail" /> <c:if
									test="${g.thumbNailType =='data' }">
									<img src="/unitygames/thumbNail?id=${f:h(g.key.id)}"
										width="50px" height="50px" align="right">
						</h2> </label><br> <fmt:message key="now" />： <label><font
							color="red"><fmt:message key="thumbNail.pictureDataUp" />
						</font> </label><br> </c:if> <c:if test="${g.thumbNailType =='url' }">
							<img src="${g.thumbNailURL}" width="50px" height="50px"
								align="right">
							</h2>
							<label><fmt:message key="now" />：<font color="red"><fmt:message
										key="thumbNail.urlUp" /> </font> </label>
							<br>
							</h2>
							</label>
							<br>


${g.thumbNailURL}<br>
						</c:if> <input type="checkbox" name="ThumbNailChange"
						id="ThumbNailChange" value="true"> <fmt:message
							key="change" /><br>
						<div id="ThumbNailChange1">
							<label><input type="radio" name="ThumbNailType"
								value="data" id="tdata" checked="checked"> <fmt:message
									key="thumbNail.pictureDataUp" /> </label><br> <label><input
								type="radio" name="ThumbNailType" value="url" id="turl">
								<fmt:message key="thumbNail.urlUp" /><br> </label>
					</td>
					</div>
				</tr>
				<tr>
					<td>
						<div id="ThumbNailChange2">
							<div id="tf">
								<label for="ThumbNail"><fmt:message key="thumbNail.data" />
								</label><input type="file" name="ThumbNail" id="ThumbNail">
							</div>
							<div id="tu">
								<label for="ThumbNailURL"><fmt:message
										key="thumbNail.url" /> </label><input type="text" name="ThumbNailURL"
									id="ThumbNailURL"
									title="<fmt:message key="thumbNail.url.title" />"><br>
							</div>
						</div></td>
				</tr>

				<tr>
					<td><label for="d"><h2>
								<fmt:message key="game.select" />
							</h2> </label><br> <fmt:message key="now" />： <c:if
							test="${g.gameType =='data' }">
							<label><font color="red"><fmt:message
										key="game.select.data" /> </font><br> </label>
						</c:if> <c:if test="${g.gameType =='url' }">
							<label><font color="red"><fmt:message
										key="game.select.url" /> </font><br> </label>${g.gameURL}<br>
						</c:if> <c:if test="${g.gameType =='hpurl' }">
							<label><font color="red"><fmt:message
										key="game.select.url.outside" /> </font><br> </label>
					</td>${g.hpURL}
					<br>
					</c:if>
					<input type="checkbox" name="GameChange" id="GameChange"
						value="true">
					<fmt:message key="change" />
					<br>
					<div id="GameChange1">
						<label><input type="radio" name="GameType" value="data"
							id="data" checked="checked"> <fmt:message
								key="game.select.data" /> </label><br> <label><input
							type="radio" name="GameType" value="url" id="url"> <fmt:message
								key="game.select.url" /><br> </label> <label><input
							type="radio" name="GameType" value="hpurl" id="hp"> <fmt:message
								key="game.select.url.outside" /><br> </label>
						</td>
					</div>
				</tr>
				<tr>
					<td>
						<div id="GameChange2">
							<div id="G1">
								<label for="GameData"><fmt:message key="game.data" /> </label>
								<input type="file" name="GameFile" id="GameData"><br>
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
						</div>
						<div>

							<h2>
								<fmt:message key="fixTag" />
								<input type="text" name="fixTag" value="${tag}" id="tag">
							</h2>
							<span class="warning"><fmt:message
									key="fixTag.explanation" /> </span>
						</div> <%--ゲームの管理方法--%>
						<h2>
							<fmt:message key="management" />
						</h2> <c:if test="${not empty g.pass}">
							<c:if test="${loginType == 'twitter' }">
								<%--パス入れてる＆Twitterアカウント--%>
								<script type="text/javascript">
						$(function(){
							$("#twitterKey").toggle();
							$("#changeKey").toggle();
						});
							
							</script>
								<div>
									<input type="radio" name="gameAdmin" id="ga1" />
									<fmt:message key="twitterAccount" />
									<input type="radio" name="gameAdmin" id="ga2" checked="checked" />
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
									<div class="heightForty"></div>
								</div>

								<div id="changeKey">
									<h2>
										<fmt:message key="change.delete.key" />
										<input type="password" name="pass" id="adminPass"
											value="${g.pass}">
									</h2>
									<div class="heightThirty"></div>
								</div>
							</c:if>
							<c:if test="${loginType == 'guest' }">
								<%--パス入れてる＆Guest--%>
								<script>
							$(function(){
								$( "#changeKey" ).toggle();
								$("#twitterKey").toggle();
							});
							</script>
								<div>

									<input type="radio" name="gameAdmin" id="ga2" checked="checked" />
									<fmt:message key="password" />
									<input type="radio" id="ga1" name="gameAdmin" />
									<fmt:message key="twitterAccount" />

								</div>
								<div id="changeKey">
									<h2>
										<fmt:message key="change.delete.key" />
										<input type="password" name="pass" id="adminPass"
											value="${g.pass}">
									</h2>
									<div class="heightThirty"></div>
								</div>
								<div id="twitterKey">
									<div class="twitterLogin">
										<fmt:message key="twitter.login" />
									</div>
									<div class="heightForty"></div>
								</div>
							</c:if>


						</c:if> <c:if test="${empty g.pass }">

							<%--パス入れてない--%>
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
								<div class="heightThirty"></div>
							</div>
						</c:if>
					</td>
				</tr>
				<tr class="bottom" class="heightThirty">
					<td colspan="2">
						<div style="margin: 10px;">
							<div style="margin: 10px;">SaveID : ${sl.saveId}</div>
							<div style="margin: 10px;">LoadID : ${sl.loadId}</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<span class="warning"><fmt:message key="url.explanation" /> </span>
	<form action="/action/delete" method="post">
		<input type="hidden" name="gameKey" value="${f:h(g.key)}">
		<div id="wrapper" align="center">
			<button class="button deleteButton" name="delete">
				<fmt:message key="button.delete" />
			</button>
		</div>
	</form>
	<%@ include file="/share/footer.jsp"%>

</body>
</html>

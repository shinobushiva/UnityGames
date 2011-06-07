<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="change.home" />
</title>

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
	<form action="${f:url('changeUp')}" method="post" class="cmxform"
		id="commentForm" enctype="multipart/form-data"
		onSubmit="return tagCheck()" name="form1"">
		<input type="hidden" name="gameKey" value="${f:h(g.key)}">
		<div style="word-break: break-all">
			<table class="purchase-options" border="0" width="859"
				style="margin-left: auto; margin-right: auto; margin-top: 25px;">
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
						</h2> <textarea style="width: 440; height: 90;" name="Contents"
							title="<fmt:message key="contents.title" />">${g.contents }</textarea><br>
						<h2>
							<fmt:message key="operation" />
						</h2> <textarea style="width: 440; height: 110;" name="Operations"
							title="<fmt:message key="operation.title" />">${g.operations}</textarea>
						<h2>
							<fmt:message key="code" />
						</h2> <textarea style="width: 440; height: 110;" name="Code"
							title="<fmt:message key="code.title" />">${g.code}</textarea>
						<div>
							<div>
								<div style="display: inline-block;">
									<h2>スクリーンサイズ</h2>
								</div>
								<div style="display: inline-block;">(デフォルト値は600*450です)</div>
							</div>
							<div style="display: inline-block;">
								Width:<input type="text" name="gameScreenWidth" value="${width}"
									style="width: 50px;" />
							</div>
							<div style="display: inline-block;">
								Height:<input type="text" name="gameScreenHeight"
									value="${height}" style="width: 50px;" />
							</div>
						</div>
						<div id="wrapper" style="float: right;">
							<button class="button"
								style="width: 200px; height: 64px; font-size: x-large;">
								<fmt:message key="button.change" />
							</button>
						</div></td>
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
									<img src="/unitygames/thumbNail?id=${f:h(g.key.id)}" width="50"
										height="50" align="right">
						</h2> </label><br> <fmt:message key="now" />： <label><font
							color="red"><fmt:message key="thumbNail.pictureDataUp" />
						</font> </label><br> </c:if> <c:if test="${g.thumbNailType =='url' }">
							<img src="${g.thumbNailURL}" width="50" height="50" align="right">
							</h2>
							<label><fmt:message key="now" />：<font color="red"><fmt:message
										key="thumbNail.urlUp" /> </font> </label>
							<br>
							</h2>
							</label>
							<br>


${g.thumbNailURL}<br>
						</c:if> <input type="checkbox" name="ThumbNailChange"
						id="ThumbNailChange" value="ThumbNailChange"> <fmt:message
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
									id="ThumbNailURL" style="width: 260px"
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
						value="GameChange">
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
									type="text" name="GameURL" id="GameURL" style="width: 260px"
									title="<fmt:message key="game.url.title" />" /><br>
							</div>
							<div id="G3">
								<label for="HpURL"><fmt:message key="game.url.outside" />
								</label> <input type="text" name="HpURL" id="HpURL" style="width: 260px"
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
						<h2>ゲームの管理方法</h2> <c:if test="${not empty g.pass}">
							<c:if test="${type == 'twitter' }">
								<%--パス入れてる＆Twitterアカウント--%>
								<script type="text/javascript">
						$(function(){
							$("#twitterKey").toggle();
							$("#changeKey").toggle();
						});
							
							</script>
								<div>
									<input type="radio" name="gameAdmin" id="ga1" />Twitterアカウント<input
										type="radio" name="gameAdmin" id="ga2" checked="checked" />パスワードを設定
								</div>

								<div id="twitterKey">
									<div style="color: blue; margin-top: 10px">登録ボタン押した後認証画面へ</div>
									<div style="height: 40px;"></div>
								</div>

								<div id="changeKey">
									<h2>
										<fmt:message key="change.delete.key" />
										<input type="password" name="pass" id="adminPass"
											style="width: 117;" value="${g.pass}">
									</h2>
									<div style="height: 30px;"></div>
								</div>
							</c:if>
							<c:if test="${type == 'guest' }">
								<%--パス入れてる＆Guest--%>
								<script>
							$(function(){
								$( "#changeKey" ).toggle();
								$("#twitterKey").toggle();
							});
							</script>
								<div>

									<input type="radio" name="gameAdmin" id="ga2" checked="checked" />パスワードを設定<input
										type="radio" id="ga1" name="gameAdmin" />Twitterアカウント

								</div>
								<div id="changeKey">
									<h2>
										<fmt:message key="change.delete.key" />
										<input type="password" name="pass" id="adminPass"
											style="width: 117;" value="${g.pass}">
									</h2>
									<div style="height: 30px;"></div>
								</div>
								<div id="twitterKey">
									<div style="color: blue; margin-top: 10px">登録ボタン押した後認証画面へ</div>
									<div style="height: 40px;"></div>
								</div>
							</c:if>


						</c:if> <c:if test="${empty g.pass }">

							<%--パス入れてない--%>
							<div>
								<input type="radio" name="gameAdmin" id="ga1" checked="checked" />Twitterアカウント<input
									type="radio" name="gameAdmin" id="ga2" />パスワードを設定
							</div>

							<div id="twitterKey">
								<div style="color: blue; margin-top: 10px">登録ボタン押した後認証画面へ</div>
								<div style="height: 40px;"></div>
							</div>

							<div id="changeKey">
								<h2>
									<fmt:message key="change.delete.key" />
									<input type="password" name="pass" id="adminPass"
										style="width: 117;">
								</h2>
								<div style="height: 30px;"></div>
							</div>
						</c:if>
					</td>
				</tr>
				<tr class="bottom" style="height: 30px;">
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
		</div>
	</form>
	<form action="delete" method="post">
		<input type="hidden" name="gameKey" value="${f:h(g.key)}">
		<div id="wrapper" align="center">
			<button class="button" name="delete"
				style="position: relative; top: -30; width: 859px; font-size: xx-large;">
				<fmt:message key="button.delete" />
			</button>
		</div>


	</form>


	<fmt:message key="url.explanation" />

	<%@ include file="/share/footer.jsp"%>

</body>
</html>

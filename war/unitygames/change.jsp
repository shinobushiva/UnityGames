<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
<script src="/js/jquery.updnWatermark.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/jquery.validate.messages_jp.js"></script>
<script src="/js/cmxform.js"></script>
<title>unitygames Change</title>

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
	var flag = 0;
	var str = $("#tag").val();
	var tag = str.split(",");
	if(tag.length >= 4){
		
		flag = 1;
	}


	if(flag){

		window.alert('固定タグは3つまでです'); // 入力漏れがあれば警告ダイアログを表示
		return false; // 送信を中止

	}
	else{

		return true; // 送信を実行

	}

	}
</script>


	<table border="0" cellpadding="0" cellspacing="0" class="titlebar">
		<tbody>
			<tr>
				<td class="titleleft">
					<img src="/images/top/left.png" /></td>
				<td>
				<img src="/images/top/logo.png" />
				<td class="titlemid">
					<table>
						<tbody>
							<tr>
								<td class="doctitle">
									Unityゲーム投稿</td>
								<td>
									<table align="right">
										<tbody>
											<tr>
<!-- #TemplateBeginEditable name="sections-nav" -->												<td class="Manual">
													<a class="scripting-anchor" href="../Manual/index.html" title="Go to Unity manual"><img border="0" class="manual" src="/images/spacer.gif" /><span class="manual-text">Manual</span></a><span class="docs-navigation">&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
												<td class="Manual">
													<a class="scripting-anchor" href="../Components/index.html" title="Go to Reference"><img border="0" class="reference" src="/images/spacer.gif" /><span class="components-text">Reference</span></a><span class="docs-navigation">&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
												<td class="Manual">
													<a class="scripting-anchor" href="../ScriptReference/index.html" title="Go to Scripting Reference"><img border="0" class="scripting" src="/images/spacer.gif" /><span class="scripting-text">Scripting &nbsp;&nbsp;</span></a></td>
<!-- #TemplateEndEditable -->											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<table class="docpath" style="border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; border-style: initial; border-color: initial; border-collapse: collapse; font-size: 13px; white-space: nowrap; color: rgb(164, 171, 174); margin-top: 2px; margin-left: 9px; width: 1166px; " width="100%">
										<tbody>
											<tr>
												<td style="font-family: Helvetica, Arial, sans-serif; font-size: 12px; vertical-align: top; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; ">
													<a href="/unitygames/" style="color: rgb(164, 171, 174); text-decoration: none; ">TOP</a>&nbsp;&gt;&nbsp;<a href="/unitygames/upload/" style="color: rgb(164, 171, 174); text-decoration: none; ">ゲーム登録</a></td>
											</tr>
										</tbody>
									</table>
									<table class="docpath" width="100%">
										<tbody>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				<td class="titleright" width="9">
					<img src="/images/top/right.png" /></td>
			</tr>
		</tbody>
	</table>

<form action="${f:url('changeUp')}" method="post" class="cmxform"  id="commentForm"  enctype="multipart/form-data" onSubmit="return tagCheck()" name="form1"">

<div style="word-break:break-all">
<table class="purchase-options" border="0" align="center" width="859">
<tr class="top"><td colspan="2">&nbsp;</td></tr>
<tr>
<td><h1>変更/削除</h1></td>

<td rowspan="6">
<h2>Game説明文</h2>
<textarea  style="width: 440;height: 90;" name="Contents" title="ゲームの内容を記入してください。">${g.contents }</textarea><br>
<h2>操作方法</h2>
<textarea  style="width: 440;height: 110;" name="Operations" title="出来るだけ詳しくアクションボタンの説明を記入してください。">${g.operations}</textarea>
<h2>ソースコード(未実装)</h2>
<textarea  style="width: 440;height: 110;" name="Code" title="ゲームに使用したJavaScriptなどを記入してください">${g.code}</textarea>
</td>
</tr><tr><td><h2><label for="GameName">Game名：</label><input type="text" name="GameName" class="required" id="GameName" value="${g.gameName }" /></h2></td>
</tr>
<tr>
<td><h2><label for="ThumbNail">サムネイル画像<c:if test="${g.thumbNailType =='data' }">
<img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.thumbNailKey)}"width="50" height="50" align="right"></h2></label><br>
現在の設定：
<label><font color="red">画像データをアップロードする(画像形式のみ)</font></label><br>


</c:if>

<c:if test="${g.thumbNailType =='url' }"><img src="${g.thumbNailURL}"width="50" height="50" align="right"></h2><label>現在の設定：<font color="red">画像URLを指定する(画像データのURL)</font></label><br>
</h2></label><br>


${g.thumbNailURL}<br></c:if>
<input type="checkbox" name="ThumbNailChange" id="ThumbNailChange" value="ThumbNailChange">変更する<br>
<div id="ThumbNailChange1">
<label><input type="radio" name="ThumbNailType" value="data" id="tdata" checked>画像データをアップロードする(画像形式のみ)</label><br>
<label><input type="radio" name="ThumbNailType" value="url" id="turl">画像URLを指定する(画像データのURL)<br></label></td>
</div>
</tr><tr><td>
<div id="ThumbNailChange2">
<div id="tf"><label for="ThumbNail">画像データ：</label><input type="file" name="ThumbNail" id="ThumbNail"></div>
<div id="tu"><label for="ThumbNailURL">画像URL：</label><input type="text" name="ThumbNailURL" id="ThumbNailURL" style="width:260px" title="画像URLを入力"><br></div>
</div>
</td></tr>

<tr><td><label for="d"><h2>Gameデータ</h2></label><br>
現在の設定：
<c:if test="${g.gameType =='data' }"><label><font color="red">Gameデータ(unity3d形式のみ。10mまで)</font><br></label></c:if>
<c:if test="${g.gameType =='url' }"><label><font color="red">GameURL(unity3d形式のデータURL)</font><br></label>${g.gameURL}<br></c:if>
<c:if test="${g.gameType =='hpurl' }"><label><font color="red">GameURL(外部サイトURL)</font><br></label></td>${g.hpURL}<br></c:if>
<input type="checkbox" name="GameChange" id="GameChange" value="GameChange">変更する<br>
<div id="GameChange1">
<label><input type="radio" name="GameType" value="data" id="data" checked>Gameデータ(unity3d形式のみ。10mまで)</label><br>
<label><input type="radio" name="GameType" value="url" id="url">GameURL(unity3d形式のデータURL)<br></label>
<label><input type="radio" name="GameType" value="hpurl" id="hp">GameURL(外部サイトURL)<br></label></td>
</div>
</tr><tr><td>
<div id="GameChange2">
<div id="G1"><label for="GameData">Gameデータ：</label>
<input type="file" name="GameFile" id="GameData"><br></div>
<div id="G2"><label for="GameURL">GameURL:</label>
<input type="text" name="GameURL" id="GameURL" style="width:260px" title="unity3dデータのURLを入力"/><br></div>
<div id="G3"><label for="HpURL">GameURL:</label>
<input type="text" name="HpURL" id="HpURL" style="width:260px" title="すでにゲームの出来るURLを入力"/><br></div>
</div>
<div style="position: relative;top: 20;">
<b>固定タグは3つまでです。複数登録は｢,  ｣で区切りを入れてください</b><br>

<h2>固定タグ：<input type="text" name="fixTag" value="${tag.fixTag}"id="tag"></h2>
</div>
</td></tr><tr><td>
<div align="left"><h2>変更/削除キー ：<input type="password" name="pass"  class="required"  value="${g.pass}" style="width: 117;"></h2></div></td>
<td> <div align="center"><input type="submit" value="変更！" class="submit" style="font-size:large;"/><input type="hidden" name="key" value="${f:h(g.key)}"></div>
</td></tr >
<tr class="bottom"><td colspan="2">&nbsp;</td></tr>
</table>
</div>
</form>



サムネイル画像URL、GameURLで利用する際はdropboxなどオンラインストレージが便利です



</body>
</html>

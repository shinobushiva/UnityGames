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

<title>ゲーム登録</title>
</head>
<body>

<script type="text/javascript">
jQuery(document).ready(function($) {  
    $.updnWatermark.attachAll();  
});  

$(function(){
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
<h1>ゲームを登録する</h1>
<div id="commentForm"> 
<table class="purchase-options" border="0" align="center">
<tr class="top"><td colspan="2">&nbsp;</td></tr>
<tr>
<form action="${f:url('upload')}" method="post" enctype="multipart/form-data">
<td><label for="GameName"><h2>Game名</h2></label></td>

<td rowspan="6"><h2>Game説明文</h2>(HTML,css,Javascript使用できます)<br>
<textarea rows="13" cols="60" name="Contents" title="ゲームの内容を記入してください。改行は<br>を使用してください。"></textarea><br>
<h2>操作方法</h2>(HTML,css,Javascript使用できます)<br>
<textarea rows="13" cols="60" name="Operations" title="出来るだけ詳しくアクションボタンの説明を記入してください。改行は<br>です。"></textarea>
</td>
</tr><tr><td><label for="GameName">Game名：</label><input type="text" name="GameName" id="GameName" title="Game名を入力"/><br></td>
</tr>
<tr>
<td><label for="ThumbNail"><h2>サムネイル画像</h2></label><br>

サムネイル画像の登録方法をお選びください<br>
<label><input type="radio" name="t"  id="tdata" checked>画像データをアップロードする(画像形式のみ)</label><br>
<label><input type="radio" name="t"  id="turl">画像URLを指定する(画像データのURL)<br></label></td>

</tr><tr><td><div id="tf"><label for="ThumbNail">画像データ：</label><input type="file" name="ThumbNail" id="ThumbNail"></div>
<div id="tu"><label for="ThumbNailURL">画像URL：</label><input type="text" name="ThumbNailURL" id="ThumbNailURL" style="width:260px" title="画像URLを入力"><br></div>
</td></tr>
<tr><td><label for="d"><h2>Gameデータ</h2></label><br>
Gameデータを投稿するかGameURLを投稿するかお選びください<br>
<label><input type="radio" name="d"  id="data" checked>Gameデータ(unity3d形式のみ。10mまで)</label><br>
<label><input type="radio" name="d"  id="url">GameURL(unity3d形式のデータURL)<br></label>
<label><input type="radio" name="d"  id="hp">GameURL(外部サイトURL)<br></label></td>
</tr><tr><td>
<div id="G1"><label for="GameData">Gameデータ：</label>
<input type="file" name="GameFile" id="GameData"><br></div>

<div id="G2"><label for="GameURL">GameURL:</label>
<input type="text" name="GameURL" id="GameURL" style="width:260px" title="unity3dデータのURLを入力"/><br></div>
<div id="G3"><label for="HpURL">GameURL:</label>
<input type="text" name="HpURL" id="HpURL" style="width:260px" title="すでにゲームの出来るURLを入力"/><br></div>
</td></tr>
<tr><td>
<div align="left"><h2>変更/削除キー:<input type="password" name="pass"></h2></div></td><td> <div align="right"><input type="submit" value="ゲーム登録！" style="font-size:large;"/></div>
</td></tr >
<tr class="bottom"><td colspan="2">&nbsp;</td></tr>
</form>
</table>
</div>
サムネイル画像URL、GameURLで利用する際はdropboxなどオンラインストレージが便利です

</body>
</html>

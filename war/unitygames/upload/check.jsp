<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
<title>ゲーム登録</title>
</head>
<body>

<script type="text/javascript">
$(function(){
$( "#tu" ).hide();
$( "#G2" ).hide();
$( "div#tu" ).hide();
	$("#data").click(function(){
		$( "#G1" ).fadeIn("fast");
		$( "#G2" ).hide();
	});
	$("#url").click(function(){
		$( "#G1" ).hide();
		$( "#G2" ).fadeIn("fast");
	});
	$("#tdata").click(function(){
		$( "#tf" ).fadeIn("fast");
		$( "#tu" ).hide();
	});
	$("#turl").click(function(){
		$( "#tf" ).hide();
		$( "#tu" ).fadeIn("fast");
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
<table class="purchase-options" border="0" align="center">
<tr class="top"><td colspan="2">&nbsp;</td></tr>
<tr>
<form action="${f:url('upload')}" method="post" enctype="multipart/form-data">
<td><label for="GameName"><h2>Game名</h2></label></td>

<td rowspan="6"><h2>Game説明文</h2><br>
${Contents}<br>
<h2>操作方法</h2><br>
${Operations}</td>
</tr><tr><td>${GameName}<br></td>
</tr>
<tr>
<td><label for="ThumbNail"><h2>サムネイル画像</h2></label><br>
${ThumbNail.fileName}
</td>

</tr><tr><td>
</td></tr>
<tr><td><label for="d"><h2>Gameデータ</h2></label><br>
${GameFile.fileName}
</td>
</tr><tr><td>
</td></tr>
<tr><td colspan="2">
<div align="right"><input type="submit" value="ゲーム登録！" style="font-size:large;"/></div>
</td></tr >
<tr class="bottom"><td colspan="2">&nbsp;
<input type="hidden" name="GameName" value="${GameName}">
<input type="hidden" name="ThumbNailURL" value="${ThumbNailURL}">
<input type="hidden" name="GameFile" value="${GameFiles}">
<input type="hidden" name="ThumbNail" value="${ThumbNails}">
<input type="hidden" name="Contents" value="${Contents}">
<input type="hidden" name="Operations" value="${Operations}">
</td></tr>




</form>
</table>
サムネイル画像URL、GameURLで利用する際はdropboxなどオンラインストレージが便利です

</body>
</html>

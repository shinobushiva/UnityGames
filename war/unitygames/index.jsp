<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<link href="/css/css.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
<title>Index</title>


</head>
<body>

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
													<a href="/unitygames/" style="color: rgb(164, 171, 174); text-decoration: none; ">TOP</a></td>
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

<a href="upload/">Uploadする!!ここくりっく！右上のManualなどは現在意味ないです</a><br>

<table border="1">
<tr>
<c:forEach var="g" items="${GameList}" varStatus="loop">

<td align="center" valign="middle"width="200">

<c:choose>
<c:when test="${empty g.hpURL}">
<a href="/unitygames/Game?id=${f:h(g.key.id)}" class="title">${g.gameName }</a><br>
<c:set var="url" value="/unitygames/Game?id=${f:h(g.key.id)}"/>
</c:when>
<c:when  test="${not empty g.hpURL}" >
<a href="${g.hpURL}" class="title">${g.gameName }</a><br>
<c:set var="url" value="${g.hpURL}"/>
</c:when>
</c:choose>
<c:choose>
<c:when test="${empty g.thumbNailURL}">
<a href="${url}"><img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.thumbNailKey)}"width="150" height="150" class="image"/></a><br>
</c:when>
<c:when test="${not empty g.thumbNailURL}">
<a href="${url}"><img src="${g.thumbNailURL}"width="150" height="150" class="image"></a><br>
</c:when>
</c:choose>
</td><td width="300">
<script type="text/javascript">

$(function(){
$( "#out-${g.key.id}" ).hide();
	$("#change-${g.key.id}").click(function(){
		$( "#out-${g.key.id}" ).show();
		$( "#br-${g.key.id}" ).hide();
	});

    

});
</script>
<div align="right"><fmt:formatDate  value="${g.date}" pattern="MM/dd HH:mm" /><br></div>

<div align="right">アクセス数：${g.access}　コメント数：未実装　評価：未実装</div>
<h4>内容</h4>
${g.contents }
<h4>操作説明</h4>
${g.operations }<br>
<div align="right">
<form action="change" method="post">
<a id="change-${g.key.id}"style="font-size: x-small;">変更/削除</a><a id="out-${g.key.id}"><input type="password" name="Pass" style="width: 40px;">
<button type="submit" ><img id="sub" src="/images/red.gif" /></button> 
</a>
<input type="hidden" name="key" value="${f:h(g.key)}">        
</form>
<div id="br-${g.key.id}"><br><br></div>
</div>

</td>
<c:if test="${loop.count mod 3 == 0}"></tr><tr></c:if>

</c:forEach>

</tr>
</table>


</body>
</html>

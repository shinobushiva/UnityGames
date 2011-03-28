<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
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

<td align="center" colspan="2">
<fmt:formatDate  value="${g.date}" pattern="MM/dd HH:mm" /><br>
<c:choose>
<c:when test="${empty g.thumbNailURL}">
<img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.thumbNailKey)}"width="100" height="100"/><br>
</c:when>
<c:when test="${not empty g.thumbNailURL}">
<img src="${g.thumbNailURL}"width="100" height="100"><br>
</c:when>
</c:choose>
<c:choose>
<c:when test="${empty g.hpURL}">
<input type="button" onClick="location.href='/unitygames/Game?key=${f:h(g.key)}'" value="${g.gameName }"><br>
</c:when>
<c:when  test="${not empty g.hpURL}">
<input type="button" onClick="location.href='${g.hpURL}'" value="${g.gameName }"><br>
</c:when>
</c:choose>
<script type="text/javascript">

$(function(){
$( "#out-${g.key.id}" ).hide();
	$("#change-${g.key.id}").click(function(){
		$( "#out-${g.key.id}" ).fadeIn("fast");
	});

    $('.inputWithImge').each(function() {
        $(this).add($(this).next()).wrapAll('<div class="imageInputWrapper"></div>');
      });
      $('#calendar').click(function() { alert('Popup some calendar here'); });


});
</script>
<table><tr><td>
<a id="change-${g.key.id}"style="font-size: x-small;"  >変更/削除</a></td><td><span id="out-${g.key.id}"><input type="password" name="Pass" style="width: 40px;"class="inputWithImge">
</span></td></tr>
</table>
</td>
<c:if test="${loop.count mod 3 == 0}"></tr><tr></c:if>
</c:forEach>
</tr>
</table>

</body>
</html>

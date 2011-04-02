<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<link href="/css/css.css" rel="StyleSheet" type="text/css" />

<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
<title>Index</title>


</head>
<body>

<jsp:include page="/share/header.jsp" />
<br><br><br><br><br><br><br><br>

<table border="0" >
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
	 $("#tabs-${g.key.id}").tabs();  
});
$(function(){
	
	$( "#out-${g.key.id}" ).hide();
	$("#change-${g.key.id}").click(function(){
		$( "#out-${g.key.id}" ).show();
		$( "#br-${g.key.id}" ).hide();
	});
});
</script>
<div align="right">
<c:if test="${not empty g.hpURL}"><a style="font-size: x-small;color: red;">外部サイト</a>
</c:if>
<fmt:formatDate  value="${g.date}" pattern="MM/dd HH:mm" /><br></div>


<div id="tabs-${g.key.id}" style="word-break:break-all"> 
		<ul>  
      <li><a href="#tab1-${g.key.id}"><span>ゲーム説明</span></a></li>  
      <li><a href="#tab2-${g.key.id}"><span>操作内容</span></a></li>  
	  <div align="right"><span>アクセス数：${g.access}</span><br><span>コメント数：${g.comment}</span></div> 	
    
	  </ul>   
		 
		<div id="tab1-${g.key.id}">  
		<p>${g.contents}</p>
		</div >
		<div id="tab2-${g.key.id}">
		<p>${g.operations}</p>
		</div>
		</div>
		
<div align="right">
<form action="change" method="post">
<a id="change-${g.key.id}"style="font-size: x-small;">変更/削除</a><a id="out-${g.key.id}"><input type="password" name="Pass" style="width: 40px;">
<button type="submit" ><img id="sub" src="/images/red.gif" /></button> 
</a>
<input type="hidden" name="key" value="${f:h(g.key)}">        
</form>
<div id="br-${g.key.id}"><br><br></div>
</div>

</td><td>&nbsp;</td>
<c:if test="${loop.count mod 3 == 0}"></tr><tr></c:if>

</c:forEach>

</tr>
</table>


</body>
</html>

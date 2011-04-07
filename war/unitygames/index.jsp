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
<link href="/css/button.css" rel="StyleSheet" type="text/css" />
<script src="/js/jquery-1.5.1.min.js"></script>
		<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
<title><fmt:message key="unitygames.top" /></title>


</head>
<body>

<jsp:include page="/share/header.jsp" />

<script type="text/javascript">
$(function(){
	 $("#tabss").tabs();  

$("#tak").click(function(){
	$( "#k" ).val("");
});
$("#kik").click(function(){
	$( "#t" ).val("");
});



});
function Check(){
	var flag = 0;
	var str1 = $("#k").val();
	var str2 = $("#t").val();
	if(str1.length == 0 && str2.length == 0){flag = 1;}
	if(flag){
		return false; // 送信を中止
	}
	else{return true; // 送信を実行
	}}
</script>
<form action="/search" method="post" onSubmit="return Check()">
<div id="tabss" style="position:absolute;top: 120;left:400; width: 459;"> 
		<ul>  
      <li><a href="#tab11" id="kik"><span><fmt:message key="keyword" /></span></a></li>  
      <li><a href="#tab22" id="tak"><span><fmt:message key="tag" /></span></a></li>  
	  </ul>   
		
		<div id="tab11" >
		<div style="position:absolute; bottom: 5;">  
		<input type="text" name="word" style=" font-size: 20;" id="k"><button class="silver"><span  class="button">a</span></button>
		</div>
		<br>
		</div >
		<div id="tab22">
		<div style="position:absolute; bottom: 5;">  
		<input type="text" name="tag" style=" font-size: 20;" id="t"><input type="submit">
		</div>
		<br></div>
		</div>
		</form>
<br><br><br><br><br><br><br><br><br><br><br><br>

<table border="0" style="width: 1000;">
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
<a href="${url}"><img src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"width="150" height="150" class="image"/></a><br>
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
<c:if test="${not empty g.hpURL}"><a style="font-size: x-small;color: red;"><fmt:message key="outside" /></a>
</c:if>
<fmt:formatDate  value="${g.date}" pattern="MM/dd HH:mm" /><br></div>


<div id="tabs-${g.key.id}" style="word-break:break-all"> 
		<ul>  
      <li><a href="#tab1-${g.key.id}"><span><fmt:message key="explanation" /></span></a></li>  
      <li><a href="#tab2-${g.key.id}"><span><fmt:message key="operation" /></span></a></li>  
	  <div align="right"><span><fmt:message key="access" />：${g.access}</span><br><span><fmt:message key="comment" />：${g.comment}</span></div> 	
    
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
<a id="change-${g.key.id}"style="font-size: x-small;"><fmt:message key="change.delete" /></a><a id="out-${g.key.id}"><input type="password" name="Pass" style="width: 40px;">
<button type="submit" ><img id="sub" src="/images/red.gif" /></button> 
</a>
<input type="hidden" name="key" value="${f:h(g.key)}">        
</form>
<div id="br-${g.key.id}"><br><br></div>
</div>

</td><td>&nbsp;</td>
<c:if test="${loop.count mod 2 == 0}"></tr><tr></c:if>

</c:forEach>

</tr>
</table>


</body>
</html>

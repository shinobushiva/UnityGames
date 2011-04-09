<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns:og="http://ogp.me/ns#"  xmlns:mixi="http://mixi-platform.com/ns#">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	if (request.getParameter("name") != null) {
%>
<title><%=request.getParameter("name")%></title>
<%
	}else{
%>
<title>header</title>
<%
	}
%>
<meta name="mixi-check-robots" content="noimage" />

	<script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
	
<link href="/css/lavalamp.css" rel="StyleSheet" type="text/css" />
<link href="/css/css.css" rel="StyleSheet" type="text/css" />
<link href="/css/docs.css" rel="StyleSheet" type="text/css" />
<link href="/css/jquery-ui-1.8.11.custom.css" rel="StyleSheet" type="text/css"  />
	<link href="/css/button.css" rel="StyleSheet" type="text/css" />
	<script src="/js/jquery-1.5.1.min.js"></script>
<script src="/js/jquery-ui-1.8.11.custom.min.js"></script>
<script src="/js/jquery.spasticNav.js"></script>

</head>

<body>
<div style="width:1000; ">


<a href="/unitygames/"><img src="/images/logo/Logo.png" width="250px" style="position: absolute; top: 30; right: 1020px;"></a>

 <ul id="nav1">
        <li id="selected"><a href="/unitygames/"><fmt:message key="home" /></a></li>
        <li><a href="/howto"><fmt:message key="howto" /></a></li>
        <li><a href="/tutorial"><fmt:message key="tutorial" /></a></li>
        <li><a href="/unitygames/upload"><fmt:message key="upload" /></a></li>
    </ul>
    

  <script type="text/javascript">
  $('#nav1').spasticNav();
        </script>
</div>

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
	}};
	
</script>
<form action="/search" method="post" onSubmit="return Check()">
<div id="tabss" style="position:absolute;top: 120;left:400; width: 459;"> 
		<ul>  
      <li><a href="#tab11" id="kik"><span><fmt:message key="keyword" /></span></a></li>  
      <li><a href="#tab22" id="tak"><span><fmt:message key="tag" /></span></a></li>  
	  </ul>   
		
		<div id="tab11" >
		<div style="position:absolute; bottom: 3;left: 30;">  
		<input type="text" name="word" style=" font-size: 20;width: 300" id="k"><button style="background-color:transparent;border: 0;width:110 ;height: 40; position: relative;bottom: 0;left: 5;" class="button silver"><span style="color: white;font-size: 20;width: 100;position: relative;right: 7;bottom: 1;" ><fmt:message key="button.search"></fmt:message></span></button>
		</div>
		<br>
		</div >
		<div id="tab22">
		<div style="position:absolute; bottom: 3;left: 30;">  
		<input type="text" name="tag" style=" font-size: 20;width: 300" id="t"><button style="background-color:transparent;border: 0;width:110 ;height: 40; position: relative;bottom: 0;left: 5;" class="button silver"><span style="color: white;font-size: 20;width: 100;position: relative;right: 7;bottom: 1;" ><fmt:message key="button.search"></fmt:message></span></button>
		</div>
		<br></div>
		</div>
		</form>


</body>
</html>

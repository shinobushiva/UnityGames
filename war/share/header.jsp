<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<div style="text-align: right;height: 25px;margin-right: 90px;margin-top: -10px;margin-bottom: 10px;">

	<c:if test="${!isLogin}">
		<a href="/login/oAuth"> <img src="/images/logo/t.png" width="15px">ログイン</a>
	</c:if>
	<c:if test="${isLogin}">
	<script type="text/javascript">
	$(function(){
		$.getJSONP("http://api.twitter.com/1/users/show.json?id=${twitter.id}&callback={callback}",function(e){
			$("#loginTwitterImage").attr("src",""+e.profile_image_url);
			$("#loginTwitterName").html(""+e.screen_name);
			$("#userPage").attr("href","/user/"+e.screen_name);
			
		});
	});
	</script>
		
		<div>
		<a id="userPage">
		<div style="display: inline-block;"><img id="loginTwitterImage" width="20"style="margin-bottom: -5px;"></div>
		<div style="display: inline-block;"><span id="loginTwitterName" style="font-size: 20px;"></span></div>
		</a>
		<div style="display: inline-block;"><a href="/login/logOut" style="clear: both;">ログアウト</a></div>
		</div>
		
	</c:if>
</div>
<div style="margin-top: -5px;">
	<a href="/" style="float: left;"><img src="/images/logo/Logo.png"
		width="250px" style="padding-top: 14px"> </a>

	<ul id="nav1" style="display: inline; left: 0;">
		<li id="selected"><a href="/"><fmt:message key="home" /> </a>
		</li>
		<li><a href="/howto"><fmt:message key="howto" /> </a>
		</li>
		<li><a href="/tutorial"><fmt:message key="tutorial" /> </a>
		</li>
		<li><a href="/unitygames/upload"><fmt:message key="upload" />
		</a>
		</li>
	</ul>

	<script type="text/javascript">
		$('#nav1').spasticNav();
	</script>
</div>
<div style="clear: both;"></div>
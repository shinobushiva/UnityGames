<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<!-- twitter follow badge by go2web20 -->
<script src='http://www.go2web20.net/twitterfollowbadge/1.0/badge.js'
	type='text/javascript'></script>
<script type='text/javascript' charset='utf-8'>tfb.account = 'UGames';
tfb.label = 'follow-me';
tfb.color = '#35ccff';
tfb.side = 'r';
tfb.top = 136;
tfb.showbadge();
--></script>
<!-- end of twitter follow badge -->
<div class="loginHeader">

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
				<div class="inline">
					<img id="loginTwitterImage" width="20px">
				</div>
				<div class="inline">
					<span id="loginTwitterName"></span>
				</div> </a>
			<div class="inline">
				<a href="/login/logOut" class="clear">ログアウト</a>
			</div>
		</div>

	</c:if>
</div>
<div class="headerPosition">
	<a href="/" class="floatLeft"><img src="/images/logo/Logo.png"
		width="250px" class="logo"> </a>

	<ul id="nav1">
		<li id="selected"><a href="/"><fmt:message key="home" /> </a></li>
		<li><a href="/howto"><fmt:message key="howto" /> </a></li>
		<li><a href="/tutorial"><fmt:message key="tutorial" /> </a></li>
		<li><a href="/unitygames/upload/newGame"><fmt:message
					key="upload" /> </a></li>
	</ul>

	<script type="text/javascript">
		$('#nav1').spasticNav();
	</script>
</div>
<div class="clear"></div>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<div style="text-align: right;">
	<a href="/login/oAuth"> <img src="/images/logo/t.png" width="15px">ログイン</a>
</div>
<div>
	<a href="/" style="float: left;"><img src="/images/logo/Logo.png"
		width="250px" style="padding-top: 14px"> </a>

	<ul id="nav1" style="display: inline; left: 0;">
		<li id="selected"><a href="/"><fmt:message key="home" /> </a></li>
		<li><a href="/howto"><fmt:message key="howto" /> </a></li>
		<li><a href="/tutorial"><fmt:message key="tutorial" /> </a></li>
		<li><a href="/unitygames/upload"><fmt:message key="upload" />
		</a></li>
	</ul>

	<script type="text/javascript">
		$('#nav1').spasticNav();
	</script>
</div>
<div style="clear: both;"></div>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>LoginCheck</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/login.css" rel="stylesheet" type="text/css" />
<%@ include file="/share/js.jsp"%>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>
	<h1 align="center">ログインしてください</h1>
	<table class="table">
		<tr>
			<td align="center" class="twitterFrame"><a
				href="/login/OAuth?loginType=twitter">
					<div class="marginTopHundred">
						<div>Twitterでログイン</div>
						<div>
							<img src="/images/logo/twitter_logo.png" />
						</div>
					</div> </a></td>
			<td class="space">&nbsp;</td>
			<td align="center" class="guestFrame"><a
				href="/unitygames/upload/newGame/guest"><div
						class="marginTopHundred">
						<div href="/unitygames/upload/">Guestでログイン</div>
						<div>
							<img src="/images/face.png" />
						</div>
					</div> </a></td>
		</tr>
	</table>
	<%@ include file="/share/footer.jsp"%>
</body>
</html>

<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<title>How to</title>

</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<pre style="font-size: 15px;">
<h1><fmt:message key="what.unitygames"/></h1>
<fmt:message key="what.unitygames1"/>

<h1><fmt:message key="use.unitygames"/></h1>

<h1><fmt:message key="view.menu"/></h1>
<img src="/images/howto/menu.png" />
<fmt:message key="view.menu1"/>
<fmt:message key="view.menu2"/>
<fmt:message key="view.menu3"/>
<fmt:message key="view.menu4"/>


<h1><fmt:message key="how.search"/></h1>
<fmt:message key="how.search1"/>
<img src="/images/howto/search.png" />

<h1><fmt:message key="how.play"/></h1>
<h2><fmt:message key="game.select"/></h2>
<fmt:message key="game.select1"/>
<img src="/images/howto/gameSelect1.png" style="margin-bottom: 20px;" /><img
			src="/images/howto/gameSelect2.png" style="margin-left: 30px;" />
<h2><fmt:message key="view.game"/></h2>
<table>
			<tr>
				<td>
<img src="/images/howto/game.png" style="width: 450;" />
				</td>
				<td><pre>
<fmt:message key="view.game1"/>

<fmt:message key="view.game2"/>

<fmt:message key="view.game3"/>

<fmt:message key="view.game4"/>

<fmt:message key="view.game5"/>

<fmt:message key="view.game6"/>

<fmt:message key="view.game7"/>

<fmt:message key="view.game8"/>

<h2><fmt:message key="ugLink"/></h2>
	<fmt:message key="ugLink1"/>
	<fmt:message key="ugLink2"/>

<h2 id="code"><fmt:message key="commentary"/></h2>
	<fmt:message key="commentary1"/>
<h3>Youtube</h3>
	<fmt:message key="youtube"/>
<h3><fmt:message key="script"/></h3>
	<fmt:message key="script2"/>
	
		<fmt:message key="script1"/>
	
	||<

<fmt:message key="script3"/>
<h3><fmt:message key="script4"/></h3>
<fmt:message key="script5"/>

http://www.youtube.com/watch?v=○○○

exampleScript1
	
	>|js|
		function ...
	||<
	
	>|cs|
		public void ...
	||< 
	
exampleScript2
	
	>|js|
		function ...
	||<
	
	>|cs|
		public void …
	||<
	</pre>
	</td>
			</tr>
	</table>
	<div style="clear: both;"></div>
<h1 id="user"><fmt:message key="user.page"/></h1>
<img src="/images/howto/user.png" />
<h2><fmt:message key="account"/></h2>
	<fmt:message key="account1"/>
	
	<fmt:message key="account2"/>

<h2><fmt:message key="game.history"/></h2>
	<fmt:message key="game.history1"/>

<h2><fmt:message key="howto.tweet"/></h2>
	<fmt:message key="howto.tweet1"/>

<h2><fmt:message key="howto.edit"/></h2>
	<fmt:message key="howto.edit1"/>

<h1 id="gameRegist"><fmt:message key="howto.game.regist"/></h1>
<img src="/images/howto/register.png" />
	<fmt:message key="howto.game.regist1"/>
	
	<fmt:message key="howto.game.regist2"/>
	
	<fmt:message key="howto.game.regist3"/>
	
	<fmt:message key="howto.game.regist4"/>
	
	<fmt:message key="howto.game.regist5"/>
	
	<fmt:message key="howto.game.regist6"/>
	
	<fmt:message key="howto.game.regist7"/>
	
	<fmt:message key="howto.game.regist8"/>
	
	<fmt:message key="howto.game.regist9"/>
	
	<fmt:message key="howto.game.regist10"/>

<h1><fmt:message key="howto.game.change"/></h1>
<fmt:message key="howto.game.change1"/>
</pre>
	<%@ include file="/share/footer.jsp"%>

</body>
</html>

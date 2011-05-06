<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>


<div style="">

	<div
		style="float: left; width: 500px; border: solid; height: 1000px; font-size: 15px;">
		<%--	画像とかプロフィール部分	--%>
		<div>
			<div style="float: left; margin-left: 10px; margin-top: 10px;">
				<img src="${tp}" />
			</div>
			<div style="display: inline-block;">${u.name}</div>
			<div>@${u.screenName }${u.location }</div>
			<div style="">${u.description}</div>
		</div>
		<%--	Tweet部分	--%>
		<div style="clear: both;">
	<div style="height: 50px;padding-top: auto;padding-bottom: auto;border: solid;">testつぶやきてすとおおお${t}</div>
	
			<c:forEach var="t" items="${tweet}">
	
	<div style="height: 50px;padding-top: auto;padding-bottom: auto;border: solid;">${t}</div>
	
	</c:forEach>
		</div>

	</div>
	<div style="width: 500px; border: solid; display: inline-block;">

		その他</div>
</div>
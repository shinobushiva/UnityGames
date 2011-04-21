<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>View</title>
</head>
<body>
<table align="center">
				<tr>
					<c:forEach var="g" items="${GameList}" varStatus="loop">


						<td>
							<table width="600" border="0" style="position: relative;">
								<tr>
									<td style="padding-left: 20px;"><script
											type="text/javascript">
										$(function() {
											$("#tabs-${g.key.id}").tabs();
										});
										$(function() {

											$("#out-${g.key.id}").hide();
											$("#change-${g.key.id}").click(
													function() {
														$("#out-${g.key.id}")
																.show();
														$("#br-${g.key.id}")
																.hide();
													});
										});
									</script>
										<div align="right">
											<c:if test="${not empty g.hpURL}">
												<a style="font-size: x-small; color: red;"><fmt:message
														key="outside" /> </a>
											</c:if>
											<fmt:message key="entryDay" />
											：
											<fmt:formatDate value="${g.date}" pattern="MM/dd HH:mm" />
											<br>
										</div>


										<div id="tabs-${g.key.id}" style="word-break: break-all">
											<ul>
												<div style="position: absolute; left: 35px;">
													<c:choose>
														<c:when test="${empty g.hpURL}">
															<a href="/unitygames/Game?id=${f:h(g.key.id)}"
																class="title">${g.gameName }</a>
															<c:set var="url"
																value="/unitygames/Game?id=${f:h(g.key.id)}" />
														</c:when>
														<c:when test="${not empty g.hpURL}">
															<a href="${g.hpURL}" class="title">${g.gameName }</a>
															<c:set var="url" value="${g.hpURL}" />
														</c:when>
													</c:choose>
												</div>

												<li style="position: relative; left: 170px; top: 75px;"><a
													href="#tab1-${g.key.id}"><span　><fmt:message
																key="explanation" /> </span> </a>
												</li>
												<li style="position: relative; left: 170px; top: 75px;"><a
													href="#tab2-${g.key.id}"><span><fmt:message
																key="operation" /> </span> </a>
												</li>
												<div align="right">
													<span><fmt:message key="access" />：${g.access}</span><br>
													<span><fmt:message key="comment" />：${g.comment}</span>
												</div>

											</ul>

											<div id="tab1-${g.key.id}">

												<c:choose>
													<c:when test="${empty g.thumbNailURL}">
														<a href="${url}"><img
															src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"
															width="150" height="150" class="image" /> </a>
														<br>
													</c:when>
													<c:when test="${not empty g.thumbNailURL}">
														<a href="${url}"><img src="${g.thumbNailURL}"
															width="150" height="150" class="image"> </a>
														<br>
													</c:when>
												</c:choose>



												<a
													style="position: absolute; left: 200px; top: 110px; font-size: 15px; font: bold;">${g.contents}</a>
												<c:forEach var="ft" items="${g.fixTags}">
													<b
														style="font-size: 20px; position: relative; left: 160px; top: -155px"><a
														href="/search?tag=${ft.name}">${ft.name}</a> </b>
												</c:forEach>
											</div>
											<div id="tab2-${g.key.id}">
												<c:choose>
													<c:when test="${empty g.thumbNailURL}">
														<a href="${url}"><img
															src="/unitygames/thumbNail?thumbNailKey=${f:h(g.key)}"
															width="150" height="150" class="image" /> </a>
														<br>
													</c:when>
													<c:when test="${not empty g.thumbNailURL}">
														<a href="${url}"><img src="${g.thumbNailURL}"
															width="150" height="150" class="image"> </a>
														<br>
													</c:when>
												</c:choose>

												<a
													style="position: absolute; left: 200; top: 110px; font-size: 15px; font: bold;">${g.operations}</a>
											</div>
										</div>

										<div align="right" style="position: relative; bottom: 25px;">
											<form action="change" method="post">
												<a id="change-${g.key.id}" style="font-size: x-small;"><fmt:message
														key="change.delete" /> </a><a id="out-${g.key.id}"><input
													type="password" name="Pass" style="width: 40px;">
													<button type="submit">
														<img id="sub" src="/images/red.gif" />
													</button> </a> <input type="hidden" name="key" value="${f:h(g.key)}">
											</form>
											<div id="br-${g.key.id}"></div>
										</div></td>
								</tr>
							</table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<c:if test="${loop.count mod 2 == 0}">
				</tr>
				<tr>
				</c:if>

				</c:forEach>
				</tr>
			</table>
</body>
</html>
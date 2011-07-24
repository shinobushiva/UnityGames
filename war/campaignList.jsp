<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>



<div>
	<c:forEach var="c" items="${campaigns}">
		<div id="container">
			<!------------------------------------- THE CONTENT ------------------------------------------------->
			<div id="lofslidecontent45" class="lof-slidecontent"
				style="width: 980px; height: 340px;">
				<div class="preload">
					<div></div>
				</div>
				<!-- MAIN CONTENT -->
				<div class="lof-main-outer" style="width: 980px; height: 340px;">
					<ul class="lof-main-wapper">
						<c:forEach var="g" items="${c.games}">
							<li><%@ include file="/share/patternDistinction.jsp"%>
								<img src="${thUrl}" alt="picture" title="${g.gameName}"
								width="980px" height="340px" />
								<div class="lof-main-item-desc">
									<h3>
										<a id="campainGameName" target="_parent"
											href="/unitygames/game/ug${g.key.id}">${g.gameName}</a>
									</h3>
									<p id="contents">
										${g.contents}
									</p>
									<a class="readmore" href="/unitygames/game/ug${g.key.id}">Play! </a>
								</div></li>
						</c:forEach>
					</ul>
				</div>
				<!-- END MAIN CONTENT -->
				<!-- NAVIGATOR -->
				<div class="lof-navigator-wapper">
					<div onclick="return false" href="" class="lof-next">Next</div>
					<div class="lof-navigator-outer">
						<ul class="lof-navigator">
							<c:forEach var="g" items="${c.games}">
								<li><%@ include file="/share/patternDistinction.jsp"%>
									<img src="${thUrl}" alt="picture" width="50px" height="50px" />
								</li>
							</c:forEach>
						</ul>
					</div>
					<div onclick="return false" href="" class="lof-previous">Previous</div>
				</div>
				<!-- END NAVIGATOR -->
			</div>
			<!------------------------------------- END OF THE CONTENT ------------------------------------------------->
			<div style="margin-left: 30px;">
				<div style="display: inline-block;">
					<img src="/images/campaign.png" />
				</div>
				<div style="display: inline-block;" class="campaignTitle">${c.title}</div>
			</div>
		</div>
	</c:forEach>
</div>
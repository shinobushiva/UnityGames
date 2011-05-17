<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%--ページング --%>
<script type="text/javascript">
	function pageselectCallback(page_index, jq) {
		var new_content = jQuery(
				'#hiddenresult div.result:eq(' + page_index + ')').clone();
		$('#Searchresult').empty().append(new_content);
		return false;
	}
	function initPagination() {
		var num_entries = jQuery('#hiddenresult div.result').length;
		$("#Pagination").pagination(num_entries, {
			callback : pageselectCallback,
			items_per_page : 1
		});
	}
	function initButtons() {
		var pg = $('#Pagination');
		$('#btnPrev').click(function() {
			pg.trigger('prevPage');
		});
		$('#btnNext').click(function() {
			$('#Pagination').trigger('nextPage');
		});
		$('#btnSet').click(function() {
			pg.trigger('setPage', [ 1 ]);
		});
	}

	$(document).ready(function() {
		initPagination();
		initButtons();
	});
</script>
<div style="float: left; width: 500px; height: 1000px; font-size: 15px;"
	id="twitter">
	<div>
		<div style="float: left; margin-left: 10px; margin-top: 10px;">
			<img src="${tp}" />
		</div>
		<div style="display: inline-block;">${u.name}</div>
		<div>@${u.screenName }${u.location }</div>
		<div style="">${u.description}</div>
	</div>
	<div style="clear: both;">

		<c:forEach var="t" items="${tweet}">

			<script type="text/javascript">
				$(function() {
					var url = "http://twitter.com/statuses/show/${t.tweetId}.json?callback={callback}";
					$.getJSONP(url, function(obj) {
						var s = "";

						s += "<div>" + obj.text + "</div>";
						s += "<div>" + jQuery.timeago(obj.created_at)
								+ "</div>";
						$("#${t.tweetId}").html(s);
					});
				});
			</script>

			<hr />
			<div style="height: 50px; border: 1px;" id="${t.tweetId}"></div>

		</c:forEach>
		<hr />
	</div>

</div>
<div
	style="width: 500px; height: 1000px; border: solid; display: inline-block;">
	その他
	<div id="web" style="font-size: 30px;">Web: ${webUrl}</div>
	<div style="margin-top: 580px;">
		ゲーム投稿履歴<br>
		<div id="hiddenresult" style="display: none;">
			<div class="result">
				<c:forEach var="g" items="${gameList}" varStatus="loop">
					<script type="text/javascript">
						$(function() {

							var flag = "hide-${g.key.id}";
							var mp = $('.panel-${g.key.id}').position();
							$(".panel-${g.key.id}").css("visibility", "hidden");
							$("#ug${g.key.id}")
									.click(
											function(event) {
												var of = $("#twitter")
														.position();
												var ugpo = $("#ug${g.key.id}")
														.position();
												var position = $(
														'.panel-${g.key.id}')
														.position();
												var view = of.left;

												$(".panel-${g.key.id}").css(
														"left", ugpo.left);

												$(".panel-${g.key.id}").css(
														"top", 0);

												//$(".panel-${g.key.id}").show();
												$(".panel-${g.key.id}")
														.css("visibility",
																"visible");
												if (flag == "hide-${g.key.id}") {

													$(".panel-${g.key.id}")
															.css("z-index", "0");
													//	$(".panel-${g.key.id}").animate({
													//		left : "-500px"
													//	});
													$(".panel-${g.key.id}")
															.css("z-index", "0");

													$(".panel-${g.key.id}")
															.animate({
																left : view

															});
													flag = "show-${g.key.id}";

													var parents = 'panel';

													divObj = $("div");
													matchObj = new RegExp(
															parents);

													for (i = 0; i < divObj.length; i++) {
														//moreを含むものだけを取得
														if (divObj[i].id
																.match(matchObj)) {

															if ($("div")[i].id != "panel-${g.key.id}") {
																//他が出てたら元に戻す
																$("div")[i].style.left = mp.left;
																$("div")[i].style.visibility = "hidden";
															}
														}
													}

												} else {

													$(".panel-${g.key.id}")
															.animate({
																left : mp.left
															})
													flag = "hide-${g.key.id}";
													$(".panel-${g.key.id}")
															.css("visibility",
																	"hidden");
												}

											})
						});
					</script>


					<%@ include file="/share/patternDistinction.jsp"%>
					<div
						style="display: inline-block; text-align: center; z-index: 1; cursor: pointer;"
						id="ug${g.key.id}">
						<img src="${thUrl}" width="100" height="100" /><br>
						<div class="bounded" style="width: 150px; text-align: center;">${g.gameName}</div>

					</div>
					<c:if test="${loop.count mod 6 == 0 }">
			</div>
			<div class="result">

				</c:if>
				</c:forEach>
			</div>
		</div>
		<div id="Pagination" align="center"></div>
		<br style="clear: both;" />
		<div id="Searchresult">This content will be replaced when
			pagination inits.</div>
		<div align="center" style="margin-top: 30px;">
			<form method="get" action="#">
				<button type="button" id="btnPrev"
					style="width: 100px; height: 30px;">Previous</button>

				<button type="button" id="btnNext"
					style="margin-right: 50px; width: 100px; height: 30px;">Next</button>
			</form>
		</div>
		<style type="text/css">
#panel-frame {
	position: relative;
	position: absolute;
	margin-top: -1000px;
	max-width: 500px;
	position: fixed;
	max-width: 500px;
}
</style>
		<div id="panel-frame">
			<c:forEach var="g" items="${gameList}" varStatus="loop">
				<div class="panel-${g.key.id}" id="panel-${g.key.id}"
					style="background-color: aqua; width: 500px; max-height: 500px; margin-top: 0px; position: relative; position: absolute; z-index: -1;">

					<%@ include file="/share/patternDistinction.jsp"%>

					<div id="more-${g.key.id}" style="width: 490px;">

						<img src="${thUrl}" width="400" height="300"
							style="margin-left: 50px; margin-right: 50px; margin-top: 5px;" />
						<div style="text-align: center; font-size: 30px;">
							${g.gameName}</div>
						<div>アクセス数：${g.access} コメント数：${g.comment}</div>
						<div>ゲーム説明</div>
						<div>${g.contents}</div>
						<div>操作内容</div>
						<div>${g.operations}</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

</div>
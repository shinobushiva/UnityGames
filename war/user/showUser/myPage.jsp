<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<script type="text/javascript">
	$(function() {

		$("#tweet").charCount();

		$("#tweetButton").hide();
		$("#reg").hide();
		$("#web").click(function() {
			$("#url").hide();
			$("#reg").show();
		})

		$("#tweet").click(function() {
			$("#tweet").css("height", "50px");
			$("#tweetButton").show();
		})

	});
</script>
<div>
	<div
		style="float: left; width: 500px; border: solid; height: 1000px; font-size: 15px;"
		id="twitter">
		<div>
			<div>いまどうしてる？</div>
			<div>
				<textarea style="width: 482px; height: 30px;" id="tweet">未実装</textarea>

				<%--ここにdiv要素のカウンターがくる --%>
				<div id="tweetButton"
					style="float: right; margin-right: 20px; display: inline-block;">
					<input type="checkbox" name="tag">#UnityGamesをつけない
					<button style="">ついーと</button>

				</div>
			</div>
		</div>


		<div style="clear: both;">

			<c:forEach var="t" items="${tweet}">

				<div style="height: 50px; border: solid;">${t}</div>

			</c:forEach>
		</div>

	</div>
	<div
		style="width: 500px; height: 1000px; border: solid; display: inline-block;">
		<div>
			<div style="float: left; margin-left: 10px; margin-top: 10px;">
				<img src="${tp}" style="width: 50px; height: 50px;" />
			</div>
			<div style="display: inline-block;">${u.name}</div>
			<div>@${u.screenName }${u.location }</div>
			<div style="">${u.description}</div>
		</div>

		<div id="web" style="font-size: 30px; clear: both;margin-top: 50px;margin-bottom: 20px;">
			Web:
			<sapn id="url">${webUrl}</sapn>
			<span id="reg"><input type="text" name="" value="${webUrl}">
			<button>変更(未実装)</button> </span>
		</div>
		<div>
			セーブデータ管理
			<div style="overflow:auto;height: 400px; ">
				<div>
					<div
						style="display: inline-block; width: 240px; text-align: center;">ゲーム名</div>
					<div
						style="display: inline-block; width: 240px; text-align: center;">削除</div>
				</div>
				<%--forEachで回す--%>
				<div>
					<div
						style="display: inline-block; width: 240px; text-align: center;">○○○</div>
					<div
						style="display: inline-block; width: 240px; text-align: center;">
						<button>削除</button>
					</div>
				</div>
				<%--ここまでforEach--%>
			</div>
			<div style="">
				ゲーム管理<br>
				<c:forEach var="g" items="${gameList}">
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


				</c:forEach>
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

						<div id="more-${g.key.id}" style="border: solid; width: 490px;">

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
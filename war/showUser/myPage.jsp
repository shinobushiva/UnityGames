<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>user Index</title>
<%@ include file="/share/css.jsp"%>
<link href="/css/userPage.css" rel="stylesheet" type="text/css"/>
<%@ include file="/share/js.jsp"%>


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

<script type="text/javascript">
	$(function() {

		$
				.getJSONP(
						"http://api.twitter.com/1/users/show.json?id=${twitterId}&callback={callback}",
						function(e) {

							$("#twitterImage").attr("src",
									"" + e.profile_image_url);
							$("#twitterScreenName").html("@" + e.screen_name);
							$("#twitterName").html("" + e.name);

						});

		loadMyself();
		$("#tweet").charCount();
		$("#myselfEdit").hide();
		$("#tweetButton").hide();
		$("#reg").hide();

		$("#myself")
				.mouseover(
						function(event) {
							$("#myself").css("background-color", "yellow");
							$("#myself").css("cursor", "pointer");
							$("#myself")
									.append(
											"<span id='edit'style='color:red;'><fmt:message key='edit'/>(Click!!)</span>");
						})
		$("#myself").mouseout(function(event) {
			$("#myself").css("background-color", "white");
			$("#edit").remove();
		})

		$("#myself").click(function() {
			$("#myself").hide();
			$("#myselfEdit").show();
		})

		$("#myself-cancel").click(function() {
			$("#myselfEdit").hide();
			$("#myself").show();

		})

		$("#url")
				.mouseover(
						function(event) {
							$("#url").css("background-color", "yellow");
							$("#url").css("cursor", "pointer");
							$("#url")
									.append(
											"<span id='edit'style='color:red;font-size:10px;'><fmt:message key='edit'/>(Click!!)</span>");
						})
		$("#url").mouseout(function(event) {
			$("#url").css("background-color", "white");
			$("#edit").remove();
		})

		$("#url").click(function() {
			$("#url").hide();
			$("#reg").show();
		})
		$("#web-cancel").click(function() {
			$("#reg").hide();
			$("#url").show();
		})

		$("#tweet").click(function() {
			$("#tweetButton").show();
			$("#tweet").css("height", "50px");
		});

		$("#tweetUpdate").click(function() {

			var a = $("#tweet").val();

			var b = $("#noHashtag").is(':checked');

			$.ajax({
				type : "post",
				url : "/action/tweet",
				data : "tweet=" + a + "&" + "check=" + b,
				success : function() {
					$.notifyBar({
						html : "	<fmt:message key='tweeted'/>",
						delay : 2000,
						animationSpeed : "normal"
					});

				}

			})

			$("#tweet").val("");
		})

		$("#myself-change").click(function() {
			//	alert($("#myselfText").val());
			var a = $("#myselfText").val();
			var b = $("#ud").serialize();
			$.ajax({
				type : "post",
				url : "/action/myselfRegist",
				data : "myselfText=" + a + "&" + b,
				success : function() {
					loadMyself();
				}

			})
			$("#myselfEdit").toggle();
			$("#myself").toggle();
		});

		$("#web-change").click(function() {
			var a = $("#wu").val();
			var b = $("#ud").serialize();
			$.ajax({
				type : "post",
				url : "/action/webUrlRegist",
				data : "webUrl=" + a + "&" + b,
				success : function() {
					loadMyself();
				}

			})
			$("#url").toggle();
			$("#reg").toggle();
		});
		function loadMyself() {

			$.ajax({
				type : "post",
				url : "/ajax/myselfLoad",
				data : "id=${um.userId}",
				success : function(e) {
					$("#myself").html(e.user.myself);
					$("#myselfText").html(e.user.myself);
					$("#url").html(e.user.webUrl);
					$("#wu").val(e.user.webUrl);

				}
			})
		}
	});
</script>
</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<input type="hidden" name="userId" id="ud" value="${um.userId}" />



	<div class="marginLeftFifty">
		<div>
			<div class="twitterImageFrame">
				<img id="twitterImage" />
			</div>
			<div class="inlineBlock"></div>
			<div class="marginTopTwenty">
				<span id="twitterScreenName"></span><span id="twitterName"></span> <span><a
					href="/user/${twitter.screenName}"><fmt:message key="edit.end" />
				</a> </span>
			</div>

		</div>
		<hr class="hrOne" />
		<div class="marginTopTen">
			<div class="Introduction">
				<fmt:message key="self.introduction" />
			</div>
			<div id="loadMyself">
				<span id="myself"></span><span id="myselfEdit"><textarea
						id="myselfText"></textarea><br> <span id="myself-cancel"><fmt:message
							key="cancellation" /> </span>
					<button id="myself-change">
						<fmt:message key="button.change" />
					</button> </span>
			</div>
		</div>
		<div>
			<div class="webFont">Web:</div>
			<div id="web">

				<sapn id="url"></sapn>
				<span id="reg"><input type="text" name="webUrl" id="wu"
					value="">
					<button id="web-change">
						<fmt:message key="button.change" />
					</button> <span id="web-cancel"><fmt:message key="cancellation" />
				</span> </span>
			</div>
		</div>

		<div>
			<%-- 
		セーブデータ管理(未実装)
		<div style="overflow: auto; height: 400px;">
			<div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">ゲーム名</div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">削除</div>
			</div>
			
			<div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">○○○</div>
				<div
					style="display: inline-block; width: 240px; text-align: center;">
					<button>削除</button>
				</div>
			</div>
		
		</div>
		<hr />
		--%>
			<div class="management">
				<fmt:message key="user.management" />
			</div>
			<hr class="hrTwe">
			<div class="gameListFrame">

				<div id="hiddenresult">
					<div class="result">
						<c:forEach var="g" items="${gameList}" varStatus="loop">
							<%@ include file="/share/patternDistinction.jsp"%>
							<div id="ug${g.key.id}" class="games">
								<a href="/unitygames/upload/change/ug${g.key.id}"> <img
									src="${thUrl}" width="80px" height="80px" /><br>
									<div class="bounded gameName">${g.gameName}</div> </a>
							</div>
							<c:if test="${loop.count mod 3 == 0 }">
					</div>
					<div class="result">
						</c:if>
						</c:forEach>
					</div>
				</div>
				<div id="Pagination" align="center"></div>
				<br class="clear" />
				<div id="Searchresult"></div>
				<div align="center" class="marginTopThirty">
					<form method="get" action="#">
						<button type="button" id="btnPrev">Prev</button>

						<button type="button" id="btnNext">Next</button>
					</form>
				</div>


			</div>
			<div class="twitterFrame">


				<div id="twitter">
					<div class="tweetFrame">
						<div class="tweetFont">
							<fmt:message key="tweet" />
						</div>
						<div>
							<div class="inline">
								<textarea id="tweet"></textarea>
							</div>
							<div id="tweetButton">
								<input type="checkbox" id="noHashtag" value="noHash">
								<fmt:message key="dont.tag" />
								<button id="tweetUpdate">
									<fmt:message key="button.tweet" />
								</button>

							</div>
						</div>
					</div>


					<div class="clear">

						<c:forEach var="t" items="${tweet}">

							<script type="text/javascript">
								$(function() {
									/* 例1 */
									var url = "http://twitter.com/statuses/show/${t.tweetId}.json?callback={callback}";
									$
											.getJSONP(
													url,
													function(obj) {
														var s = "";

														s += "<div><div style='width: 250px;'>"
																+ obj.text
																+ "</div>";
														s += "<div>"
																+ jQuery
																		.timeago(obj.created_at)
																+ "</div></div>";
														$("#${t.tweetId}")
																.html(s);
													});
								});
							</script>

							<hr />
							<div id="${t.tweetId}" class="twitterIds"></div>

						</c:forEach>
						<hr />
					</div>

				</div>

			</div>
		</div>
		<%@ include file="/share/footer.jsp"%>
</body>
</html>

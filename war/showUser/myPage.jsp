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

<script type="text/javascript">
	$(function() {
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
											"<span id='edit'style='color:red;'>編集する(Click!!)</span>");
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
											"<span id='edit'style='color:red;font-size:10px;'>編集する(Click!!)</span>");
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
						html : "	ツイートされました！",
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


<input type="hidden" name="userId" id="ud" value="${um.userId}" />



<div style="margin-left: 50px;">
	<div>
		<div style="float: left; margin-left: 10px; margin-top: 10px;">
			<img src="${tp}" style="width: 50px; height: 50px;" />
		</div>
		<div style="display: inline-block;"></div>
		<div style="margin-top: 20px;">
			<span style="font-size: xx-large;">@${u.screenName }</span><span
				style="margin-left: 30px;">${u.name}</span>
		</div>

	</div>
	<hr style="margin-top: 50px; width: 900px;" />
	<div style="margin-top: 10px;">
		<div
			style="font-size: 20px; margin-top: auto; margin-bottom: auto; display: inline-block;">紹介：</div>
		<div id="loadMyself" style="display: inline-block; width: 450px;">
			<span id="myself"></span><span id="myselfEdit"><textarea
					style="width: 427px; height: 37px; font-size: 10px;"
					id="myselfText"></textarea><br> <span id="myself-cancel"
				style="float: right; margin-right: 10px; margin-top: 5px; cursor: pointer;">キャンセル</span>
				<button id="myself-change" style="float: right; margin-right: 0px;">変更</button>
			</span>
		</div>
	</div>
	<div>
		<div style="font-size: 20px; display: inline-block;">Web:</div>
		<div id="web"
			style="clear: both; margin-top: 20px; margin-bottom: 20px; display: inline-block;">

			<sapn id="url"></sapn>
			<span id="reg"><input type="text" name="webUrl" id="wu"
				value="">
				<button id="web-change">変更</button> <span id="web-cancel"
				style="font-size: 10px;">キャンセル</span> </span>
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
		<div style="font-size: 20px;margin-top: 50px;">ゲーム管理</div>
		<hr style="width: 500px;">
		<div style="display: inline-block; width: 500px;">
			
			<div id="hiddenresult" style="display: none;">
				<div class="result">
					<c:forEach var="g" items="${gameList}" varStatus="loop">
						<%@ include file="/share/patternDistinction.jsp"%>
						<div
							style="display: inline-block; text-align: center; z-index: 1; cursor: pointer;"
							id="ug${g.key.id}">
							<a href="/unitygames/change?id=${g.key.id}"> <img
								src="${thUrl}" width="80" height="80" /><br>
								<div class="bounded" style="width: 150px; text-align: center;">${g.gameName}</div>
							</a>
						</div>
						<c:if test="${loop.count mod 3 == 0 }">
				</div>
				<div class="result">
				</c:if>
				</c:forEach>
			</div>
		</div>
		<div id="Pagination"　style="" align="center"></div>
		<br style="clear: both;" />
		<div id="Searchresult"></div>
		<div align="center" style="margin-top: 30px;">
			<form method="get" action="#">
				<button type="button" id="btnPrev"
					style="width: 100px; height: 30px;">前</button>

				<button type="button" id="btnNext"
					style="margin-right: 50px; width: 100px; height: 30px;">次</button>
			</form>
		</div>


	</div>
<div style="float: right;margin-top: -150px;margin-right: 50px;">
	
	
	<div
		style="float: right;margin-right: 80px;"
		id="twitter">
		<div style="width: 250px;">
			<div style="font-size: 15px;">つぶやく(Twitterと連携しています)</div>
			<div>
				<div style="display: inline;"><textarea style="width: 250px; height: 30px;" id="tweet"></textarea></div>
				<div id="tweetButton"
					style="display: inline-block; height: 25px;">
					<input type="checkbox" id="noHashtag" value="noHash">#UnityGamesをつけない
					<button style="margin-left: 0px;" id="tweetUpdate">ついーと</button>

				</div>
			</div>
		</div>


		<div style="clear: both;">

			<c:forEach var="t" items="${tweet}">

				<script type="text/javascript">
					$(function() {
						/* 例1 */
						var url = "http://twitter.com/statuses/show/${t.tweetId}.json?callback={callback}";
						$.getJSONP(url, function(obj) {
							var s = "";

							s += "<div style='width: 250px;'>" + obj.text + "</div>";
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
	
</div>
</div>
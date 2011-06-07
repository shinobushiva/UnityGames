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
<div style="font-size: 15px;" id="twitter">
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

	<hr style="margin-top: 50px; width: 900px;clear: both;" />

</div>
<div style="">
	<div>
		<div
			style="font-size: 20px; margin-top: auto; margin-bottom: auto; display: inline-block;">紹介：</div>
		<div style="margin-top: 10px; display: inline-block; width: 450px;">${u.description}</div>
	</div>
	<div>
		<div style="display: inline-block; font-size: 20px; margin-top: 10px;">Web:</div>
		<div id="web" style="display: inline-block;">${webUrl}</div>
	</div>
	<div style="width: 500px;">
		<div style="font-size: 20px; margin-top: 50px;">ゲーム投稿履歴</div>
			<hr style="width: 500px;">
		<div id="hiddenresult" style="display: none;">
			<div class="result">
				<c:forEach var="g" items="${gameList}" varStatus="loop">
					<%@ include file="/share/patternDistinction.jsp"%>
					<div
						style="display: inline-block; text-align: center; z-index: 1; cursor: pointer;"
						id="ug${g.key.id}">
						<a href="/unitygames/game?id=${g.key.id}"><img
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



	</div>
<div style="float: right;margin-right: 200px;margin-top: -330px;">
<div style="font-size: 20px;">つぶやき</div>
		<c:forEach var="t" items="${tweet}">

			<script type="text/javascript">
				$(function() {
					var url = "http://twitter.com/statuses/show/${t.tweetId}.json?callback={callback}";
					$.getJSONP(url, function(obj) {
						var s = "";

						s += "<div　style='width: 250px;'>" + obj.text + "</div>";
						s += "<div>" + jQuery.timeago(obj.created_at)
								+ "</div>";
						$("#${t.tweetId}").html(s);
					});
				});
			</script>

			<hr />
			<div style="height: 50px; border: 1px;" id="${t.tweetId}"></div>

		</c:forEach>
		
		
	</div>
</div>
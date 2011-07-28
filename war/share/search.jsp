<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<div class="searchBox">

	<script type="text/javascript">
		$(function() {
			$("#tabss").tabs();
			$("#tabHeader").addClass("gameViewHead");
			$("#tak").click(function() {
				$("#k").val("");
			});
			$("#kik").click(function() {
				$("#t").val("");
			});
		$('.words').tagSuggest({  
		    tags: ${words}
		    	});
		$(".wordButton").click(function(){
			$("#setAction").attr("action","/search?type=word&q="+$(".words").val());
		})
	});
		function Check() {
			var flag = 0;
			var str1 = $("#k").val();
			var str2 = $("#t").val();
			if (str1.length == 0 && str2.length == 0) {
				flag = 1;
			}
			if (flag) {
				return false; // 送信を中止
			} else {
				return true; // 送信を実行
			}
		};
	</script>

	<div id="tabss">
		<form action="" method="post" onSubmit="return Check()" id="setAction">
				<div class="textBox">
					<input type="text" name="word" id="k" class="input words" autocomplete="off">
					<button class="searchButton black wordButton">
						<fmt:message key="button.search" />
					</button>
				</div>
		</form>
	</div>
</div>


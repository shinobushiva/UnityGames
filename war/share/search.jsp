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
		<form action="/search" method="post" onSubmit="return Check()">
			<ul id="tabHeader">
				<li><a href="#tab11" id="kik"><span><fmt:message
								key="keyword" /> </span> </a></li>
				<li><a href="#tab22" id="tak"><span><fmt:message
								key="tag" /> </span> </a></li>
			</ul>

			<div id="tab11" class="tabsStyle">
				<div class="textBox">
					<input type="text" name="word" id="k">
					<button class="searchButton black">
						<fmt:message key="button.search" />
					</button>
				</div>
				<br>
			</div>
			<div id="tab22" class="tabsStyle">
				<div class="textBox">
					<input type="text" name="tag" id="t">
					<button class="searchButton black">
						<fmt:message key="button.search"></fmt:message>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>


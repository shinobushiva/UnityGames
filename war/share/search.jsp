<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<div
	style="clear: both; width: 440px; padding-top: 20px; margin-left: auto; margin-right: auto;">

	<script type="text/javascript">
		$(function() {
			$("#tabss").tabs();

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


	<form action="/search" method="post" onSubmit="return Check()">
		<div id="tabss">
			<ul>
				<li><a href="#tab11" id="kik"><span><fmt:message
								key="keyword" /> </span> </a>
				</li>
				<li><a href="#tab22" id="tak"><span><fmt:message
								key="tag" /> </span> </a>
				</li>
			</ul>

			<div id="tab11">
				<div>
					<input type="text" name="word" style="font-size: 20; width: 300"
						id="k">
					<button style="background-color: transparent; border: 0;"
						class="searchButton black">
						<span style="color: white; font-size: 20;"><fmt:message
								key="button.search"></fmt:message> </span>
					</button>
				</div>
				<br>
			</div>
			<div id="tab22">
				<div>
					<input type="text" name="tag" style="font-size: 20; width: 300"
						id="t">
					<button style="background-color: transparent; border: 0;"
						class="searchButton black">
						<span style="color: white; font-size: 20;"><fmt:message
								key="button.search"></fmt:message> </span>
					</button>
				</div>
			</div>
		</div>
	</form>
</div>


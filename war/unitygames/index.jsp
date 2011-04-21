<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<title><fmt:message key="unitygames.top" /></title>
<script type="text/javascript">
	(function($) {
		var origContent = "";

		function loadContent(hash) {
			if (hash != "") {
				if (origContent == "") {
					origContent = $('#content').html();
				}
				$('#content').load(hash + ".html", function() {
					prettyPrint();
				});
			} else if (origContent != "") {
				$('#content').html(origContent);
			}
		}

		$(document).ready(function() {
			$.history.init(loadContent);
			$('#navigation a').click(function(e) {
				var url = $(this).attr('href');
				url = url.replace(/^.*#/, '');
				$.history.load(url);
				return false;
			});
		});
	})(jQuery);
	$(function() {

		$("#View").load("/view?view=default");
		$("#howto,#howto2").click(function() {
			$(function() {
				$("#PageLoad").load("/howto")
			})
		});
		$("#contact").click(function() {
			$(function() {
				$("#PageLoad").load("/contact")
			})
		});
		$("#selectView").change(function() {

			var data = $(this).serialize();

			$.ajax({
				type : "post",

				data : data,
				success : function() {

					$("#View").load("/view?" + data)
				}
			})
		})
	});

	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-22824102-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
</head>
<body>

	<%@ include file="/share/header.jsp"%>
	<%@ include file="/share/search.jsp"%>

	<div id="PageLoad">

		<select name="view" id="selectView">
			<option value="NewEntry" selected="selected">投稿日時が新しい順</option>
			<option value="OldEntry">投稿日時が古い順</option>
			<option value="MostAccess">アクセス数が多い順</option>
			<option value="LeastAccess">アクセス数が少ない順</option>
			<option value="MostComment">コメントが多い順</option>
			<option value="LeastComment">コメントが少ない順</option>
		</select>
		<div id="View"></div>
	</div>
	<%@ include file="/share/footer.jsp" %>

</body>
</html>

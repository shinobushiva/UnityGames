<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords"
	content="Unity,Unity3D,UnityGames,Game,tutorial,チュートリアル">
<meta name="description"
	content="Unity3Dゲーム投稿サイトです。ゲームを触って学ぶそんなサイト。チュートリアルも充実してます。日本のUnityゲーム投稿サイトならここ！">
<%@ include file="/share/css.jsp"%>
<%@ include file="/share/js.jsp"%>
<title>UnityGames</title>

</head>


<script type="text/javascript">
					$(function() {
						/* 例1 */
						var url = "http://twitter.com/statuses/show/66538237149200386.json?callback={callback}";
						  $.getJSONP(url, function(obj){
			                    var s="";
			                     
			                        //    s+="<li>obj.id = "+obj.id+"</li>";
			                            s+="<div>"+obj.text+"</div>";
			                            s+="<div>"+jQuery.timeago(obj.created_at)+"</div>";
			                        //    s+="<li>obj.source = "+obj.source+"</li>";
			                         //   s+="<li>obj.truncated = "+obj.truncated+"</li>";
			                          //  s+="<li>obj.in_reply_to_status_id = "+obj.in_reply_to_status_id+"</li>";
			                          //  s+="<li>obj.in_reply_to_user_id = "+obj.in_reply_to_user_id+"</li>";
			                          //  s+="<li>obj.favorited = "+obj.favorited+"</li>";
			                          //  s+="<li>obj.in_reply_to_screen_name = "+obj.in_reply_to_screen_name+"</li>";
			                    $("#66538237149200386").html(s);
			                });
					});
				</script>

<body>


	 <div style="height: 50px; border: 1px;" id="66538237149200386"></div>
</body>
</html>

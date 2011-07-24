function gameInfo(keyId,jscode){
	
  startInfo(keyId);
    $("#commentUp").click(function () {
        commentUp(keyId);
    });
    
    $("#tt").click(function () {
        $("#ttt").show();
    });
    
    $("#tagButton").click(function () {
        registTag(keyId);
    });
    
    $("#load,#reload").click(function () {
        loadClicked(keyId)
    });

    middleInfo(keyId,jscode);

    $("#edit,#cancel").click(function () {
        editToggle();
    });

    $("#save").click(function () {
        saveCode(keyId);
    });


}

function startInfo(keyId) {
    $(".success").hide();
    $("#fo").validate();
    $('#tabs').tabs();
    $('#contentTab').tabs();
    $("#reload").hide();
    $("#ttt").hide();
    GetUnity();
	updateTags(keyId);
}

function middleInfo(keyId,jscode){

	loadComments(keyId);
	toCode(eval(jscode));
	preCode();

}



function GetUnity() {
    if (typeof unityObject != "undefined") {
        return unityObject.getObjectById("unityPlayer");
    }
    return null;
}

function getTwitterInfo(twitterId) {

    $.getJSONP("http://api.twitter.com/1/users/show.json?id=" + twitterId + "&callback={callback}", function (e) {
        $("#twitterImage").attr("src", "" + e.profile_image_url);
        $("#twitterName").html("" + e.screen_name);
        $(".userPage").attr("href", "/user/" + e.screen_name);

    });

}

function commentUp(keyId) {
    $(".error").hide();
    $(".success").show();

    $(".success").fadeOut("slow");
    var a = $("#commentInput").serialize();
    var b = $("#gameKey").serialize();
    $.ajax({
        type: "post",
        url: "/commentUp",
        data: a + "&" + b,
        success: function () {
            loadComments(keyId);
        }
    });
    $("#commentInput").val("");
}


function registTag(keyId) {

    var a = $("#tagReg").serialize();
    var b = $("#gameKey").serialize();

    $.ajax({
        type: "post",
        url: "/tagRegist",
        data: a + "&" + b,
        success: function () {
            updateTags(keyId);
        }
    });

    $('#tagReg').val("");


}

function deleteTag(tagId,keyId) {

    var a = "tagId=" + tagId;
    var b = $("#gameKey").serialize();
    $.ajax({
        type: "post",
        url: "/tagDelete",
        data: a + "&" + b,
        success: function () {
            updateTags(keyId);
        }
    });
}

function toCode(obj) {

    var array = new Array();
    var count = 0;

    var result = "";
    for (var oIdx in obj) {
        var o = obj[oIdx];
        if (o[0] == 'text') {

            var match = o[1].match(".*(youtu(?:\.be|be\.com)/(?:.*v(?:/|=)|(?:.*/)?)([a-zA-Z0-9-_]+)).*");
            if (match) {

                o[1] = o[1].replace("" + match[0], "<a rel='video' href='http://www.youtube.com/watch?v=" + match[2] + "'></a>");
            }
            result += "<pre style='margin:10px;'>" + o[1] + "</pre>";
        } else {
            result += "<div class='" + o[0] + "' style='display:none; margin : 5px'>" + "<pre class='prettyprint'>" + o[1] + "</pre>" + "</div>";
            if ($.inArray(o[0], array)) array[count++] = o[0];
        }
    }

    var str = '';
    if (array.length > 0) {
        str += '<div ><fmt:message key="lang" /><select name="hoge">';
        for (var oIdx2 in array) {
            str += '<option value="' + array[oIdx2] + '">' + array[oIdx2] + '</option>';
        }
        str += '</select></div>';
    }

    $("#code").html(str + result).ready(function () {
        prettyPrint();
        $("#code").find("select").change(function () {
            $("select option").each(function () {
                $("." + $(this).text()).hide();
            });
            $("select option:selected").each(function () {
                $("." + $(this).text()).show();
            });
        });
    });

    $("." + array[0]).show();

}

function preCode() {

    $("pre").css("margin", "0");
    $("pre").css("margin-left", "10px");
    $("pre").css("margin-top", "10px");
    $("a[rel=video]").createVideo();
}


function editToggle(){

		$(".codeEdit").toggle();
			$(".edit").toggle();

}

function updateTags(keyId) {
    $.ajax({
        type: "post",
        url: "/ajax/tagUpload",
        data: "id=" + keyId,
        success: function (e) {
            udt(e, keyId);
        }
    });
}

function loadClicked(keyId) {
    $("#reload").show();

    $.ajax({
        type: "post",
        url: "/ajax/gameLoad",
        data: "id=" + keyId,
        success: function (e) {
            lkd(e);
        }
    });

}

function loadComments(keyId) {

    $.ajax({
        type: "post",
        url: "/ajax/commentLoad",
        data: "id=" + keyId,
        success: function (e) {
            lcs(e);
        }
    });
}

function saveCode(keyId) {

    var text = $("#codeEditArea").serialize();
    $.ajax({
        type: "post",
        url: "/ajax/codeSave",
        data: "id=" + keyId + "&" + text,
        success: function (e) {
            $(".codeEdit").toggle();
            $(".edit").toggle();
            toCode(eval(e.code));
            $("a[rel=video]").createVideo();
        }
    });
}

function changePass(keyId) {

    $("#change-"+keyId).css("position", "relative");
    $("#change-"+keyId).css("top", "-5px");
    $("#out-"+keyId).show();

}
function lcs(e) {

    var dateFormat = new DateFormat("yyyy/MM/dd HH:mm:ss");
    var html = "";
    var cms = e.comments;

    for (i in cms) {
        var c = cms[i];
        var com = c.comment;

        html += "<div style='margin:5px;font-size:15px;'>";
        html += "" + c.comment + " " + dateFormat.format(new Date(c.date)) + " " + c.twitterId;
        html += "</div>";
    }
    $('#commentLoad').html(html);
}

function udt(e,keyId) {
    var html = "";
    var html2 = "";

    var i;
    var t;

    var fixTags = e.fixTags;
    for (i in fixTags) {
        t = fixTags[i];

        html += '<a href="/search?type=tag&q=' + t.name + '"style="font-size: 2em; padding:3px;" >' + t.name + '</a>';
    }

   var tags = e.tags;
    for (i in tags) {
        t = tags[i];
        html += '<a href="/search?type=tag&q=' + t.name + '"style="font-size: 1em; padding:3px;" >' + t.name + '</a>';

        html2 += '<span style="padding:10px;">';
        html2 += '<span style="font-size: 20px;">' + t.name + '</span>';
        //	console.log(t.key);
        html2 += '<a onclick="deleteTag(\'' + t.key.id + '\',\'' + keyId + '\');">' + '<img src="/images/delete.png">' + '</a>'
        html2 += '</span>';
    }

    $("#tagUpload").html(html);
    $("#tagUpload2").html(html2);



}

function lkd(e) {
    var html = "";
    html += '<div class="content">';
    html += '<div id="unityPlayer">';
    html += '<div class="missing">';
    html += '<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">';
    html += '<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />';
    html += '</a>';
    html += '</div>';
    html += '</div>';
    html += '</div>';
    $("#loaded").html(html);
    eval(e.play);
}

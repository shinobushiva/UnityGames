function myPageInfo(userId, twitterId) {
    startInfo();
    initPagination();
	initButtons();
    twitterInfo(twitterId);
    loadMyself(userId);
    mySelfInfo(userId);
    urlInfo(userId);
}

function startInfo() {
    $("#tweet").charCount();
    $("#myselfEdit").hide();
    $("#tweetButton").hide();
    $("#reg").hide();
}

function pageselectCallback(page_index, jq) {
    var new_content = jQuery('#hiddenresult div.result:eq(' + page_index + ')').clone();
    $('#Searchresult').empty().append(new_content);
    return false;
}

function initPagination() {
    var num_entries = jQuery('#hiddenresult div.result').length;
    $("#Pagination").pagination(num_entries, {
        callback: pageselectCallback,
        items_per_page: 1
    });
}

function initButtons() {
    var pg = $('#Pagination');
    $('#btnPrev').click(function () {
        pg.trigger('prevPage');
    });
    $('#btnNext').click(function () {
        $('#Pagination').trigger('nextPage');
    });
    $('#btnSet').click(function () {
        pg.trigger('setPage', [1]);
    });
}

function twitterInfo(twitterId) {
    $.getJSONP("http://api.twitter.com/1/users/show.json?id=" + twitterId + "&callback={callback}", function (e) {

        $("#twitterImage").attr("src", "" + e.profile_image_url);
        $("#twitterScreenName").html("@" + e.screen_name);
        $("#twitterName").html("" + e.name);

    });

    $("#tweet").click(function () {
        $("#tweetButton").show();
        $("#tweet").css("height", "50px");
    });

    $("#tweetUpdate").click(function () {

        var a = $("#tweet").val();

        var b = $("#noHashtag").is(':checked');

        $.ajax({
            type: "post",
            url: "/action/tweet",
            data: "tweet=" + a + "&" + "check=" + b,
            success: function () {
                $.notifyBar({
                    html: "	<fmt:message key='tweeted'/>",
                    delay: 2000,
                    animationSpeed: "normal"
                });

            }

        })

        $("#tweet").val("");
    })
}

function mySelfInfo(userId) {

    $("#myself").mouseover(

    function (event) {
        $("#myself").css("background-color", "yellow");
        $("#myself").css("cursor", "pointer");
        $("#myself").append("<span id='edit'style='color:red;'><fmt:message key='edit'/>(Click!!)</span>");
    })
    $("#myself").mouseout(function (event) {
        $("#myself").css("background-color", "white");
        $("#edit").remove();
    })

    $("#myself").click(function () {
        $("#myself").hide();
        $("#myselfEdit").show();
    })

    $("#myself-cancel").click(function () {
        $("#myselfEdit").hide();
        $("#myself").show();

    })
    $("#myself-change").click(function () {
        var a = $("#myselfText").val();
        var b = $("#ud").serialize();
        $.ajax({
            type: "post",
            url: "/action/myselfRegist",
            data: "myselfText=" + a + "&" + b,
            success: function () {
                loadMyself(userId);
            }

        })
        $("#myselfEdit").toggle();
        $("#myself").toggle();
    });
}

function urlInfo(userId) {

    $("#url").mouseover(

    function (event) {
        $("#url").css("background-color", "yellow");
        $("#url").css("cursor", "pointer");
        $("#url").append("<span id='edit'style='color:red;font-size:10px;'><fmt:message key='edit'/>(Click!!)</span>");
    })
    $("#url").mouseout(function (event) {
        $("#url").css("background-color", "white");
        $("#edit").remove();
    })

    $("#url").click(function () {
        $("#url").hide();
        $("#reg").show();
    })
    $("#web-cancel").click(function () {
        $("#reg").hide();
        $("#url").show();
    })
    $("#web-change").click(function () {
        var a = $("#wu").val();
        var b = $("#ud").serialize();
        $.ajax({
            type: "post",
            url: "/action/webUrlRegist",
            data: "webUrl=" + a + "&" + b,
            success: function () {
                loadMyself(userId);
            }

        })
        $("#url").toggle();
        $("#reg").toggle();
    });
}

function loadMyself(userId) {

    $.ajax({
        type: "post",
        url: "/ajax/myselfLoad",
        data: "id=" + userId,
        success: function (e) {
            $("#myself").html(e.user.myself);
            $("#myselfText").html(e.user.myself);
            $("#url").html(e.user.webUrl);
            $("#wu").val(e.user.webUrl);

        }
    })
}

function tweet(tweetId){
	
var url = "http://twitter.com/statuses/show/" + tweetId + ".json?callback={callback}";
$.getJSONP(
url, function (obj) {
    var s = "";

    s += "<div><div style='width: 250px;'>" + obj.text + "</div>";
    s += "<div>" + jQuery.timeago(obj.created_at) + "</div></div>";
    $("#" + tweetId + "").html(s);
});

}
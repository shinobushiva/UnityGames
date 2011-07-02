function otherPageInfo(twitterId) {
    initPagination();
    initButtons();
    twitterinfo(twitterId);
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



function twitterinfo(twitterId) {
    $.getJSONP("http://api.twitter.com/1/users/show.json?id=" + twitterId + "&callback={callback}", function (e) {

        $("#twitterImage").attr("src", "" + e.profile_image_url);
        $("#twitterScreenName").html("@" + e.screen_name);
        $("#twitterName").html("" + e.name);

    });
}

function tweet(tweetId) {

    var url = "http://twitter.com/statuses/show/" + tweetId + ".json?callback={callback}";
    $.getJSONP(url, function (obj) {
        var s = "";

        s += "<div><div　style='width: 250px;'>" + obj.text + "</div>";
        s += "<div>" + jQuery.timeago(obj.created_at) + "</div></div>";
        $("#"+tweetId+"").html(s);
    });

}
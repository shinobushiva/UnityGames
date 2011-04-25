(function($){
    $.fn.tagCloud = function(options) {

        var defaults = {
            separator: ',',
            randomize: true
        };
  
        var options = $.extend(defaults, options);
  
        var randomize = function(){
            return (Math.round(Math.random())-0.5);
        };
  
        var trim = function(text){
            return text.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
        };

        return this.each(function() {
            var arr = $(this).text().split(options.separator);
            if (options.randomize) arr.sort(randomize);
            var words_arr = {};
            $(arr).each(function(i){
                word = trim(this);
                words_arr[word] = words_arr[word]? words_arr[word] + 1 : 1;
            });
            var html = '';
            $.each(words_arr, function(k, v) {
                v = (v > 9)? 9 : v;
                v = (v >= 3)? '2' + '.' + v : v;
                html += '<a href="#' + k + '" style="font-size: ' + v + 'em" title="' + k + '"><span>' + k + '</span></a>'
            });
            $(this).html(html);
        });
    };
})(jQuery);
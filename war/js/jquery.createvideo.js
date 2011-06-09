/*
jQuery CreateVideo Plugin v1.1

The MIT License

Copyright (c) 2011 ks-product.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

(function($){
	$.fn.createVideo = function(config) {
		var defaults = {
			width:315,
			height:192
		}
		
		var options = $.extend(defaults, config);
		
		return  this.each(function(){	
			var o = options;
			var videoId;
			var type;
			if(!(type = $(this).attr("rel"))) return;

			if(type == "video"){
				var url = $(this).attr("href");
				
				if(url.indexOf("http://www.youtube.com/") != -1 || url.indexOf("http://youtube.com/") != -1) type = "youtube";
				else if(url.indexOf("http://www.dailymotion.com") != -1 || url.indexOf("http://dailymotion.com") != -1) 	type = "dailymotion";
				else if(url.indexOf("http://www.vimeo.com/") != -1 || url.indexOf("http://vimeo.com/") != -1) 	type = "vimeo";
				else if(url.indexOf("http://www.nicovideo.jp/") != -1 || url.indexOf("http://nicovideo.jp/") != -1) 	type = "nicovideo";
			}
			
			switch(type){
				case "youtube":
					
						var str = String($(this).attr("href").match(/(\?|&)v=.*/g));
						str = str.substr(3);
						if(str.lastIndexOf("&") == -1){
							videoId = str
						}else{
							var endpos = str.lastIndexOf("&");
							videoId = str.substr(0,endpos);
						}
						$(this).after('<div class="videoContainer"><iframe width="'+o.width+'" height="'+o.height+'" src="http://www.youtube.com/embed/'+videoId+'?wmode=transparent" frameborder="0" allowfullscreen></iframe></div>');
						//$(this).after('<div class="videoContainer"><embed src="http://www.youtube.com/v/'+videoId+'?fs=1&amp;hl=ja_JP" type="application/x-shockwave-flash" width="'+o.width+'" height="'+o.height+'" allowscriptaccess="always" allowfullscreen="true" wmode="opaque"></embed></div>');
						$(this).remove();		
					
				break;
				
				case "dailymotion":
						var str = String($(this).attr("href").match(/\/video\/.*/g));
						str = str.substr(7);
						if(str.indexOf("_") == -1){
							videoId = str
						}else{
							var endpos = str.indexOf("_");
							videoId = str.substr(0,endpos);
						}
						$(this).after('<div class="videoContainer"><iframe frameborder="0" width="'+o.width+'" height="'+o.height+'" src="http://www.dailymotion.com/embed/video/'+videoId+'?width='+o.width+'&theme=none&wmode=opaque"></iframe></div>');
						$(this).remove();		
				break;
				
				case "vimeo":
						var str = String($(this).attr("href"));
						videoId = str = str.substr(str.lastIndexOf("/")+1)
						
						$(this).after('<div class="videoContainer"><iframe src="http://player.vimeo.com/video/'+videoId+'?portrait=0" width="'+o.width+'" height="'+o.height+'" frameborder="0"></iframe></div>');
						$(this).remove();		
				break;
				
				case "nicovideo":

						/*
							iFrameの動的読み込みに関して、一部下記を参考にしています。
							http://jsdo.it/GeckoTang/ttTu
						*/
				
						var str = String($(this).attr("href"));
						videoId = str = str.substr(str.lastIndexOf("/")+1).split("?")[0];
						
						var $div = $('<div class="videoContainer"></div>');
						$(this).after($div);
						$(this).remove();
						
						var $ifm = $div.append('<iframe frameborder="0" width="'+o.width+'" height="'+o.height+'" />').find('> :last-child');
						var $idoc = $ifm.contents();
						$idoc[0].open();
						$idoc[0].write('<html><body style="margin:0; padding:0; background-color:#000;"><div style="margin:0; padding:0;"><script type="text/javascript" src="http://ext.nicovideo.jp/thumb_watch/'+videoId+'?w='+o.width+'&h='+o.height+'&tr=1"></script></div></body></html>');
						//$idoc[0].close(); //IE,Operaがストリームを閉じると動画が表示されないバグがある為省略
						 $ifm.bind('load', function(){
							$(window).unload = function(){};
							window.location = url;
						 });
						
				break;
				default:
				break;	
			}
		});
	};
})(jQuery);
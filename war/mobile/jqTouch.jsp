<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>mobile Index</title>
<%@ include file="/mobile/share/jqTouch.jsp"%>
<script type="text/javascript" charset="utf-8"> 
var jQT = new $.jQTouch({
    addGlossToIcon: false,
   
    statusBar: 'black',
    preloadImages: [
        'css/img/back_button.png',
        'css/img/back_button_clicked.png',
        'css/img/button_clicked.png',
        'css/img/grayButton.png',
        'css/img/whiteButton.png',
        'css/img/loading.gif'
        ]
});
</script> 
<script type="text/javascript"> 
 
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-280255-7']);
  _gaq.push(['_trackPageview']);
 
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
 
</script> 
</head> 
<body> 
<!-- home --> 
<div id="home"> 
  <div class="toolbar"><h1>サンプルホーム</h1></div> 
  <div class="info">このページは?：jQTouchの class や div などの挙動について確認するために作りました。</div> 
  
  <div class="info"><h2>class - 画面の上につくボタン</h2></div> 
  <ul> 
    <li class="arrow"><a href="#button">buttonサンプル</a></li> 
    <li class="arrow"><a href="#add">addサンプル</a></li> 
    <li class="arrow"><a href="#back">backサンプル</a></li> 
    <li class="arrow"><a href="#cancel">cancelサンプル</a></li> 
  </ul> 
  <div class="info"><h2>class - メインに表示するボタン</h2></div> 
  <ul> 
    <li class="arrow"><a href="#whiteButton">whiteButtonサンプル</a></li> 
    <li class="arrow"><a href="#grayButton">grayButtonサンプル</a></li> 
  </ul> 
  <div class="info"><h2>class - アニメーション</h2></div> 
  <ul> 
    <li class="arrow"><a href="#anim-back">backサンプル</a></li> 
    <li class="arrow"><a class="cube" href="#anim-cube">cubeサンプル</a></li> 
    <li class="arrow"><a class="fade" href="#anim-fade">fadeサンプル</a></li> 
    <li class="arrow"><a class="dissolve" href="#anim-dissolve">dissolveサンプル</a></li> 
    <li class="arrow"><a class="flip" href="#anim-flip">flipサンプル</a></li> 
    <li class="arrow"><a class="pop" href="#anim-pop">popサンプル</a></li> 
    <li class="arrow"><a href="#anim-slide">slideサンプル</a></li> 
    <li class="arrow"><a class="slideup" href="#anim-slideup">slideupサンプル</a></li> 
    <li class="arrow"><a class="swap" href="#anim-swap">swapサンプル</a></li> 
  </ul> 
  <div class="info"><h2>class - ul li</h2></div> 
  <ul> 
    <li class="arrow"><a href="#ulli-blueButton">blueButtonサンプル</a></li> 
    <li class="arrow"><a href="#ulli-whiteButton">whiteButtonサンプル</a></li> 
    <li class="arrow"><a href="#ulli-grayButton">grayButtonサンプル</a></li> 
    <li class="arrow"><a href="#arrow">arrowサンプル</a></li> 
    <li class="arrow"><a href="#forward">forwardサンプル</a></li> 
    <li class="arrow"><a href="#individual">individualサンプル</a></li> 
    <li class="arrow"><a href="#ulli-edgetoedge">edgetoedgeサンプル</a></li> 
    <li class="arrow"><a href="#sep">sepサンプル</a></li> 
  </ul> 
  <div class="info"><h2>リスト時 特殊タグ - ul li xxx</h2></div> 
  <ul> 
    <li class="arrow"><a href="#small">smallサンプル</a></li> 
    <li class="arrow"><a href="#em">emサンプル</a></li> 
  </ul> 
  <div class="info"><h2>class - ul のテーマ種類</h2></div> 
  <ul> 
    <li class="arrow"><a href="#plastic">plasticサンプル</a></li> 
    <li class="arrow"><a href="#metal">metalサンプル</a></li> 
    <li class="arrow"><a href="#edgetoedge">edgetoedgeサンプル</a></li> 
  </ul> 
  <div class="info"><h2>class - div</h2></div> 
  <ul> 
    <li class="arrow"><a href="#info">infoサンプル</a></li> 
    <li class="arrow"><a href="#toolbar">toolbarサンプル</a></li> 
  </ul> 
<hr/> 
  <ul> 
    <li class="forward"><a href="http://www.jqtouch.com/preview/demos/main/">jQTouchの本家サンプル</a></li> 
  </ul> 
</div> 
<!-- /home --> 
 
<!-- button --> 
<div id="button"> 
  <div class="toolbar"><h1>buttonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  <a class="button goback" href="#home">ホームヘ</a> 
  
  <div class="info">class="button" の解説</div> 
  <ul> 
    <li>形状：角丸四角</li> 
    <li>表示場所：画面右上</li> 
    <li>サンプル：画面右上参照。</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="button goback" href="#home">ホームヘ</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し<br /> 
    （全角5文字以内が妥当?）</li> 
    <li>注意点：画面遷移する場合は、アニメーション指定をする必要があるかもしれません。<br /> 
    (iPadで戻らない現象を確認しています)</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /button --> 
 
<!-- add --> 
<div id="add"> 
  <div class="toolbar"><h1>addサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  <a class="add" href="#" onclick="alert('+が押されました')">+</a> 
  
  <div class="info">class="add" の解説</div> 
  <ul> 
    <li>形状：角丸四角</li> 
    <li>表示場所：画面右上</li> 
    <li>サンプル：画面右上参照。</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="add" href="#" onclick="alert('+が押されました')">+</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し<br /> 
    （「+」と書くのが通例）</li> 
    <li>補足：これ、そのものに何か機能があるわけではないので、JavaScriptでonclickイベントで処理するなどで利用する。<br /> 
    もちろん、 + クリックで画面遷移してもいい。</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /add --> 
 
<!-- back --> 
<div id="back"> 
  <div class="toolbar"><h1>backサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="back" の解説</div> 
  <ul> 
    <li>形状：左矢印四角</li> 
    <li>表示場所：画面左上</li> 
    <li>サンプル：画面左上参照。</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="back" href="#">Back</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：全角3文字以内<br /> 
    （それ以上与えると…で切られる）</li> 
    <li>注意点：hrefに何を入れててもhistory.backのような動きをする。</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /back --> 
 
<!-- cancel --> 
<div id="cancel"> 
  <div class="toolbar"><h1>cancelサンプル</h1></div> 
  <a class="cancel" href="#">Cancel</a> 
  
  <div class="info">class="cancel" の解説</div> 
  <ul> 
    <li>形状：角丸四角</li> 
    <li>表示場所：画面左上</li> 
    <li>サンプル：画面左上参照。</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="cancel" href="#">Cancel</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し<br /> 
    （全角5文字以内が妥当?）</li> 
    <li>注意点：挙動はbackと変わらない。形状と文字数制限の違いだけっぽい。</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /cancel --> 
 
<!-- whiteButton --> 
<div id="whiteButton"> 
  <div class="toolbar"><h1>whiteButtonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="whiteButton" の解説</div> 
  <ul> 
    <li>形状：角丸四角-白</li> 
    <li>サンプル：</li> 
  </ul> 
  <a class="whiteButton" href="#home">home</a> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="whiteButton" href="#home">home</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し長ければ2段になる<br /> 
    （全角10文字以内が妥当?）</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /whiteButton --> 
 
<!-- grayButton --> 
<div id="grayButton"> 
  <div class="toolbar"><h1>grayButtonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="grayButton" の解説</div> 
  <ul> 
    <li>形状：角丸四角-黒</li> 
    <li>サンプル：</li> 
  </ul> 
  <a class="grayButton" href="#home">home</a> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="grayButton" href="#home">home</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し長ければ2段になる<br /> 
    （全角10文字以内が妥当?）</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /grayButton --> 
 
<!-- anim-back --> 
<div id="anim-back"> 
  <div class="toolbar"><h1>backサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">backSelector の解説</div> 
  <ul> 
    <li>動き：1つ前の画面に来た時と同じアニメーションで戻る</li> 
    <li>サンプル：このページの一番下の「ホームへ」ボタンを実行した時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="button goback" href="#home">ホームヘ</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.back, .cancel, .goback」の3つが割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-back --> 
 
<!-- anim-cube --> 
<div id="anim-cube"> 
  <div class="toolbar"><h1>cubeサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">cubeSelector の解説</div> 
  <ul> 
    <li>動き：4角形の2面に今と次の画面を描画してあり、サイコロのように回転するアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="cube" href="#anim-cube">cubeサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.cube」が割り当てられている</li> 
    <li>補足：ディスプレイが小さい場合は、回転というよりslideと同じように見えるかもしれない…</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-cube --> 
 
<!-- anim-fade --> 
<div id="anim-fade"> 
  <div class="toolbar"><h1>fadeサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">fadeSelector の解説</div> 
  <ul> 
    <li>動き：次の画面がフェードインするアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="fade" href="#anim-fade">fadeサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.fade」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-fade --> 
 
<!-- anim-dissolve --> 
<div id="anim-dissolve"> 
  <div class="toolbar"><h1>dissolveサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">dissolveSelector の解説</div> 
  <ul> 
    <li>動き：今の画面が消えつつ次の画面が出てくるクロスフェードアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="dissolve" href="#anim-dissolve">dissolveサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.dissolve」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-dissolve --> 
 
<!-- anim-flip --> 
<div id="anim-flip"> 
  <div class="toolbar"><h1>flipサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">flipSelector の解説</div> 
  <ul> 
    <li>動き：トランプのカードを裏返すようなアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="flip" href="#anim-flip">flipサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.flip」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-flip --> 
 
<!-- anim-pop --> 
<div id="anim-pop"> 
  <div class="toolbar"><h1>popサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">popSelector の解説</div> 
  <ul> 
    <li>動き：新しい画面が奥からフェードインしてくるようなアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="pop" href="#anim-pop">popサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.pop」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-pop --> 
 
<!-- anim-slide --> 
<div id="anim-slide"> 
  <div class="toolbar"><h1>slideサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">slideSelector の解説</div> 
  <ul> 
    <li>動き：新しい画面が右から左にスライドするアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li>
<a href="#anim-slide">slideサンプル</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>「body > * > ul li a」とあるので、ul li a の場合のデフォルトの挙動</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-slide --> 
 
<!-- anim-slideup --> 
<div id="anim-slideup"> 
  <div class="toolbar"><h1>slideupサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">slideupSelector の解説</div> 
  <ul> 
    <li>動き：新しい画面が下から上にスライドするアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="slideup" href="#anim-slideup">slideupサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.slideup」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-slideup --> 
 
<!-- anim-swap --> 
<div id="anim-swap"> 
  <div class="toolbar"><h1>swapサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">swapSelector の解説</div> 
  <ul> 
    <li>動き：2枚のトランプのカードをシャッフルする感じで入れ替えるようなアニメーション</li> 
    <li>サンプル：このページへ遷移してきた時の挙動</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<a class="swap" href="#anim-swap">swapサンプル</a>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>指定できるclassは、デフォルトで「.swap」が割り当てられている</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /anim-swap --> 
 
<!-- ulli-blueButton --> 
<div id="ulli-blueButton"> 
  <div class="toolbar"><h1>blueButtonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="blueButton" の解説</div> 
  <ul> 
    <li>形状/変化：角丸四角-青 のボタンになる</li> 
    <li>サンプル：↓下記参照</li> 
    <li><a class="blueButton" href="#home">home</a></li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li>
<a class="blueButton" href="#home">home</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /ulli-blueButton --> 
 
<!-- ulli-whiteButton --> 
<div id="ulli-whiteButton"> 
  <div class="toolbar"><h1>whiteButtonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="whiteButton" の解説</div> 
  <ul> 
    <li>形状/変化：角丸四角-白 のボタンになる</li> 
    <li>サンプル：↓下記参照</li> 
    <li><a class="whiteButton" href="#home">home</a></li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li>
<a class="whiteButton" href="#home">home</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /ulli-whiteButton --> 
 
<!-- ulli-grayButton --> 
<div id="ulli-grayButton"> 
  <div class="toolbar"><h1>grayButtonサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="grayButton" の解説</div> 
  <ul> 
    <li>形状/変化：角丸四角-黒 のボタンになる</li> 
    <li>サンプル：↓下記参照</li> 
    <li><a class="grayButton" href="#home">home</a></li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li>
<a class="grayButton" href="#home">home</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /ulli-grayButton --> 
 
<!-- arrow --> 
<div id="arrow"> 
  <div class="toolbar"><h1>arrowサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="arrow" の解説</div> 
  <ul> 
    <li>形状/変化：リストの右に「＞」と表示される</li> 
    <li>サンプル：↓下記参照</li> 
    <li class="arrow"><a href="#home">Go home</a></li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li class="arrow">
<a href="#home">Go home</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /arrow --> 
 
<!-- forward --> 
<div id="forward"> 
  <div class="toolbar"><h1>forwardサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="forward" の解説</div> 
  <ul> 
    <li>形状/変化：リストの右に「(＞)」と表示される</li> 
    <li>サンプル：↓下記参照</li> 
    <li class="forward"><a href="#home">Go home</a></li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li class="forward">
<a href="#home">Go home</a>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /forward --> 
 
<!-- individual --> 
<div id="individual"> 
  <div class="toolbar"><h1>individualサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="individual" の解説</div> 
  <ul> 
    <li>形状/変化：リストを左右に分割して表示する</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="individual"> 
    <li>hoge1</li> 
    <li>fuga1</li> 
    <li>hoge2</li> 
    <li>fuga2</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="individual">
<li>hoge1</li>
<li>fuga1</li>
<li>hoge2</li>
<li>fuga2</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>補足：「hoge1」「fuga1」のように 2つになる。3つ以上に分けたい場合は、theme.cssをカスタマイズする</li> 
    <li>注意点：デフォルトのCSSは、なぜか2行目以降のインデントがズレる…</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /individual --> 
 
<!-- ulli-edgetoedge --> 
<div id="ulli-edgetoedge"> 
  <div class="toolbar"><h1>edgetoedgeサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="edgetoedge" の解説</div> 
  <ul> 
    <li>形状/変化：角丸ではない四角いリストで表示する</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="edgetoedge"> 
    <li>hoge</li> 
    <li>fuga</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="edgetoedge">
<li>hoge</li>
<li>fuga</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /ulli-edgetoedge --> 
 
<!-- sep --> 
<div id="sep"> 
  <div class="toolbar"><h1>sepサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="sep" の解説</div> 
  <ul> 
    <li>形状/変化：余白を削って細いリストで表示する</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="edgetoedge"> 
    <li class="sep">f</li> 
    <li>fuga</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="edgetoedge">
<li class="sep">f</li>
<li>fuga</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点：「ul.edgetoedge」でのみ使用できる。</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /sep --> 
 
<!-- small --> 
<div id="small"> 
  <div class="toolbar"><h1>smallサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">small タグの解説</div> 
  <ul> 
    <li>用途：リストの右に「(xxx)」と表示される</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul> 
    <li><a href="#home">home</a><small class="counter">1</small></li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul>
<li>
<a href="#home">home</a>
<small class="counter">1</small>
</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点： aタグ部分をただのテキストにすると、small部分の表示位置がズレる</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /small --> 
 
<!-- em --> 
<div id="em"> 
  <div class="toolbar"><h1>emサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">em タグの解説</div> 
  <ul> 
    <li>用途：文字の太さが通常(細め)で表示される</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="edgetoedge"> 
    <li><em>emなhoge</em></li> 
    <li>emじゃないhoge</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="edgetoedge">
<li><em>emなhoge</em></li>
<li>emじゃないhoge</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点：「ul.edgetoedge」でのみ使用できる。</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /em --> 
 
<!-- plastic --> 
<div id="plastic"> 
  <div class="toolbar"><h1>plasticサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">plastic の解説</div> 
  <ul> 
    <li>用途：背景がリスト毎に濃淡で表示される</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="plastic"> 
    <li class="arrow">hoge1</li> 
    <li class="forward">fuga1</li> 
    <li><a href="#">hoge2</a><small class="counter">20</small></li> 
    <li>fuga2</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="plastic">
<li class="arrow">hoge1</li>
<li class="forward">fuga1</li>
<li><a href="#">hoge2</a><small class="counter">20</small></li>
<li>fuga2</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点：CSS3の「ul.plastic li:nth-child(odd)」が動いてないのか表示されないかも? </li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /plastic --> 
 
<!-- metal --> 
<div id="metal"> 
  <div class="toolbar"><h1>metalサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">metal の解説</div> 
  <ul> 
    <li>用途：リストの縦が長めで表示される</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="metal"> 
    <li class="arrow">hoge1</li> 
    <li class="forward">fuga1</li> 
    <li><a href="#">hoge2</a><small class="counter">20</small></li> 
    <li>fuga2</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="metal">
<li class="arrow">hoge1</li>
<li class="forward">fuga1</li>
<li><a href="#">hoge2</a><small class="counter">20</small></li>
<li>fuga2</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点：metalでは class="forward" は利用できない</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /metal --> 
 
<!-- edgetoedge --> 
<div id="edgetoedge"> 
  <div class="toolbar"><h1>edgetoedgeサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">edgetoedge の解説</div> 
  <ul> 
    <li>用途：角丸でない四角いリストで表示される</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <ul class="edgetoedge"> 
    <li class="arrow">hoge1</li> 
    <li class="forward">fuga1</li> 
    <li><a href="#">hoge2</a><small class="counter">20</small></li> 
    <li>fuga2</li> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<ul class="edgetoedge">
<li class="arrow">hoge1</li>
<li class="forward">fuga1</li>
<li><a href="#">hoge2</a><small class="counter">20</small></li>
<li>fuga2</li>
</ul>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>注意点：edgetoedgeでは class="forward" は利用できない</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /edgetoedge --> 
 
<!-- info --> 
<div id="info"> 
  <div class="toolbar"><h1>infoサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="info" の解説</div> 
  <ul> 
    <li>形状/変化：該当要素に背景が付き読みやすくなる</li> 
    <li>サンプル：↓下記参照</li> 
  </ul> 
  <div class="info">hoge</div> 
  </ul> 
  <ul> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<div class="info">
hoge
</div>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：特に無し</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /info --> 
 
<!-- toolbar --> 
<div id="toolbar"> 
  <div class="toolbar"><h1>toolbarサンプル</h1></div> 
  <a class="back" href="#">Back</a> 
  
  <div class="info">class="toolbar" の解説</div> 
  <ul> 
    <li>形状/変化：画面上部にページタイトルのように表示する</li> 
    <li>サンプル：画面上部参照</li> 
    <li>コード：
    <form> 
    <ul class="form"> 
      <li><textarea> 
<div class="toolbar">
<h1>
toolbarサンプル
</h1>
</div>
      </textarea></li> 
    </ul> 
    </form></li> 
    <li>文字数制限：全角6文字以内<br /> 
    （それ以上与えると…で切られる）</li> 
  </ul> 
  <a class="whiteButton goback" href="#home">ホームへ戻る</a> 
</div> 
<!-- /toolbar --> 
</body>
</html>

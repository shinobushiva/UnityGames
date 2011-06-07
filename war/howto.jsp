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
<title>How to</title>

</head>
<body>
	<%@ include file="/share/header.jsp"%>
	<h1>
		<fmt:message key="what.unitygames1" />
	</h1>
	<fmt:message key="what.unitygames2" />
	<br>
	<h1>
		<fmt:message key="what.unity1" />
	</h1>
	<fmt:message key="what.unity2" />

	<h1>使い方</h1>
	メニュー画面からゲームの登録を選択します
	<br>
	<img alt="" src="/images/howto/menu.png">
	<pre>
ゲーム名(必須)、サムネイル画像、ゲームデータ、固定タグ、パスワード(必須)、各説明文を記入します。
</pre>
	<img alt="" src="/images/howto/upload.png">
	<pre>
サムネイル画像について…サムネイル画像の登録には2種類用意しています。
①	画像をアップロード登録する
②	画像URLを登録する
の2種類です。

ゲームデータについて…ゲームデータの登録には3種類用意しています。
①	ゲームデータをアップロード登録する(10MBまで。unity3d形式のみ。例→sample.unity3d)
②	ゲームデータURLを登録する(unity3d形式のみ)
③	ゲームが出来るURLを登録する(外部サイト)
の3種類です。
本サイトでゲームデータをアップロードするときの容量は10MBまでとなっております。
それ以上の容量のゲームデータを登録する場合は、オンラインストレージサービスを利用しデータURLを保存するようにしてください。

固定タグについて…本サイトではタグ機能を実装しています
ゲーム登録者のみ変更が可能な固定タグ、利用者関係なく変更可能なタグと2種類に分かれています。
固定タグで複数登録をする場合「,」で区切り3つまで登録できます。
 </pre>
	<h1 id="code">ソースコードについて</h1>
<pre>
記入例 
	説明文などが書けますコードは下のコードタグを使います
	
	&lt;code&gt;
	>|js|
	
	function ...
	
	||<
	
	>|cs|
	
	using ...
	
	||<
	
	&lt;/code&gt;
	コードタグは複数回使えます
	&lt;code&gt;
	
	　	...
	
	&lt;/code&gt;
</pre>

	<pre>
	
	
	
	
チュートリアルについて…
本サイトでは初心者でもUnity3Dに触れてもらえるようにチュートリアルを用意しております。簡単なチュートリアルから本格的なものまで随時更新していく予定です。
もちろん貴方自身がチュートリアルを作成することも出来ます。
</pre>
	<h1>ゲーム選択画面</h1>
	<img alt="" src="/images/howto/game.png">
	<pre>
ゲーム名、投稿日、アクセス数、コメント数、サムネイル画像、固定タグ、ゲーム説明、操作説明、変更削除パスワード入力が表示されます
ゲーム名、またはサムネイル画像をクリックするとゲーム画面へ移動します。
</pre>
	<h1>ゲーム画面</h1>
	<img alt="" src="/images/howto/gamePage.png">
	<pre>
ゲーム名、投稿日、最終更新日、各説明文、コメント、タグ登録/削除が表示・行うことが出来ます。
真ん中の再生ボタンを押すとロードされゲームを開始することが出来ます。
</pre>
	<%@ include file="/share/footer.jsp"%>

</body>
</html>

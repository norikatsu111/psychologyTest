<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<title>１カラムレイアウト</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/base_top.css">
	</head>

	<body>

		<h1 class="header"></h1>

		<!----------------メニュー------------------------------------------------------------------------------------>

		<nav class=”current”>
			<ul>
				<li>
				<a href="index.jsp">Home</a></li>
				<li><a href=”#”>News</a></li>
				<li><a href=”#”>About</a></li>
				<li><a href=”#”>Access</a></li>
				<li><a href=”#”>Blog</a></li>
			</ul>
		</nav>

		<!-----------------画像--------------------------------------------------------------------------------------->

		<div class="sample">

			<img src="img/wallpaper-jungle-photo-05.jpg" />

			<a href="/psychologyTest/LoginServlet"><i class="test"></i>ログインメニュー</a>

		</div>

		<!----------グローバルナビゲーション-------------------------------------------------------------------------->

		<main>

			<div class="grovalNavigation">

	       		<p>グローバルナビゲーション</p>

		    </div>

		    <div class="content">

		        <p>コンテンツ</p>

		    </div>

		    <div class="localNavigation">

		        <p>ローカルナビゲーション</p>

		    </div>

		</main>

		<!------------------フッター---------------------------------------------------------------------------------->

		<div class="footer"></div>

	</body>
</html>
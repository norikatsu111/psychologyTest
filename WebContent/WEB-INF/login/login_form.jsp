<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<title>タイトル</title>
		<link rel="stylesheet"
    href="${pageContext.request.contextPath}/topCss.css">
	</head>
	<body>
	<div class="body"></div>
        <div class="grad"></div>
        <div class="header">
            <div>Site<span>Random</span></div>
        </div>
        <br>
        <form action="/psychologyTest/LoginServlet" method="post" onsubmit="return funcConfirmPassword()">
	        <div class="login">
	                <input type="text" placeholder="username" name="login" maxlength="20"><br>
	                <input type="password" placeholder="password" name="password" maxlength="20"><br><br>
	                <button name="state" value="login_process">ログイン</button>
	                <!--<input type="button" value="login_process">--><br><br>
	                <button name="state" value="new">新規登録はこちら</button>
	                <button name="state" value="new">パスワード変更</button>
	                <a href="index.jsp">戻る</a>
	        </div>
        </form>
	</body>
	<!--<script type="text/javascript" src="validation_check.js"></script> -->
</html>



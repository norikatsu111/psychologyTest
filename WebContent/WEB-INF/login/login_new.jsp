<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<title>タイトル</title>
		<link rel="stylesheet" href="topCss.css" />

		<style>
			.header{
			    position: absolute;
			    top: calc(50% - 35px);
			    left: calc(50% - 255px);
			    z-index: 2;
			}

			.header div span{
			    color: #5379fa !important;
			}
				.login{
			    position: absolute;
			    top: 2%;
			    left:35% ;
			    height: 150px;
			    width: 350px;
			    padding: 10px;
			    z-index: 2;
			}
		</style>

	</head>

	<body>

		<div class="body"></div>

        <div class="grad"></div>

        <div class="header"></div>

        <form  action="/psychologyTest/LoginServlet" method="post" onsubmit="return funcConfirmPassword()">

			<div class="login">

				<h2>新規登録フォーム</h2>

				<span>氏 名:</span><br>
				<input type="text" placeholder="username" name="user_name" maxlength="20"><br><br>
				<span>パスワード：</span><br>
				<input type="password" placeholder="password" name="password1" maxlength="20"><br><br>
				<span>パスワード(確認用):</span>
				<input type="password" placeholder="password" name="password2" maxlength="20"><br><br>
				<button name="state" value="add">新規登録</button>　　<a href="index.jsp"><span>戻る</span></a>

			 </div>

		</form>

	</body>

	<script type="text/javascript" src="validation_check.js"></script>

</html>
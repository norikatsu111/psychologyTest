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
			    top: 15%;
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
        <br>

			<div class="login">

				<h2>新規登録フォーム</h2>

				旧パスワード：<br>
	            <input type="password" placeholder="password" name="old_password" maxlength="20"><br><br>

	            新パスワード：<br>
	            <input type="password" placeholder="password" name="new_password1" maxlength="20"><br><br>

	            新パスワード(確認用)：<br>
	            <input type="password" placeholder="password" name="new_password2" maxlength="20"><br><br>

				　　<input type="submit" name="登録">　　<a href="index.jsp">戻る</a>
			 </div>

	</body>
	<script type="text/javascript" src="validation_check.js"></script>
</html>
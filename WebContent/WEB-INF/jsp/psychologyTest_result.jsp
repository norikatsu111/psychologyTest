<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.UserBean, user.UserListBean, java.util.List"%>

<% UserBean userEdit = (UserBean) session.getAttribute("userEdit"); %>

<!DOCTYPE html>
<html>
	<head>

		<title>心理テスト</title>

		<link rel = "stylesheet" href = "base_top.css" type = "text/css" />

	</head>

	<body>
		<!--------------------フッター-------------------------------------------------->
		<h1 class="header"></h1>

		<!---------------------メニュー-------------------------------------------------->

		<form action="" method="post">

			<dl>
				<dt><h3><%= userEdit.getUserName() %>さんあなたは <%= userEdit.getComment()%> です</h3></dt>

				<img src="imgQ/<%= userEdit.getImg() %>"><br><br>
				<a href="index.jsp">戻る</a>
			</dl>

		<!------------------フッター---------------------------------->
			<div class="footer"></div>

		</form>
	</body>
</html>
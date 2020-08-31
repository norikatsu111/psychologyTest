<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>心理テスト</title>
		<link rel = "stylesheet" href = "base_top.css" type = "text/css" />
	</head>

	<body>

		<h1 class="header"></h1>

		<!---------------------メニュー----------------------------------------------------------------------------->

		<form action ="/psychologyTest/PsychoPassTestServlet" method="post">

			<dl>
				<dt><h3>Ｑ海に浮かぶ無人島で一冊だけ本を持って行けるとしたらどんな本を選びますか？</h3></dt>

				<img src="imgQ/無人島.jpg"><br><br>

					<dd><input type="radio" name="question1" value="ch" checked >Ａ：島での生活を指南するサバイバルガイド</dd>
			        <dd><input type="radio" name="question1" value="ku">Ｂ：ベストセラーのエッセイ　　　　　　　</dd>
					<dd><input type="radio" name="question1" value="tk">Ｃ：非日常を描いたファンタジー小説　　　</dd>
					<dd><input type="radio" name="question1" value="sk">Ｄ：友だちに勧められたマンガ　　　　　　</dd>
				<br><br>

				<dt><h3>Q記憶にない友人に声をかけられて、あなたがする行動はどれ？</h3></dt>
				<img src="imgQ/声かけられる.jpg"><br><br>
					<dd><input type="radio" name="question2" value="st"checked>A：忘れてごめんと謝って、とりあえず遊ぶ！　　　　　　</dd>
			        <dd><input type="radio" name="question2" value="yk">B：自分をだまそうとしてる？と思ってやんわり距離を置く</dd>
					<dd><input type="radio" name="question2" value="mg">C：まじで思い出せないので完全スルー　　　　　　　　　</dd>
					<dd><input type="radio" name="question2" value="sk">D：記憶を思いだせるかもしれないのでとりあえず話を聞く</dd>
				<br><br>

				<dt><h3>Q喉が乾いたあなたは友達から飲み物を貰いました。飲み物の色は何色ですか？</h3></dt>
				<img src="imgQ/drink.jpg"><br><br>
					<dd><input type="radio" name="psychopass1" value=num_0 checked>A:赤　　　　　　　　</dd>
			        <dd><input type="radio" name="psychopass1" value=num_0 >B:青　　　　　　　　</dd>
					<dd><input type="radio" name="psychopass1" value=num_0 >C:黄色　　　　　　　</dd>
					<dd><input type="radio" name="psychopass1" value=num_20 >D:無色　　　　　　　</dd>
				<br><br>

				<dt><h3>Q動物園で熊が脱走しました。あなたは動物園のどこに逃げ隠れますか？</h3></dt>
				<dd><img src="imgQ/bear.jpg"><br><br></dd>
					<dd><input type="radio" name="psychopass2" value=num_0 checked>A: 宿舎の鍵がかけられる部屋</dd>
			        <dd><input type="radio" name="psychopass2" value=num_20 >B：熊がみえる屋上　　　　　</dd>
					<dd><input type="radio" name="psychopass2" value=num_10 >C：少し離れたトイレの影　　</dd>
					<dd><input type="radio" name="psychopass2" value=num_0 >D：動物園の外　　　　　　　</dd>
				<br><br>

				<dt><h3>Q子供にぬいぐるみを買ってあげるとしたらどれを選びますか？</h3></dt>
				<dd><img src="imgQ/child.jpg"><br><br></dd>
					<dd><input type="radio" name="psychopass3" value=num_0 checked>A: 熊のぬいぐるみ　　　　　　　</dd>
			        <dd><input type="radio" name="psychopass3" value=num_0 >B:猫のぬいぐるみ　　　　　　　</dd>
					<dd><input type="radio" name="psychopass3" value=num_0 >C:ゴリラの形をしたサンドバッグ</dd>
					<dd><input type="radio" name="psychopass3" value=num_10 >D:全部(熊・猫・ゴリラ)　　　　</dd>
				<br><br>

				<dt><h3>Q仕事が終わり家に帰ると、飼い猫がいなくなっていました。さて、いなくなった原因は次のうちどれだと思いますか？</h3></dt>
				<dd><img src="imgQ/cat.jpg"><br><br></dd>
					<dd><input type="radio" name="psychopass4" value=num_0 checked>A:窓から脱出した　　</dd>
			        <dd><input type="radio" name="psychopass4" value=num_10 >B:泥棒が盗んだ　　　</dd>
					<dd><input type="radio" name="psychopass4" value=num_20 >C:実は隠れている　　</dd>
					<dd><input type="radio" name="psychopass4" value=num_0 >D:猫なんていなかった</dd>
				<br><br>

				<dt><h3>Q今日一日仕事や勉強でヘトヘトのあなた。睡眠時に一番気を配るのは？</h3></dt>
				<dd><img src="imgQ/勉強.jpg"><br><br></dd>
					<dd><input type="radio" name="question3" value="tk"checked>A:目覚まし時計をセット </dd>
			        <dd><input type="radio" name="question3" value="ch">B:服　　　　　　　　　 </dd>
					<dd><input type="radio" name="question3" value="mg">C:枕　　　　　　　　　 </dd>
					<dd><input type="radio" name="question3" value="yk">D:香り　　　　　　　　 </dd>
				<br><br>

				<dt><h3>Qあなたは学校の教師で、生徒たちの中から学級委員長を選ばなければなりません。どんな子を選びますか？</h3></dt>
				<dd><img src="imgQ/school.jpg"><br><br></dd>
					<dd><input type="radio" name="psychopass5" value=num_0 checked>A:陽気な生徒　　　　　　　　　　</dd>
			        <dd><input type="radio" name="psychopass5" value=num_30 >B:特徴がない生徒　　　　　　　　</dd>
					<dd><input type="radio" name="psychopass5" value=num_0 >C:頭のよい生徒　　　　　　　　　</dd>
					<dd><input type="radio" name="psychopass5" value=num_0 >D:リーダーシップのありそうな生徒</dd>
				<br><br>

				<dt><h3>Qあなたはある大会で優勝しました。トロフィーを家のどこにおきますか？</h3></dt>
				<dd><img src="imgQ/trophy.jpg"><br><br></dd>
					<dd><input type="radio" name="psychopass6" value= num_20 checked>A:テレビ台の上</dd>
			        <dd><input type="radio" name="psychopass6" value= num_0 >B:勉強机の横　</dd>
					<dd><input type="radio" name="psychopass6" value= num_10 >C:寝室の棚　　</dd>
					<dd><input type="radio" name="psychopass6" value= num_0 >D:トイレ　　　 </dd>

				<dt><h3>Qあなたのとっても大事な人が山頂に向かって頑張って登っています。その間にいくつの障害物があると思いますか？</h3></dt>
				<dd><img src="imgQ/登山.jpg"><br><br></dd>
					<dd><input type="radio" name="question4" value="tk"checked>A:足をすくわれそうな川が１つ </dd>
			        <dd><input type="radio" name="question4" value="ch">B:足をすくわれそうな川が２つ </dd>
					<dd><input type="radio" name="question4" value="ku">C:小川が１つ　　　　　　　　</dd>
					<dd><input type="radio" name="question4" value="st">D:小川が２つ　　　　　　　　 </dd>
			</dl>
			<br><br>

			<div class="send">

				<a href="index.jsp">戻る</a><button name ="state" value="test">診断</button>

			</div>
			<br><br>
		</form>
		<!-------------------------------フッター---------------------------------->
			<div class="footer"></div>


	</body>
</html>
# psychologyTest
## 概要<br>
心理テスト　SQL DAO サーブレット　jspの復習

## 環境<br>
#「eclipse」javaEE
サーバー　 Apache Tomcat v8.0
DB H2database

## 機能<br>


    /**
    * HTTPリクエスト内の各情報を処理、ユーザーBeanを生成し格納する
    * @param request HTTPのリクエスト
	  * @param psychopass サイコパス
	  * @param comment プロフィールコメント
	  * @param img プロフィール画像
    * @return ユーザーBean
    */
	public UserBean createUserBeanFromRequest(HttpServletRequest request,LoginBean loginUser) {
		LogUtil.println(this.getClass().getSimpleName() + "#createUserBeanFromRequest");

		ArrayList<String> questionArray = new ArrayList<String>();

		questionArray.add(request.getParameter("question1"));
		questionArray.add(request.getParameter("question2"));
		questionArray.add(request.getParameter("question3"));
		questionArray.add(request.getParameter("question4"));

		 //値をカウント
		int tkCount =0;
		int chCount =0;
		int skCount =0;
		int kuCount =0;
		int mgCount =0;
		int ykCount =0;
		int stCount =0;

		String img;   //Beanに入れべき画像の情報
		String comment;   //Beanに入れべきプロフィールコメントの情報

		for(String strquestion : questionArray) {
			System.out.println("strquestion : " + strquestion);

			switch(strquestion) {
			    case "tk":
			      tkCount++;
			      break;
			    case "ch":
			      chCount++;
			       break;
			    case "sk":
			      skCount++;
			      break;
			    case "ku":
			      kuCount++;
			      break;
			    case "mg":
			      mgCount++;
			      break;
			    case "yk":
			      ykCount++;
			      break;
			    case "st":
			      stCount++;
			      break;
			    }
		}

		//降順でソート
		Integer[] countSort = {tkCount, chCount, skCount, kuCount, mgCount, ykCount, stCount};

		Arrays.sort(countSort, Collections.reverseOrder());

		System.out.println("Arrays.asList(countSort) : " + Arrays.asList(countSort));//確認

		if(countSort[0] == tkCount) {
			img = "tk.jpg";
			comment = "短気なゴリラ";
		}else if(countSort[0] == chCount) {
			img = "kk.jpg";
			comment = "童心を忘れないゴリラ";
		}else if(countSort[0] == skCount) {
			img = "sk.jpg";
			comment = "神経質なゴリラ";
		}else if(countSort[0] == kuCount) {
			img = "ks.jpg";
			comment = "食いしん坊なゴリラ";
		}else if(countSort[0] == mgCount) {
			img = "mg.jpg";
			comment = "メスゴリラ";
		}else if(countSort[0] == ykCount) {
			img = "yk.jpg";
			comment = "陽気なゴリラ";
		}else{
			img = "st.jpg";
			comment = "悟したゴリラ";
		}

		ArrayList<String> psychopassArray =new ArrayList<String>();

		psychopassArray.add(request.getParameter("psychopass1"));
		psychopassArray.add(request.getParameter("psychopass2"));
		psychopassArray.add(request.getParameter("psychopass3"));
		psychopassArray.add(request.getParameter("psychopass4"));
		psychopassArray.add(request.getParameter("psychopass5"));
		psychopassArray.add(request.getParameter("psychopass6"));

		int psychopass = 0; //Beanに入れる情報

		for(String strPsychopass : psychopassArray) {
			System.out.println("strPsychopass : "+ strPsychopass);
			if(strPsychopass.equals("num_0")){
				strPsychopass = "0";
			}else if(strPsychopass.equals("num_10")){
				strPsychopass = "10";
			}else if(strPsychopass.equals("num_20")){
				strPsychopass = "20";
			}else if(strPsychopass.equals("num_30")){
				strPsychopass = "30";
			}else {
				strPsychopass = "0";
		  }

		int intPsychopass = Integer.parseInt(strPsychopass);
		psychopass += intPsychopass;

		}

		System.out.println("UserLogic　:　点数 :"+ psychopass);
		System.out.println("UserLogic　:　画像 :"+ img);

		UserBean userEdit = (UserBean)request.getSession().getAttribute("userEdit");
		if (userEdit == null) {
		userEdit = new UserBean();

		}
		userEdit.setUserName(loginUser.getLogin());
		userEdit.setComment(comment);
		userEdit.setImg(img);
		userEdit.setPsychopass(psychopass);
		return userEdit;
	 }

###
###

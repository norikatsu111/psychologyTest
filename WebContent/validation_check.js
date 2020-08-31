 function funcConfirmPassword() {

 		if (document.username.value == "") {
 			alert("氏名が入力されていません。");
 			return false;
 		}

 		if (!document.password.value.match(/^[\x20-\x7E]+$/)) {
 			alert("パスワードは半角英数字と記号で入力してください");
 			return false;
 		}
 }

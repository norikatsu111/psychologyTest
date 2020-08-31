package user;

import java.io.Serializable;

public class UserBean implements Serializable {

	private int id;  //id

	private String userName;//ユーザー名

	private String img;//プロフィール画像

	private String comment;//プロフィールコメント

	private String text;//つぶやきの内容

	private int psychopass;//犯罪係数

	public UserBean() {}
	public UserBean(String userName) {this.userName = userName;}

	public UserBean(String userName,String text) {

		this.userName = userName;
		this.text = text;

	}
	public UserBean(String userName,String text,int id) {

		this.userName = userName;
		this.text = text;
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPsychopass() {
		return psychopass;
	}

	public void setPsychopass(int psychopass) {

		if(psychopass > 100) {
			this.psychopass = 100;
		}
		this.psychopass = psychopass;
	}
}

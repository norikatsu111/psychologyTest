package login;

/**
 * ログインユーザーBean
 */
public class LoginBean {
    /** ID */
    private int intId;
    /** ログイン名 */
    private String strLogin;
    /** ユーザー名 */
    private String strName;
    /** パスワード */
    private String pwd;
	/** 権限 */
    private int intLvl;

    public LoginBean() {}

    public LoginBean(int id, int lvl) {

    	this.intId = id;
    	this.intLvl = lvl;
    }

    public LoginBean(String strLogin,String strName) {

    	this.strLogin = strLogin;
    	this.strName = strName;
    }

    /**
     * IDのセッター
     * @param id ID
     */
    public void setId(int id) {
        this.intId = id;
    }

    /**
     * ログイン名のセッター
     * @param login ログイン名
     */
    public void setLogin(String login) {
        this.strLogin = login;
    }

    /**
     * ユーザー名のセッター
     * @param name ユーザー名
     */
    public void setName(String name) {
        this.strName = name;
    }

    /**
     * 権限のセッター
     * @param lvl 権限
     */
    public void setLvl(int lvl) {
        this.intLvl = lvl;
    }

    /**
     * IDのゲッター
     * @return ID
     */
    public int getId() {
        return this.intId;
    }

    /**
     * ログイン名のゲッター
     * @return ログイン名
     */
    public String getLogin() {
        return this.strLogin;
    }

    /**
     * ユーザー名のゲッター
     * @return ユーザー名
     */
    public String getName() {
        return this.strName;
    }

    /**
     * 権限のゲッター
     * @return 権限
     */
    public int getLvl() {
        return this.intLvl;
    }
    /**
     * パスワードのゲッター
     * @return パスワード
     */
    public String getPwd() {
		return pwd;
	}
    /**
     * パスワードのセッター
     * @return パスワード
     */

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}

package login;

import static constants.MessageConstants.*;

import javax.servlet.http.HttpServletRequest;

import dao.LoginDao;
import util.LogUtil;
import util.StringUtil;

/**
 * ユーザーのロジック
 */
public class LoginLogic {

    /**
     * 指定のログイン名とログインパスワードでログイン可能かチェックする
     * @param login ログイン名
     * @param pwd ログインパスワード
     * @return ユーザーBean(ログイン不可の場合、null)
     */
    public LoginBean login(String login, String pwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#login");
        LoginDao loginDao = new LoginDao();
        LoginBean loginUser = loginDao.getUser(login, pwd);
        System.out.println(" LoginLogic + loginUser :"+loginUser);
        return loginUser;
    }

//    /**
//     * 指定ユーザーのパスワードを変更する
//     * @param oldPwd 既存ログインパスワード
//     * @param intId ユーザーテーブルのID
//     * @param newPwd 新ログインパスワード
//     * @return エラーメッセージ(処理成功時、null)
//     */
//    public String passwordChange(String oldPwd, int intId, String newPwd) {
//        LogUtil.println(this.getClass().getSimpleName() + "#passwordChange");
//
//        String errMessage = null;
//
//        if (oldPwd.equals(newPwd)) {
//            errMessage = MESSAGE_SAME_OLD_PASSWORD_AND_NEW_PASSWORD;
//        } else {
//            UserDao userDao = new UserDao();
//            errMessage = userDao.updateUser(newPwd, intId, oldPwd);
//        }
//
//        return errMessage;
//    }

    /**
     * 指定ユーザーを追加する
     * @param pwd ログインパスワード
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String add(String password, LoginBean loginUser) {
        LogUtil.println(this.getClass().getSimpleName() + "#add");

        String errMessage = null;
        LoginDao loginDao = new LoginDao();
        if (loginDao.addUser(password, loginUser) != null) {
            errMessage = MESSAGE_CAN_NOT_ADD;
        }

        return errMessage;
    }

    /**
     * IDで指定されたユーザーを取得する
     * @param id ユーザーテーブルのID
     * @return ユーザーBean
     */
    public LoginBean load(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        LoginDao loginDao = new LoginDao();
        LoginBean loginUser = loginDao.load(id);

        return loginUser;
    }

    /**
     * ユーザーBeanの情報でユーザーを更新する
     * @param pwd 新ログインパスワード
     * @param loginUser ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(String strPassword, LoginBean userEdit) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        if (userEdit == null) {
            return MESSAGE_NO_EXIST_CORRESPOND_DATA;
        }

        String errMessage = null;
        LoginDao loginDao = new LoginDao();
        errMessage = loginDao.update(strPassword, userEdit);
        if (errMessage != null) {
            errMessage = MESSAGE_CAN_NOT_UPDATE;
        }
        return errMessage;
    }


    /**
     * HTTPリクエスト内の情報からユーザーBeanを生成する
     * @param request HTTPのリクエスト
     * @return ユーザーBean
     */
    public LoginBean createLoginBeanFromRequest(HttpServletRequest request) {
        LogUtil.println(this.getClass().getSimpleName() + "#createUserBeanFromRequest");

        LoginBean userEdit = (LoginBean)request.getSession().getAttribute("userEdit");
        if (userEdit == null) {
            userEdit = new LoginBean();

        }
        userEdit.setLogin(StringUtil.exchangeESCEncoding(request.getParameter("login")));
        userEdit.setName(StringUtil.exchangeESCEncoding(request.getParameter("user_name")));

        return userEdit;
    }
}

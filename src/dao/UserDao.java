package dao;

import static constants.MessageConstants.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.LoginBean;
import user.UserBean;
import user.UserListBean;
import util.LogUtil;

/**
 * ユーザー管理DAO(オートコミット)
 */
public class UserDao extends BaseDao {

    /** DBMS_CRYPTOを使用した暗号化･復号化時のパスワード */
    private static final String SECRET = "SECPWD";

    /**
     * ログイン名とログインパスワードを指定してユーザーテーブルからユーザー情報を取得する
     * @param login ログイン名
     * @param pwd ログインパスワード
     * @return ユーザーの情報を格納したユーザーBean
     */
    public LoginBean getUser(String strLogin, String strPassword) {
        LogUtil.println(this.getClass().getSimpleName() + "#getUser");

        PreparedStatement pstmt = null;
        LoginBean loginUser = null;

        String strSql = "SELECT ID,LOGINNAME,PWD,LVL FROM LOGIN_USER WHERE LOGINNAME=? AND PWD=?";
        try {
            open();

            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, strLogin);
            pstmt.setString(2, strPassword);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loginUser = new LoginBean();
                loginUser.setId(rs.getInt("id"));
                loginUser.setName(rs.getString("strLogin"));

            }
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        return loginUser;
    }


    /**
     * ユーザーテーブルへ指定ユーザーの情報を追加する
     * @param userEdit ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String addUser(String password, UserBean userEdit) {
        LogUtil.println(this.getClass().getSimpleName() + "#addUser");
        System.out.println( "userEdit.getUserName() : "+ userEdit.getUserName());

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "INSERT INTO USER_NAME (USERNAME,IMG,COMMENT,TEXT,PSYCHOPASS) VALUES(?,?,?,?,?)";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
//            pstmt.setString(1, userEdit.getUserName());
//            pstmt.setString(2, userEdit.getText());
//            pstmt.setString(3, userEdit.getImg());
//            pstmt.setString(4, userEdit.getComment());
//            pstmt.setInt(5,userEdit.getPsychopass());

            pstmt.setString(1, userEdit.getUserName());
            pstmt.setString(2, userEdit.getImg());
            pstmt.setString(3, userEdit.getComment());
            pstmt.setString(4, userEdit.getText());
            pstmt.setInt(5, userEdit.getPsychopass());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }

    /**
     * IDを指定してユーザーテーブルからユーザー情報を取得する
     * @param id ユーザーテーブルのID
     * @return ユーザーBean
     */
    public LoginBean load(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        PreparedStatement pstmt = null;
        String strSql = "SELECT * FROM LOGIN_USER WHERE id =?";
        LoginBean user = null;

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new LoginBean();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setLvl(rs.getInt("lvl"));
                rs.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        return user;
    }

    /**
     * ユーザーテーブルの指定ユーザーの情報を更新する
     * @param pwd  ログインパスワード(変更しない場合、null)
     * @param user ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(String pwd, LoginBean user) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        String errMessage = null;
        PreparedStatement pstmt = null;

        try {
            open();
            String strSql = null;
            if (pwd == null) {
                strSql = "UPDATE LOGIN_USER set login=?,name=?,lvl=? WHERE id=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getName());
                pstmt.setInt(3, user.getLvl());
                pstmt.setInt(4, user.getId());
            } else {
                strSql = "UPDATE LOGIN_USER set login=?,name=?,lvl=?,pwd=PWD_ENCRYPT(?,'" + SECRET + "')"
                        + " WHERE id=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getName());
                pstmt.setInt(3, user.getLvl());
                pstmt.setString(4, pwd);
                pstmt.setInt(5, user.getId());
            }

            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_NO_EXIST_CORRESPOND_DATA;
            }
        } catch (SQLException | ClassNotFoundException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * IDを指定してユーザーテーブルからユーザー情報を削除する
     * @param id ユーザーテーブルのID
     * @return エラーメッセージ(処理成功時、null)
     */
    public String delete(int id) {
        LogUtil.println(this.getClass().getSimpleName() + "#delete");

        String errMessage = null;
        PreparedStatement pstmt = null;
        String strSql = "UPDATE LOGIN_USER set login=null,lvl=-1,pwd=null WHERE id=?";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);

            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_CAN_NOT_DELETE;
            }
        } catch (SQLException | ClassNotFoundException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }
        return errMessage;
    }

    /**
     * ユーザーテーブルから全ユーザーの情報を取得する
     * @return ユーザーBeanのリスト
     */
    public UserListBean loadAll() {
        LogUtil.println(this.getClass().getSimpleName() + "#loadAll");

        PreparedStatement pstmt = null;
        ArrayList<UserBean> alUser = new ArrayList<UserBean>();
//        String strSql = "SELECT * FROM USER_NAME WHERE lvl ORDER BY id ASC";
        String strSql = "SELECT * FROM USER_NAME";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            ResultSet rs = pstmt.executeQuery();
            alUser.clear();
            while (rs.next()) {
                UserBean loginUser = new UserBean();
                loginUser.setId(rs.getInt("id"));
//                loginUser.setLogin(rs.getString("login"));
//                loginUser.setLvl(rs.getInt("lvl"));
//                alUser.add(loginUser);
                loginUser.setUserName(rs.getString("userName"));
                loginUser.setText(rs.getString("text"));
                loginUser.setImg(rs.getString("img"));
                loginUser.setComment(rs.getString("comment"));
                loginUser.setPsychopass(rs.getInt("psychopass"));
            }
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
                pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        UserListBean userListBean = new UserListBean();
        userListBean.setAlUser(alUser);
        userListBean.setIntCounter(0);

        return userListBean;
    }
}

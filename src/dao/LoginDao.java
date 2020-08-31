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
public class LoginDao extends BaseDao {

    /**
     * ログイン名とログインパスワードを指定してユーザーテーブルからユーザー情報を取得する
     * @param login ログイン名
     * @param pwd ログインパスワード
     * @return ユーザーの情報を格納したユーザーBean
     */
    public LoginBean getUser(String login, String pwd) {
        LogUtil.println(this.getClass().getSimpleName() + "#getUser");

        PreparedStatement pstmt = null;
        LoginBean loginUser = null;
        System.out.println("LoginDao　：" + login + ":" + pwd);
        String strSql = "SELECT ID,LOGINNAME,PWD,LVL FROM LOGIN_USER WHERE LOGINNAME=? AND PWD=?";
        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setString(1, login);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loginUser = new LoginBean();
                loginUser.setId(rs.getInt("id"));
                loginUser.setLogin(rs.getString("loginName"));
                loginUser.setPwd(rs.getString("pwd"));
                loginUser.setLvl(rs.getInt("lvl"));
                rs.close();
            }

        } catch (SQLException | ClassNotFoundException e) {
            LogUtil.printStackTrace(e);
        } finally {
            try {
            	pstmt.close();
                close();
            } catch (SQLException e) {
                LogUtil.printStackTrace(e);
            }
        }

        return loginUser;
    }


    /**
     * ユーザーテーブルへ指定ユーザーの情報を追加する
     * @param password ログインパスワード
     * @param loginUser ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String addUser(String password, LoginBean loginUser) {
        LogUtil.println(this.getClass().getSimpleName() + "#addUser");
        System.out.println("DAO"+loginUser.getName()+ password);
        String errMessage = null;
        PreparedStatement pstmt = null;
//        String strSql = "INSERT INTO LOGIN_USER(ID,LOGINNAME,PWD,LVL) VALUES(?,?,?,?)";
        String strSql = "INSERT INTO LOGIN_USER(LOGINNAME,PWD,LVL) VALUES(?,?,?)";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);

            System.out.println("DAO "+ loginUser.getId()+":"+ loginUser.getLvl());
//            pstmt.setInt(1, loginUser.getId());
//            pstmt.setString(2, loginUser.getName());
//            pstmt.setString(3, password);
//            pstmt.setInt(4, loginUser.getLvl());

            pstmt.setString(1, loginUser.getName());
            pstmt.setString(2, password);
            pstmt.setInt(3, loginUser.getLvl());
            int intResult = pstmt.executeUpdate();
            if (intResult != 1) {
                errMessage = MESSAGE_INCORRECT_OLD_PASSWORD;
            }
            return errMessage;
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
        LoginBean loginUser = null;

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loginUser = new LoginBean();
                loginUser.setId(rs.getInt("id"));
                loginUser.setName(rs.getString("userName"));

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

        return loginUser;
    }

    /**
     * ユーザーテーブルの指定ユーザーの情報を更新する
     * @param pwd  ログインパスワード(変更しない場合、null)
     * @param loginUser ユーザーBean
     * @return エラーメッセージ(処理成功時、null)
     */
    public String update(String strPassword, LoginBean userEdit) {
        LogUtil.println(this.getClass().getSimpleName() + "#update");

        String errMessage = null;
        PreparedStatement pstmt = null;

        try {
            open();
            String strSql = null;
            if (strPassword == null) {
                strSql = "UPDATE LOGIN_USER set LOGINNAME=? WHERE ID=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, userEdit.getName());

                pstmt.setInt(2, userEdit.getId());
            } else {
                strSql = "UPDATE LOGIN_USER set LOGINNAME=?, PWD =? WHERE ID=?";
                pstmt = conn.prepareStatement(strSql);
                pstmt.setString(1, userEdit.getName());
                pstmt.setString(2, strPassword);
                pstmt.setInt(3, userEdit.getId());
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
     * ユーザーテーブルから全ユーザーの情報を取得する
     * @return ユーザーBeanのリスト
     */
    public UserListBean loadAll() {
        LogUtil.println(this.getClass().getSimpleName() + "#loadAll");

        PreparedStatement pstmt = null;
        ArrayList<UserBean> alUser = new ArrayList<UserBean>();
        String strSql = "SELECT * FROM LOGIN_USER WHERE ORDER BY id ASC";

        try {
            open();
            pstmt = conn.prepareStatement(strSql);
            ResultSet rs = pstmt.executeQuery();
            alUser.clear();
            while (rs.next()) {
            	UserBean loginUser = new UserBean();
                loginUser.setId(rs.getInt("id"));
                loginUser.setUserName(rs.getString("userName"));
                alUser.add(loginUser);
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

package controller;

import static constants.MessageConstants.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginBean;
import login.LoginLogic;
import user.UserBean;
import user.UserListBean;
import user.UserListLogic;
import util.LogUtil;
import util.StringUtil;

/**
 * ログイン管理のサーブレット
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doGet *****");

            getServletContext().getRequestDispatcher("/WEB-INF/login/login_form.jsp").forward(request, response);

    }
    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * ログイン管理固有の処理を行う
     * </pre>
     * @see BaseServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doPost *****");

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String state = request.getParameter("state");
        LogUtil.println("state: " + state);

        if (state == null) {
            procSessionError(request, response, session);
            return;
        }

        LoginBean loginUser = (LoginBean) session.getAttribute("loginUser");

        switch (state) {
        case "login_process":
            procLoginProcess(request, response, session, loginUser);
            break;
        case "new":
            procNew(request, response, session, loginUser);
            break;
        case "add":
            procAdd(request, response, session, loginUser);
            break;
        case "top":
            procTop(request, response, session, loginUser);
            break;
        case "logout":
            procLogout(request, response, session, loginUser);
            break;
        }
    }

    /**
     * ユーザ情報の全レコードをDBから取得し、各画面に遷移する
     * リクエスト内のログイン情報と、DBのユーザ情報を比較し、メニュー画面(ログイン成功)、または、ログイン失敗画面に遷移する
     * <pre>
     * state属性＝"login_process"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param loginUser      ログイン中のユーザー情報(通常はログイン前のためnullとなる)
     */
    private void procLoginProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            LoginBean loginUser) throws ServletException,
            IOException {

        if (loginUser != null) {
            session.setAttribute("errMessage", MESSAGE_ALREADY_LOGGED_IN);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);
            return;
        }

        String strLogin = StringUtil.exchangeESCEncoding(request.getParameter("login"));
        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password"));

        System.out.println("リクエストパラメータ"+ strLogin + strPassword);

        LoginLogic loginLogic = new LoginLogic();
        LoginBean loginBean = loginLogic.login(strLogin, strPassword);
        System.out.println("loginBeanレベル :"+loginBean.getLvl());//デバッグ

        if (loginBean != null && loginBean.getLvl() == 0) {
            session.setAttribute("loginUser",loginBean);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/psychologyTest.jsp").forward(request, response);
            return;
        }else if(loginBean.getLvl() == 1) {
        	session.setAttribute("loginUser",loginBean);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);//TODO　掲示板画面
            return;
        }else if(loginBean.getLvl() == 2) {
			UserListLogic userListLogic = new UserListLogic();
			UserListBean userListBean = null;
			userListBean = userListLogic.load();
			System.out.println("LoginServlet　："+userListBean );
			request.setAttribute("userList", userListBean);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
			return;
        } else {
            session.setAttribute("errMessage", MESSAGE_NO_EXIST_CORRESPOND_DATA);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);
        }
    }

    /**
     * 新規登録画面に遷移する
     * <pre>
     * state属性＝"new"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procNew(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser)
            throws ServletException, IOException {
    	System.out.println("サーブレットnew");
        getServletContext().getRequestDispatcher("/WEB-INF/login/login_new.jsp").forward(request, response);
    }

    /**
     * 追加対象のユーザー情報をリクエストから取得し、DB追加処理後、新規登録完了(成功時)画面、または、新規登録未完了(失敗時)画面に遷移する
     * <pre>
     * state属性＝"add"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser)
            throws ServletException, IOException, UnsupportedEncodingException {

    	String strLogin = StringUtil.exchangeESCEncoding(request.getParameter("login"));
        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password1"));
        LoginLogic loginLogic = new LoginLogic();
        System.out.println("サーブレット"+ request );
        LoginBean userEdit = loginLogic.createLoginBeanFromRequest(request);

        String errMessage = null;
        errMessage = loginLogic.add(strPassword, userEdit);
        System.out.println("ログインサーブレット、 errMessage：" +  errMessage);
        //loginをUserBeanのnameに格納
        UserBean userBean = new UserBean();
        userBean.setUserName(strLogin);

        session.removeAttribute("userEdit");

        if (errMessage == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/psychologyTest.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login_error.jsp").forward(request, response);
        }
    }



    /**
     * ログイン中のユーザ情報をDBから取得し直し、メニュー画面に遷移する
     * <pre>
     * state属性＝"top"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param user      ログイン中のユーザー情報
     */
    private void procTop(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser)
            throws ServletException, IOException {
        if (loginUser == null || loginUser.getName() == null) {

            procSessionError(request, response, session);
            return;
        }

        LoginLogic loginLogic = new LoginLogic();
        loginUser = loginLogic.load(loginUser.getId());
        session.setAttribute("loginUser", loginUser);

        getServletContext().getRequestDispatcher("/WEB-INF/login/menu.jsp").forward(request, response);
    }

    /**
     * セッションを破棄し、ログアウト画面に遷移する
     * <pre>
     * state属性＝"logout"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param loginUser      ログイン中のユーザー情報
     */
    private void procLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser)
            throws ServletException,
            IOException {
        if ((loginUser == null) || (loginUser.getName() == null)) {
            procSessionError(request, response, session);
            return;
        }

        session.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/login/login_out.jsp").forward(request, response);
    }
}

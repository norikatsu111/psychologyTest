package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginBean;
import user.UserBean;
import user.UserListBean;
import user.UserListLogic;
import user.UserLogic;
import util.LogUtil;
import util.StringUtil;

/**
 * ユーザー管理のサーブレット
 */
@WebServlet("/PsychoPassTestServlet")
public class PsychoPassTestServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see BaseServlet#BaseServlet()
     */
    public PsychoPassTestServlet() {
        super();
    }

    /**
     * HTTPのPOSTメソッド受信時に呼び出される処理
     * <pre>
     * セッションに含まれるstate属性の値に応じて
     * ユーザー管理固有の処理を行う
     * </pre>
     * @see BaseServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.println("**** " + this.getClass().getSimpleName() + "#doPost *****");
        System.out.println("PsychoPassTestServlet:"+ request);//デバッグ
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        LoginBean loginUser = (LoginBean)session.getAttribute("loginUser");
        System.out.println("PsychoPassTestServlet_LoginBean.strLogin : "+ loginUser.getLogin());//デバッグ
 //       if((loginUser == null) || (loginUser.getName() == null)){
    	if(loginUser == null){
            procSessionError(request, response, session);
            return;
        }

//        LoginLogic loginLogic = new LoginLogic();
//        loginUser = loginLogic.load(loginUser.getId());
//        session.setAttribute("loginUser", loginUser);
//        UserBean userBean = new UserBean();
//        String login_name=loginUser.getLogin();
//        userBean.setUserName(login_name);
//        System.out.println("PsychoPassTestServlet_LoginBean.strLogin : ");//デバッグ

        String param = request.getParameter("state");
        String[] state = param.split(",");

        LogUtil.println("state: " + state[0]);

        if (state[0] == null) {
            return;
        }

        switch (state[0]) {
        case "list":
            procList(request, response, session, loginUser);
            break;
        case "test":
            procTest(request, response, session, loginUser);
            break;

        }
    }

    /**
     * 最高権限のみが閲覧可能
     * ユーザ情報の全レコードをDBから取得し、ユーザー一覧画面に遷移する
     * <pre>
     * state属性＝"list"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param loginUser      ログイン中のユーザー情報
     */
    private void procList(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser) throws ServletException,
    IOException {

//        if (loginUser.getLvl() != 3) {
//            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
//            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
//            return;
//        }

        UserListLogic userListLogic = new UserListLogic();
        UserListBean userListBean = null;
        userListBean = userListLogic.load();

        request.setAttribute("userList", userListBean);
        getServletContext().getRequestDispatcher("/WEB-INF/user/list.jsp").forward(request, response);
    }

    /**
     * 心理テスト結果画面に遷移する
     * <pre>
     * state属性＝"test"時の処理
     * </pre>
     * @param request   HTTPのリクエスト
     * @param response  HTTPのレスポンス
     * @param session   HTTPのリクエストに含まれるセッション
     * @param loginUser      ログイン中のユーザー情報
     */
    private void procTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginBean loginUser)
            throws ServletException, IOException {
    	//TODO 未処理
//        if (loginUser.getLvl() <= 2) {
//            session.setAttribute("errMessage", MESSAGE_NO_OPERATION_PRIVILEGE);
//            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
//            return;
//        }
    	System.out.println("session : "+ session);
        String strPassword = StringUtil.exchangeESCEncoding(request.getParameter("password1"));

        UserLogic userLogic = new UserLogic();
        UserBean userEdit = userLogic.createUserBeanFromRequest(request,loginUser);
        String errMessage = null;

        errMessage = userLogic.add(strPassword, userEdit);

//        session.removeAttribute("userEdit");

        if (errMessage == null) {
            session.setAttribute("userEdit", userEdit);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/psychologyTest_result.jsp").forward(request, response);
        } else {
            session.setAttribute("errMessage", errMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/user/error.jsp").forward(request, response);
        }
    }


}

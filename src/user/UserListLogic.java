package user;

import dao.UserDao;
import util.LogUtil;

/**
 * ユーザーリストのロジック
 *
 */
public class UserListLogic {

    /**
     * DBから全ユーザーのユーザーBeanのリストを取得する
     * @return 全ユーザーのユーザーBeanのリスト
     */
    public UserListBean load() {
        LogUtil.println(this.getClass().getSimpleName() + "#load");

        UserDao userDao = new UserDao();
        UserListBean userListBean = userDao.loadAll();
        System.out.println("userListBean DBの配列:"+userListBean);
        return userListBean;
    }
}

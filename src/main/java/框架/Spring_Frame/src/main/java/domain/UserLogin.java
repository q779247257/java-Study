package domain;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 17:48
 * @description:
 */
public class UserLogin {
    private String userLogin;//账户
    private String userPassword;//密码

    public UserLogin() {
    }

    public UserLogin(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

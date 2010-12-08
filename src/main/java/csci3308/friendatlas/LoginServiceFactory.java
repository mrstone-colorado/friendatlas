package csci3308.friendatlas;

/**
 * Created by IntelliJ IDEA.
 * User: mrstone
 * Date: Dec 8, 2010
 * Time: 3:51:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LoginServiceFactory {

    private static LoginService instance;

    public static synchronized LoginService getInstance() {
        if(instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public static synchronized void setInstance(LoginService instance) {
        LoginServiceFactory.instance = instance;
    }
}
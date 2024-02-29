package serveses;

import database.ServiceProviderDB;
import entity.ServiceProvider;
import main.LoggerUtility;


import java.util.logging.Logger;
public class LoginToMyAppAsServiceProvider {

    private static final Logger logger = LoggerUtility.getLogger();
    private boolean isLoggedIn;

    public LoginToMyAppAsServiceProvider() {
        this.isLoggedIn = false;
        AppLogger.setLevel(logger);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    public void logout() {
        isLoggedIn=false;
    }
    public void login() {
        isLoggedIn=true;
    }
    public void errorInLogin() {
        logger.info("Something WRONG!,The username or the password is not correct \n" );
    }
    public void loggInCheck(String email, String password) {
        for(ServiceProvider sp: ServiceProviderDB.getServiceProviders())
        {
            if (email.equals(sp.getEmail()) && password.equals(sp.getPassword()))
            {
                login();
            }
        }
    }
}

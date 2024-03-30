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
    public boolean verifyPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);
    }
    public ServiceProvider loggInCheck(String email, String password) {

        for (ServiceProvider a : ServiceProviderDB.getServiceProviders()) {
            logger.warning("Checking Service Provider: " + a.getEmail());
            String x = "Entered email: " + email;
            logger.info(x);

            if (a.getEmail().equalsIgnoreCase(email)) {
                logger.warning("Email match found.");

                if (verifyPassword(password, a.getPassword())) {
                    x="Password match found. Login successful for user: " + email;
                    logger.warning(x);
                    login();
                    return a;
                } else {
                    errorInLogin();
                }
            }

        }
        errorInLogin();
        return null;
    }
}

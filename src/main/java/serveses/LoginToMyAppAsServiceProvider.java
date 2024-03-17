package serveses;

import database.AdminDB;
import database.ServiceProviderDB;
import entity.Admin;
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
            System.out.println("Checking Service Provider: " + a.getEmail());
            System.out.println("Entered email: " + email);

            if (a.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email match found.");

                if (verifyPassword(password, a.getPassword())) {
                    System.out.println("Password match found. Login successful for user: " + email);
                    return a;
                } else {
                    System.out.println("Incorrect password for user: " + email);
                }
            }

        }
        System.out.println("User not found for login: " + email);
        return null;
    }
}

package serveses;

import entity.User;
import main.LoggerUtility;
import database.UserDB;

import java.util.logging.Logger;


public class LoginAsUser {
    private static final Logger logger = LoggerUtility.getLogger();
    private boolean isLoggedIn;
    private String username;
    private String password;

    public LoginAsUser() {
        this.isLoggedIn = false;
        AppLogger.setLevel(logger);
    }

    public LoginAsUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    public void logout() {
        isLoggedIn = false;
        //System.out.println("User logged out.");
    }
    public void login() {
        //if (!isLoggedIn && UserDB.authenticateUser(username, password)) {
            isLoggedIn = true;
           // System.out.println("User logged in successfully.");
        //} else {
           // System.out.println("Login failed. Please check your credentials.");
        //}
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void errorInLogin() {

        logger.info("Something WRONG!,The username or the password is not correct\n");
    }

    public void loggInCheck(String username, String password) {
        logger.info("Attempting login with username: " + username);
        for(User user: UserDB.getUsers())
        {
            if (username.equals(user.getEmail()) && password.equals(user.getPassword()))
            {
                login();
                logger.info("Login successful for username: " + username);
                return;
            }
        }
        logger.warning("Login failed for username: " + username);
        errorInLogin();
        logout();
    }

    public boolean validateUserInformation(User user) {
        return false;
    }
}

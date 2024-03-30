package serveses;

import database.AdminDB;

import entity.Admin;

import main.LoggerUtility ;

import java.util.logging.Logger;

public class LoginToMyAppAsAdmin {

    private static final Logger logger = LoggerUtility.getLogger();
    private boolean isLoggedIn;

    public LoginToMyAppAsAdmin() {
        this.isLoggedIn = false;
        AppLogger.setLevel(logger);
    }

    public boolean isLoggedIn() {

        return isLoggedIn;
    }

    public void logout() {

        isLoggedIn = false;
    }

    public void login() {

        isLoggedIn = true;
    }

    public boolean verifyPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);
    }

    public Admin loggInCheck(String email, String password) {
String x = "\nLogin successful for user: " + email+"\n" ;
        for (Admin a : AdminDB.getAdmins()) {
            if (email.equalsIgnoreCase(a.getEmail()) && verifyPassword(password, a.getPassword())) {
                isLoggedIn = true; // Update login state on successful authentication
                logger.info(x);
                return a;
            }
        }
        x="\nUser not found or incorrect password for login: " + email+"\n";
        logger.info(x);
        return null;

        }
}





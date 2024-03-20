package serveses;

import database.AdminDB;
import database.UserDB;
import entity.Admin;
import entity.User;
import main.LoggerUtility ;
import org.mindrot.jbcrypt.BCrypt;

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

    public void errorInLogin() {

        logger.info("___________________________________________________________________________________\n");
        logger.warning("|             Something WRONG!,The username or the password is not correct        |\n");
        logger.info("|_________________________________________________________________________________|\n");
    }

    public boolean verifyPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);
    }

    public Admin loggInCheck(String email, String password) {

        for (Admin a : AdminDB.getAdmins()) {
            if (email.equalsIgnoreCase(a.getEmail()) && verifyPassword(password, a.getPassword())) {
                isLoggedIn = true; // Update login state on successful authentication
                logger.info("\nLogin successful for user: " + email+"\n");
                return a;
            }
        }
        logger.info("\nUser not found or incorrect password for login: " + email+"\n");
        return null;

        }
}





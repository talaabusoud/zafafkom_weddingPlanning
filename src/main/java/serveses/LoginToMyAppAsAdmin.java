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
            System.out.println("Checking Admin: " + a.getEmail());
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





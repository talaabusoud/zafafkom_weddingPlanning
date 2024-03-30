package serveses;

import entity.User;
import main.LoggerUtility;
import database.UserDB;
import org.mindrot.jbcrypt.BCrypt;

import java.util.logging.Logger;

public class LoginAsUser {
    private static final Logger logger = LoggerUtility.getLogger();
    private boolean isLoggedIn;
    private String username;
    private String password;
     String temp;

    public LoginAsUser() {
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


        if (!isLoggedIn && loggInCheck(username, password) != null) {
            isLoggedIn = true;
            logger.info("User logged in successfully.");
        } else {
            logger.warning("Login failed. Please check your credentials.");
        }


    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void errorInLogin() {

        logger.info("Something WRONG!,The username or the password is not correct\n");
    }

    // Verify entered password against stored hashed password
    public boolean verifyPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }


    public User loggInCheck(String enteredEmail, String enteredPassword) {
        for (User u : UserDB.getUsers()) {
            logger.info("Checking user: " + u.getEmail());
            temp = "Entered email: " + enteredEmail;
            logger.info(temp);

            if (u.getEmail().equalsIgnoreCase(enteredEmail)) {
                logger.info("Email match found.");

                if (verifyPassword(enteredPassword, u.getPassword())) {
                    temp="Password match found. Login successful for user: " + enteredEmail;
                    logger.info(temp);
                    return u; // Successfully logged in
                } else {
                    temp="Incorrect password for user: " + enteredEmail;
                    logger.info(temp);
                }
            }
        }
temp="User not found for login: " + enteredEmail;
        logger.info(temp);
        return null;
    }

    public boolean validateUserInformation(User user) {
        return user != null && user.getEmail() != null && !user.getEmail().isEmpty();

    }
}

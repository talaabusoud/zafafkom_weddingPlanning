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
            //isLoggedIn = true;

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

//    public User loggInCheck(String enteredEmail, String enteredPassword) {
//        for (User u : UserDB.getUsers()) {
//            if (u.getEmail().equalsIgnoreCase(enteredEmail)) {
//                if (verifyPassword(enteredPassword, u.getPassword())) {
//                    return u; // Successfully logged in
//                }
//            }
//        }
//        return null; // Login failed
//    }

    public User loggInCheck(String enteredEmail, String enteredPassword) {
        for (User u : UserDB.getUsers()) {
            System.out.println("Checking user: " + u.getEmail());
            System.out.println("Entered email: " + enteredEmail);

            if (u.getEmail().equalsIgnoreCase(enteredEmail)) {
                System.out.println("Email match found.");

                if (verifyPassword(enteredPassword, u.getPassword())) {
                    System.out.println("Password match found. Login successful for user: " + enteredEmail);
                    return u; // Successfully logged in
                } else {
                    System.out.println("Incorrect password for user: " + enteredEmail);
                }
            }
        }

        System.out.println("User not found for login: " + enteredEmail);
        return null; // Login failed
    }

    public boolean validateUserInformation(User user) {
        return user != null && user.getEmail() != null && !user.getEmail().isEmpty();
//        return false;
    }
}

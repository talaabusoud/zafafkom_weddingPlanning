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

        isLoggedIn=false;
    }
    public void login() {

        isLoggedIn = true;
    }
    public void errorInLogin() {

        logger.info("___________________________________________________________________________________\n");
     logger.warning("|             Something WRONG!,The username or the password is not correct        |\n");
        logger.info("|_________________________________________________________________________________|\n");
    }
    public void loggInCheck(String email, String password) {
        for(Admin admin: AdminDB.getAdmins())
        {
            if (email.equals(admin.getEmail()) && password.equals(admin.getPassword()))
            {
                login();
            }
        }
    }
}







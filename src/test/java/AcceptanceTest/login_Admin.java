package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import serveses.LoginToMyAppAsAdmin;

import java.util.logging.Logger;

import static org.junit.Assert.*;


public class login_Admin {
    private static Logger logger = LoggerUtility.getLogger();
    LoginToMyAppAsAdmin myApp;
    String password,email;
    public login_Admin()
    {
        myApp = new LoginToMyAppAsAdmin();
    }


    @Given("that the admin is not logged in the app")
    public void thatTheAdminIsNotLoggedInTheApp() {

        assertFalse(myApp.isLoggedIn());
    }
    @Given("the username is {string}")
    public void theUsernameIs(String email) {
        this.email = email;
    }
    @Given("the password is {string}")
    public void thePasswordIs(String password) {
        this.password = password;
    }
    @Then("the admin is logged in the app successfully")
    public void theAdminIsLoggedInTheAppSuccessfully() {
        myApp.loggInCheck(email,password);
        assertEquals(true,myApp.isLoggedIn());

    }
    @Then("the admin will not login")
    public void theAdminWillNotLogin() {
        myApp.loggInCheck(email,password);
        assertFalse(myApp.isLoggedIn());
        logger.info("\n");

    }
    @Then("the message appear to tell the admin what's wrong")
    public void theMessageAppearToTellTheAdminWhatSWrong() {
        myApp.errorInLogin();
    }

}

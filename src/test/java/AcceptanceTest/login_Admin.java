package AcceptanceTest;

import database.AdminDB;
import entity.Admin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Main;
import main.LoggerUtility;
import serveses.LoginToMyAppAsAdmin;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class login_Admin {
    private static final Logger logger = LoggerUtility.getLogger();
    private LoginToMyAppAsAdmin myApp;
    private Admin admin;

    public login_Admin() {
        myApp = new LoginToMyAppAsAdmin();
    }

    @Given("the admin is not logged in to the app")
    public void theAdminIsNotLoggedInToTheApp() {
        assertFalse(myApp.isLoggedIn());
    }

    @When("the admin logs in with email {string} and password {string}")
    public void theAdminLogsInWithEmailAndPassword(String email, String password) {
        admin = myApp.loggInCheck(email, password);
    }

    @Then("the admin should be directed to the admin page")
    public void theAdminShouldBeDirectedToTheAdminPage() {
        assertNotNull(admin);
        assertTrue(myApp.isLoggedIn());
        AdminDB.displayAdmin(admin);
    }

    @Then("the admin should see a login failed message")
    public void theAdminShouldSeeALoginFailedMessage() {
        assertNull(admin);
        assertFalse(myApp.isLoggedIn());
        logger.info("Login failed! Please check your email and password and try again.");
    }

    @When("the admin tries to log in again with email {string} and password {string}")
    public void theAdminTriesToLogInAgainWithEmailAndPassword(String email, String password) {
        theAdminLogsInWithEmailAndPassword(email, password);
    }

    @When("the admin selects to return to the home page")
    public void theAdminSelectsToReturnToTheHomePage() {
        Main.menu();
    }

    @Then("the admin should see the home page")
    public void theAdminShouldSeeTheHomePage() {
        logger.info("Returned to the home page successfully.");
    }
}

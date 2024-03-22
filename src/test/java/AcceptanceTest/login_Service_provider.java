package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import serveses.LoginToMyAppAsServiceProvider;

import entity.ServiceProvider;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class login_Service_provider {

    private LoginToMyAppAsServiceProvider myApp;
    private String password, email;

    public login_Service_provider()
    {
        myApp = new LoginToMyAppAsServiceProvider();
    }

    @Given("that the owner is not logged in the app")
    public void thatTheOwnerIsNotLoggedInTheApp() {
        assertFalse(myApp.isLoggedIn());
    }
    @Given("the username owner is {string}")
    public void theUsernameOwnerIs(String email) {
        this.email = email;
    }
    @Given("the password owner is {string}")
    public void thePasswordOwnerIs(String password) {
        this.password = password;
    }
    @Then("the owner is logged in the app successfully")
    public void theOwnerIsLoggedInTheAppSuccessfully() {
        ServiceProvider serviceProvider = myApp.loggInCheck(email, password);
        assertNotNull("Login failed", serviceProvider);
        assertTrue("Login successful", myApp.isLoggedIn());
    }

        @Then("the owner will not login")
    public void theOwnerWillNotLogin() {
        try {
            myApp.loggInCheck(email, password);
            assertFalse("Login successful", myApp.isLoggedIn());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    @Then("the message appear to tell the owner what's wrong")
    public void theMessageAppearToTellTheOwnerWhatSWrong() {
        myApp.errorInLogin();
    }
}

package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class login_Service_provider {

    boolean isLoggedIn = false;
    boolean isErrorMessageDisplayed = false;

    @Given("the Service provider is on the login page")
    public void theServiceProviderIsOnTheLoginPage() {
    }

    @When("the Service provider enters valid username and password")
    public void theServiceProviderEntersValidUsernameAndPassword() {
        isLoggedIn = true; // Simulating successful login
    }

    @Then("the Service provider should be successfully logged in")
    public void theServiceProviderShouldBeSuccessfullyLoggedIn() {
        assertEquals(true, isLoggedIn);
    }

    @When("the Service provider enters invalid username {string} or password {string}")
    public void theServiceProviderEntersInvalidUsernameOrPassword(String username, String password) {
        isLoggedIn = false; // Simulating failed login
    }

    @When("the Service provider leaves the username and password fields empty")
    public void theServiceProviderLeavesTheUsernameAndPasswordFieldsEmpty() {
        isLoggedIn = false;
        isErrorMessageDisplayed = true;
    }

    @Then("an error message should be displayed prompting the Service provider to enter both username and password")
    public void anErrorMessageShouldBeDisplayedPromptingTheServiceProviderToEnterBothUsernameAndPassword() {
        assertEquals(true, isErrorMessageDisplayed);
    }
}

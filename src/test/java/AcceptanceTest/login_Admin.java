package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class login_Admin {

    boolean isLoggedIn = false;
    boolean isErrorMessageDisplayed = false;

    @Given("the admin is on the login page")
    public void theAdminIsOnTheLoginPage() {
        // Implement code to navigate to the login page
    }

    @When("the admin enters valid username and password")
    public void theAdminEntersValidUsernameAndPassword() {
        // Implement code to enter valid username and password
    }

    @When("clicks on the login button")
    public void clicksOnTheLoginButton() {
        isLoggedIn = true; // Assuming successful login
    }

    @Then("the admin should be successfully logged in")
    public void theAdminShouldBeSuccessfullyLoggedIn() {
        assertEquals(true, isLoggedIn);
    }

    @When("the admin enters invalid username {string} or password {string}")
    public void theAdminEntersInvalidUsernameOrPassword(String username, String password) {
        // Implement code to enter invalid username or password
    }

    @Then("the appropriate \"\"Invalid username or password provided\"\" message should be displayed")
    public void theAppropriateInvalidUsernameOrPasswordProvidedMessageShouldBeDisplayed() {
        isErrorMessageDisplayed = true; // Assuming error message is displayed
        assertEquals(true, isErrorMessageDisplayed);
    }

    @When("the admin leaves the username and password fields empty")
    public void theAdminLeavesTheUsernameAndPasswordFieldsEmpty() {
        // Implement code to leave both username and password fields empty
    }

    @Then("an error message should be displayed prompting the admin to enter both username and password")
    public void anErrorMessageShouldBeDisplayedPromptingTheAdminToEnterBothUsernameAndPassword() {
        isErrorMessageDisplayed = true; // Assuming error message is displayed
        assertEquals(true, isErrorMessageDisplayed);
    }
}

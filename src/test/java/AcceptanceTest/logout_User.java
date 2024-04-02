package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import serveses.LoginAsUser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class logout_User {
    LoginAsUser system ;

    @Given("that the user choose to logout")
    public void thatTheUserChooseToLogout() {
        // Write code here that turns the phrase above into concrete actions
        system = new LoginAsUser();
        system.setCredentials("jana123@gmail.com", "12345666");  // Set the credentials explicitly
        system.login();
        assertTrue(system.isLoggedIn());
    }
    @Then("the user will be logout")
    public void theUserWillBeLogout() {
        system.logout();
        assertFalse(system.isLoggedIn());
    }
    @Then("the the user can't logout")
    public void the_the_user_can_not_logout() {
        assertTrue(system.isLoggedIn());
    }

}

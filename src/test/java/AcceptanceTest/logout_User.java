package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import serveses.LoginAsUser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class logout_User {
    LoginAsUser system ;

//    public logout_User(){
//        system = new LoginAsUser();
//        system.login();
//
//    }

    @Given("that the user choose to logout")
    public void thatTheUserChooseToLogout() {
        // Write code here that turns the phrase above into concrete actions
        system = new LoginAsUser();
        system.setCredentials("jana123@gmail.com", "12345666");  // Set the credentials explicitly
        system.login();
        assertTrue(system.isLoggedIn());
        //throw new io.cucumber.java.PendingException();
    }
    @Then("the user will be logout")
    public void theUserWillBeLogout() {
        // Write code here that turns the phrase above into concrete actions
        system.logout();
        assertFalse(system.isLoggedIn());
        //throw new io.cucumber.java.PendingException();
    }
    @Then("the the user can't logout")
    public void the_the_user_can_not_logout() {
        assertTrue(system.isLoggedIn());
    }

}

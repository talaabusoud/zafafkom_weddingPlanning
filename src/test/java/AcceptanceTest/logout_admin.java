package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class logout_admin {

    private boolean adminLoggedIn = true;

    @Given("that the admin choose to logout")
    public void thatTheAdminChooseToLogout() {
        adminLoggedIn = false;
    }

    @Then("the admin will be logout")
    public void theAdminWillBeLogout() {
        assertEquals(false, adminLoggedIn);
    }

    @Then("the the admin can't logout")
    public void theTheAdminCanTLogout() {
        assertEquals(true, !adminLoggedIn);
    }

}

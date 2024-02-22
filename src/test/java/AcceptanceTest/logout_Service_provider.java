package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class logout_Service_provider {

    private boolean ServiceproviderLoggedIn = true;

    @Given("that the Service provider choose to logout")
    public void thatTheServiceproviderChooseToLogout() {
        ServiceproviderLoggedIn = false;
    }

    @Then("the Service provider will be logout")
    public void theServiceproviderWillBeLogout() {
        assertEquals(false, ServiceproviderLoggedIn);
    }

    @Then("the the Service provider can't logout")
    public void theTheServiceproviderCanTLogout() {
        assertEquals(true, !ServiceproviderLoggedIn);
    }

}

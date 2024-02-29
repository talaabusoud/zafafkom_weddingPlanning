package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import serveses.LoginToMyAppAsServiceProvider;
import static org.junit.Assert.*;

public class logout_Service_provider {


     LoginToMyAppAsServiceProvider myApp ;
    public logout_Service_provider(){
        myApp = new LoginToMyAppAsServiceProvider();
        myApp.login();
    }

    @Given("that the Service provider choose to logout")
    public void thatTheServiceproviderChooseToLogout() {

            assertTrue(myApp.isLoggedIn());

    }

    @Then("the Service provider will be logout")
    public void theServiceproviderWillBeLogout() {
        myApp.logout();
        assertFalse(myApp.isLoggedIn());
    }

    @Then("the the Service provider can't logout")
    public void theTheServiceproviderCanTLogout() {
            assertTrue(myApp.isLoggedIn());

    }

}

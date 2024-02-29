package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import serveses.LoginToMyAppAsAdmin;

import static org.junit.Assert.*;


public class logout_admin {
    LoginToMyAppAsAdmin myApp ;
    public logout_admin(){
        myApp = new LoginToMyAppAsAdmin();
        myApp.login();
    }
    @Given("that the admin choose to logout")
    public void that_the_admin_choose_to_logout() {
        assertTrue(myApp.isLoggedIn());
    }
    @Then("the admin will be logout")
    public void the_admin_will_be_logout() {
        myApp.logout();
        assertFalse(myApp.isLoggedIn());
    }


    @Then("the the admin can't logout")
    public void the_the_admin_can_t_logout() {
        assertTrue(myApp.isLoggedIn());
    }

}

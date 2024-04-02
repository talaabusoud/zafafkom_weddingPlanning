package AcceptanceTest;

import entity.Service;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import main.LoggerUtility;
import serveses.AppLogger;
import serveses.LoginToMyAppAsAdmin;
import serveses.AddServiceToMyAppAsServiceProvider ;
import java.util.logging.Logger;
import database.ServiceDB;

import static database.RequestToAddServiceDB.clearTheRequestList;
import static database.RequestToAddServiceDB.getServices;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class add_Service_By_Admin {
    LoginToMyAppAsAdmin myApp ;
    Service service;
    private static final Logger logger = LoggerUtility.getLogger();
    public add_Service_By_Admin(){
        myApp = new LoginToMyAppAsAdmin();
        myApp.login();
        AppLogger.setLevel(logger);
    }

    @Given("that the administrator is logged in")
    public void thatTheAdministratorIsLoggedIn() {
         assertTrue(myApp.isLoggedIn());
    }
    @Given("request list have Sarvice to rent")
    public void requestListHaveSarviceToRent() {
        AddServiceToMyAppAsServiceProvider.addServiceToRequestList(service);
        assertTrue( getServices().size() > 0);
    }
    @Then("the Sarvice will be saved in the Sarvice list with in available state")
    public void theSarviceWillBeSavedInTheSarviceListWithInAvailableState() {
        for(Service s :getServices()){
            ServiceDB.addService(s);
        }
        clearTheRequestList();
    }
    @Then("the requests list will be empty")
    public void theRequestsListWillBeEmpty() {
        assertEquals(0, getServices().size());
    }

    @Given("request list dosn't have Service to rent")
    public void requestListDosnTHaveServiceToRent() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(0, getServices().size());
    }
    @Then("the message appear to tell the admin that request list empty")
    public void theMessageAppearToTellTheAdminThatRequestListEmpty() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("The request list EMPTY, you dont have houses to add");
    }

}

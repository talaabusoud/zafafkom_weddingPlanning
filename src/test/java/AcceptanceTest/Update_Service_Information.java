package AcceptanceTest;

import database.ServiceDB;
import entity.Reserve;
import entity.Service;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import serveses.AppLogger;
import serveses.LoginToMyAppAsServiceProvider;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Update_Service_Information {
    private static final Logger logger = LoggerUtility.getLogger();
    LoginToMyAppAsServiceProvider loginToMyAppAsServiceProvider;
    Service service; // Assuming Service class

    public Update_Service_Information() {
        loginToMyAppAsServiceProvider = new LoginToMyAppAsServiceProvider();
        loginToMyAppAsServiceProvider.login();
        AppLogger.setLevel(logger);
    }
    @Given("the service list has a service with ID {int}")
    public void theServiceListHasAServiceWithID(Integer id) {

        service = ServiceDB.getServices().get(id); // Use getServiceById
        if (service == null) {
            throw new RuntimeException("Service with ID " + id + " not found");
        }
          }
    @When("the admin updates the service with ID {int} to have a new price {double} and status available")
    public void theAdminUpdatesTheServiceWithIDToHaveANewPriceAndStatusAvailable(Integer id, Double newPrice) {
        service.setPrice(newPrice);
        service.setStatus("available"); // Assuming "available" is a valid status
    }
    @Then("the service with ID {int} should have its price updated to {double} and its status updated to available")
    public void theServiceWithIDShouldHaveItsPriceUpdatedToAndItsStatusUpdatedToAvailable(Integer id, Double expectedPrice) {
        Service updatedService = ServiceDB.getServices().get(id);
       // assertEquals(expectedPrice, updatedService.getPrice(), 0.001);
        assertEquals("available", updatedService.getStatus()); }


    @When("the admin updates the service with ID {int} to have a new price {double} and status discontinued")
    public void theAdminUpdatesTheServiceWithIDToHaveANewPriceAndStatusDiscontinued(Integer id, Double double1) {
        service = ServiceDB.getServices().get(id); // Get the service by ID
        service.setPrice(double1);
        service.setStatus("discontinued");
    }
    @Then("the service with ID {int} should have its price updated to {double} and its status updated to discontinued")
    public void theServiceWithIDShouldHaveItsPriceUpdatedToAndItsStatusUpdatedToDiscontinued(Integer id, Double double1) {
        Service updatedService = ServiceDB.getServices().get(id);
        assertEquals(double1, updatedService.getPrice(), 0.001);
        assertEquals("discontinued", updatedService.getStatus());
    }

    @Given("that the admin wants to update the service with ID {int} to have an invalid {double} and\\/or invalid available")
    public void thatTheAdminWantsToUpdateTheServiceWithIDToHaveAnInvalidAndOrInvalidAvailable(Integer id, Double double1) {
        service = ServiceDB.getServices().get(id);
    }
    @When("the admin updates the service with ID {int}")
    public void theAdminUpdatesTheServiceWithID(Integer id) {
    }
    @Then("the error message should be equal to {string}")
    public void theErrorMessageShouldBeEqualTo(String string) {
        logger.info("\n"+string);
    }

    @Given("that the admin wants to update the service with ID {int} to have an invalid {int} and\\/or invalid abc")
    public void thatTheAdminWantsToUpdateTheServiceWithIDToHaveAnInvalidAndOrInvalidAbc(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("that the admin wants to update the service with ID {int} to have an invalid {int} and\\/or invalid ")
    public void thatTheAdminWantsToUpdateTheServiceWithIDToHaveAnInvalidAndOrInvalid(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
    }

}

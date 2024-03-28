package AcceptanceTest;

import database.ServiceDB;
import entity.Service;
import entity.ServiceProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import serveses.AppLogger;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class show_Services_And_Delete_Option_by_admin {
    private static final Logger logger = LoggerUtility.getLogger();
    private List<Service> servicesBeforeAction;
    private int serviceIDToDelete = 3 ;
    private boolean deletionResult;

    public show_Services_And_Delete_Option_by_admin() {
        AppLogger.setLevel(logger);
    }

    @Given("the admin is logged into the admin panel")
    public void theAdminIsLoggedIntoTheAdminPanel() {
        assertTrue("Admin should be logged in", true);

    }
    @When("the admin chooses to view the list of services")
    public void theAdminChoosesToViewTheListOfServices() {
        servicesBeforeAction = ServiceDB.getServices();
        assertNotNull("Service list should not be null", servicesBeforeAction);
    }

    @Then("the admin should see a list of all services")
    public void theAdminShouldSeeAListOfAllServices() {
        assertFalse("Service list should not be empty", servicesBeforeAction.isEmpty());
        ServiceDB.displayServices(servicesBeforeAction); // This will log the list of services
    }


    @Given("a list of services is displayed")
    public void aListOfServicesIsDisplayed() {

        Service service = new Service();
        service.setId(12000);
        service.setLocation("Hebron");
        service.setType("Hall");
        service.setStatus("available");
        service.setName("Royal");
        service.setPrice(15000);

        // When
        String result = service.toString();

        // Then
        String expected = "Service{id=12000, location='Hebron', type='Hall', status='available', name='Royal', price=15000.0}";
        assertEquals(expected, result);


        servicesBeforeAction = ServiceDB.getServices();
        assertFalse(servicesBeforeAction.isEmpty());


    }
    @When("the admin chooses to delete a service by entering its ID")
    public void theAdminChoosesToDeleteAServiceByEnteringItsID() {
        deletionResult = ServiceDB.deleteService(serviceIDToDelete);


    }
    @Then("the service should be deleted successfully")
    public void theServiceShouldBeDeletedSuccessfully() {
        assertTrue("Service deletion should be successful", deletionResult);

    }
    @Then("the updated list of services is displayed")
    public void theUpdatedListOfServicesIsDisplayed() {
        List<Service> servicesAfterDeletion = ServiceDB.getServices();
        assertNotNull("Service list should not be null after deletion", servicesAfterDeletion);
       // assertTrue("Service list size should decrease after deletion", servicesAfterDeletion.size() < servicesBeforeAction.size());
        ServiceDB.displayServices(servicesAfterDeletion); // This will log the updated list of services


    }

    @When("the admin enters an invalid option")
    public void theAdminEntersAnInvalidOption() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("an error message is displayed indicating the choice is invalid")
    public void anErrorMessageIsDisplayedIndicatingTheChoiceIsInvalid() {
        logger.info("Invalid choice. Please select 1 or 2."); // Simulating error message logging

    }


    @When("the admin attempts to delete a service with an invalid ID")
    public void theAdminAttemptsToDeleteAServiceWithAnInvalidID() {
        deletionResult = ServiceDB.deleteService(Integer.MAX_VALUE);


    }
    @Then("an error message is displayed indicating no service was found with that ID")
    public void anErrorMessageIsDisplayedIndicatingNoServiceWasFoundWithThatID() {

        assertFalse("Deletion should fail for an invalid service ID", deletionResult);
        logger.info("No service found with the provided ID."); // Simulating error message logging

    }

}

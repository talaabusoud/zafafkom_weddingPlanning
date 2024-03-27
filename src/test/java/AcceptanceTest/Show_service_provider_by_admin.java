package AcceptanceTest;

import database.ServiceProviderDB;
import entity.ServiceProvider;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.Main;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;

public class Show_service_provider_by_admin {

    private static final Logger logger = LoggerUtility.getLogger();

    @When("the admin chooses to view the list of service providers")
    public void theAdminChoosesToViewTheListOfServiceProviders() {
        logger.info("Admin chooses to view the list of service providers.");
    }
    @Then("the admin should see a list of all service providers")
    public void theAdminShouldSeeAListOfAllServiceProviders() {
        List<ServiceProvider> providers = ServiceProviderDB.getServiceProviders();
        assertFalse("The list of service providers should not be empty", providers.isEmpty());
    }


}

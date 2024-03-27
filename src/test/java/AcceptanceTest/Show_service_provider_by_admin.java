package AcceptanceTest;

import database.ServiceProviderDB;
import entity.ServiceProvider;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.Main;

import java.util.List;
import java.util.logging.Logger;

public class Show_service_provider_by_admin {

    private static final Logger logger = LoggerUtility.getLogger();

    @When("the admin chooses to view the list of service providers")
    public void theAdminChoosesToViewTheListOfServiceProviders() {
        logger.info("Admin chooses to view the list of service providers.");
    }
    @Then("the admin should see a list of all service providers")
    public void theAdminShouldSeeAListOfAllServiceProviders() {
        List<ServiceProvider> serviceProviders = ServiceProviderDB.getServiceProviders();
        if (serviceProviders.isEmpty()) {
            logger.info("No service providers found.");
        } else {
            String format = "|%-12s|%12s|%15s|%14s|%20s|%20s|\n";
            logger.info(String.format(format, "ID", "Name", "Phone", "Address", "Email", "password"));

            logger.info(new String(new char[100]).replace('\0', '-'));
            logger.info("\n");
            for (ServiceProvider serviceProvider : serviceProviders) {
                logger.info(String.format(format,
                        serviceProvider.getId(),
                        serviceProvider.getName(),
                        serviceProvider.getPhone(),
                        serviceProvider.getAddress(),
                        serviceProvider.getEmail(),
                        serviceProvider.getPassword()
                ));
            }
        }

    }


}

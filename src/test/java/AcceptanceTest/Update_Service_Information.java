package AcceptanceTest;

import database.ServiceDB;
import entity.Service;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.TestInput;
import serveses.LoginToMyAppAsServiceProvider;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Update_Service_Information {
    private static final Logger logger = LoggerUtility.getLogger();
    LoginToMyAppAsServiceProvider loginToMyAppAsServiceProvider;
    Service service; // Assuming Service class

    public Update_Service_Information() {
        loginToMyAppAsServiceProvider = new LoginToMyAppAsServiceProvider();
        loginToMyAppAsServiceProvider.login();
    }


    @Given("the service provider is logged into their account")
    public void theServiceProviderIsLoggedIntoTheirAccount() {
        assertNotNull(loginToMyAppAsServiceProvider, "Service provider must be logged in.");

    }
    @Given("the service list has a service with ID {int} belonging to the provider")
    public void theServiceListHasAServiceWithIDBelongingToTheProvider(Integer id) {
        service = ServiceDB.getServiceById(id);
        assertNotNull(service, "Service with ID " + id + " should exist.");
    }
    @When("the service provider chooses to update the service with ID {int}")
    public void theServiceProviderChoosesToUpdateTheServiceWithID(Integer id) {
        this.service = ServiceDB.getServiceById(id);
        assertNotNull(service, "Service should not be null.");
    }
    @When("enters {string} for the new name")
    public void entersForTheNewName(String newName) {
        if(TestInput.isValidName(newName)) {
            service.setName(newName);
        } else {
            logger.info("Invalid name. Keeping the current name.");
        }
    }
    @When("enters {string} for the new type")
    public void entersForTheNewType(String newType) {
        if (TestInput.type(newType)) {
            service.setType(newType);
        } else {
            logger.info("\nInvalid type. No changes made.");
        }

    }
    @When("enters {string} for the new location")
    public void entersForTheNewLocation(String newLocation) {
        service.setLocation(newLocation);

    }
    @When("chooses {string} for the new status")
    public void choosesForTheNewStatus(String newStatus) {
            if (newStatus.equals("1") || newStatus.equalsIgnoreCase("Available")) {
                service.setStatus("Available");
            } else if (newStatus.equals("2") || newStatus.equalsIgnoreCase("Not Available")) {
                service.setStatus("Not Available");
            } else {
                logger.info("\nInvalid status choice. No changes made.");
            }

    }
    @When("enters {string} for the new price")
    public void entersForTheNewPrice(String newPriceStr) {
        if (TestInput.isValidPrice(newPriceStr)) {
            double newPrice = Double.parseDouble(newPriceStr);
            service.setPrice(newPrice);
        } else {
            logger.info("\nInvalid price format. No changes made.");
        }
    }
    @When("enters {string} for the new phone")
    public void entersForTheNewPhone(String newPhone) {
        if (TestInput.isValidPhone(newPhone)) {
            service.setPhone(newPhone);
        } else {
            logger.info("\nInvalid phone format. No changes made.");
        }

    }
    @When("enters {string} for the new image URL")
    public void entersForTheNewImageURL(String newImageURL) {
        if (TestInput.imge(newImageURL)) {
            service.setImage(newImageURL);
        } else {
            logger.info("\nInvalid image URL. No changes made.");
        }

    }
    @Then("the service should be updated successfully with the new information")
    public void theServiceShouldBeUpdatedSuccessfullyWithTheNewInformation() {
        Service updatedService = ServiceDB.getServiceById(service.getId());
        assertNotNull(updatedService, "Updated service should not be null.");
        assertEquals(service.getName(), updatedService.getName());

    }

    @When("the service provider attempts to update a service with an invalid ID <InvalidID>")
    public void theServiceProviderAttemptsToUpdateAServiceWithAnInvalidIDInvalidID() {
        this.service = ServiceDB.getServiceById(-1); // فرض أن -1 هو معرف غير صالح
        assertNull(this.service, "Service should not exist for an invalid ID.");

    }

    @When("enters invalid data for the new name, type, price, phone, and image URL")
    public void entersInvalidDataForTheNewNameTypePricePhoneAndImageURL() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("an error message is displayed for each invalid input")
    public void anErrorMessageIsDisplayedForEachInvalidInput() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the service's current information is kept unchanged")
    public void theServiceSCurrentInformationIsKeptUnchanged() {
        // Write code here that turns the phrase above into concrete actions

    }



}

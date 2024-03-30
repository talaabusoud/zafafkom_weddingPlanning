package AcceptanceTest;

import database.ServiceProviderDB;
import entity.ServiceProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.TestInput;
import serveses.LoginToMyAppAsServiceProvider;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class show_Service_provider_profile {
    LoginToMyAppAsServiceProvider myApp;
    ServiceProvider service_provider;
    private static Logger logger = LoggerUtility.getLogger();
    private String newName;
    private String newPhone;
    private String newAddress;
    private String newPassword;
    boolean re_test;

    public show_Service_provider_profile() {
        myApp = new LoginToMyAppAsServiceProvider();
        myApp.login();

    }
    @Given("that the service provider is logged in")
    public void thatTheServiceProviderIsLoggedIn() {
        assertTrue(myApp.isLoggedIn());
        logger.info("\n");

    }
    @Then("the service provider profile appears with the following information:")
    public void theServiceProviderProfileAppearsWithTheFollowingInformation() {
        ServiceProviderDB.displayServiceProvider(service_provider);

    }


    @Given("the service provider wants to update their profile information")
    public void theServiceProviderWantsToUpdateTheirProfileInformation() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the service provider changes their {string} to {string}")
    public void theServiceProviderChangesTheirTo(String field, String value) {
            switch (field) {
                case "name":
                    if(TestInput.isValidName(field)) {
                        re_test = true;
                        newName=value;
                    } else {
                        re_test = false;
                        logger.warning("Invalid name format.");
                    }
                    break;
                case "phone number":
                    if(TestInput.isValidPhone(value)) {
                        re_test = true;
                        newPhone=value;
                    } else {
                        re_test = false;
                        logger.warning("Invalid phone number format.");
                    }
                    break;
                case "address":
                    if(TestInput.isValidName(value)) {
                        re_test = true;
                        newAddress=value;
                    } else { re_test = false;
                        logger.warning("Invalid name format.");}
                    break;
                case "password":
                    newPassword=value;

                    break;
                default:
                    logger.warning("Invalid field: " + field);
            }
    }
    @When("the service provider clicks on the {string} button")
    public void theServiceProviderClicksOnTheButton(String button) {
        if (button.equals("Save") && service_provider != null){
            if(re_test==true){
                service_provider.setName(newName);
                service_provider.setPhone(newPhone);
                service_provider.setAddress(newAddress);
                service_provider.setPassword(newPassword);
            }
        }
        else System.out.println(button+"\t"+service_provider);

    }
    @Then("the service provider profile should be updated successfully")
    public void theServiceProviderProfileShouldBeUpdatedSuccessfully() {
        assertEquals(true,re_test);
    }

    @Then("the service provider should see {string}")
    public void theServiceProviderShouldSee(String message) {
        if (message.equals("Profile updated successfully")) {

            logger.warning( message);

        } else {
            logger.warning("Unexpected message: " + message);
        }
    }
    }


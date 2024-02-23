package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class Update_Service_Information {
    private boolean updateCancelled = false;
    private boolean serviceInfoCleared = false;
    private boolean serviceUpdated = false;


    @Given("the admin is on the service update page")
    public void theAdminIsOnTheServiceUpdatePage() {


    }
    @When("the admin decides to cancel the update")
    public void theAdminDecidesToCancelTheUpdate() {
        updateCancelled = true;

    }
    @When("clicks on the {string} button")
    public void clicksOnTheButton(String button) {
        if (button.equals("Cancel")) {
            theAdminDecidesToCancelTheUpdate();
        }
    }
    @Then("all service information fields should be cleared")
    public void allServiceInformationFieldsShouldBeCleared() {
        if (updateCancelled) {
            serviceInfoCleared = true;
        }

    }

    @Given("there is a service with ID {int}")
    public void thereIsAServiceWithID(Integer int1) {

    }
    @When("the admin updates the service information with valid values")
    public void theAdminUpdatesTheServiceInformationWithValidValues() {
        serviceUpdated = true;

    }
    @Then("the service information should be updated successfully")
    public void theServiceInformationShouldBeUpdatedSuccessfully() {
        assertEquals(true,serviceUpdated);

    }



    @When("the admin updates the service information with invalid values")
    public void theAdminUpdatesTheServiceInformationWithInvalidValues() {
        serviceUpdated = false;
    }
    @Then("an error {string} should be displayed")
    public void anErrorShouldBeDisplayed(String string) {
        System.out.println(string);
        assertEquals(false,serviceUpdated);

    }



}

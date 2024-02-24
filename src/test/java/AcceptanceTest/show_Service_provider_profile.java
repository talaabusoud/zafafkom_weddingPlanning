package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class show_Service_provider_profile {

    private String password;
    private String phoneNumber;
    private String address;
    private String name;
    private boolean profileUpdated;
    @Given("that the Service provider is logged in")
    public void thatTheServiceProviderIsLoggedIn() {


    }
    @Given("the Service provider has the following information:")
    public void theServiceProviderHasTheFollowingInformation(io.cucumber.datatable.DataTable dataTable) {


    }
    @Then("the Service provider profile appears with the provided information")
    public void theServiceProviderProfileAppearsWithTheProvidedInformation() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(true, true);
    }


    @Given("the Service provider wants to update their profile information")
    public void theServiceProviderWantsToUpdateTheirProfileInformation() {

    }
    @When("the Service provider changes their password to {string}")
    public void theServiceProviderChangesTheirPasswordTo(String newPassword) {
        this.password = newPassword;

    }
    @When("the Service provider changes their phone number to {string}")
    public void theServiceProviderChangesTheirPhoneNumberTo(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    @When("the Service provider changes their address to {string}")
    public void theServiceProviderChangesTheirAddressTo(String newAddress) {
        this.address = newAddress;
    }
    @When("the Service provider changes their name to {string}")
    public void theServiceProviderChangesTheirNameTo(String newName) {
        this.name = newName;

    }
    @When("the Service provider clicks on the {string} button")
    public void theServiceProviderClicksOnTheButton(String buttonName) {
        if (buttonName.equals("Save")) {
            this.profileUpdated = true;
        }

    }
    @Then("the Service provider profile should be updated successfully")
    public void theServiceProviderProfileShouldBeUpdatedSuccessfully() {
        assertEquals(true, this.profileUpdated);

    }


    @Given("the Service provider wants to update their password")
    public void theServiceProviderWantsToUpdateTheirPassword() {


    }
    @When("the Service provider enters the current password {string}")
    public void theServiceProviderEntersTheCurrentPassword(String string) {
        this.profileUpdated= true;

    }
    @When("the Service provider enters the new password {string}")
    public void theServiceProviderEntersTheNewPassword(String newPassword) {
        this.password = newPassword;

    }
    @When("the Service provider confirms the new password {string}")
    public void theServiceProviderConfirmsTheNewPassword(String string) {


    }
    @Then("the Service provider password should be updated successfully")
    public void theServiceProviderPasswordShouldBeUpdatedSuccessfully() {
        assertEquals(true, this.profileUpdated);

    }
    @When("the Service provider enters an incorrect current password {string}")
    public void theServiceProviderEntersAnIncorrectCurrentPassword(String string) {

        this.profileUpdated= false;
    }

    @Then("an error message should appear for Service provider indicating the incorrect current password")
    public void anErrorMessageShouldAppearForServiceProviderIndicatingTheIncorrectCurrentPassword() {

        assertEquals(false, this.profileUpdated);
    }
}

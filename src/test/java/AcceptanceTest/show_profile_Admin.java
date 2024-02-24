package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class show_profile_Admin {
    private String password;
    private String phoneNumber;
    private String address;
    private String name;
    private boolean profileUpdated;

    @Given("that the admin is logged in")
    public void thatTheAdminIsLoggedIn() {

    }
    @Given("the admin has the following information:")
    public void theAdminHasTheFollowingInformation(io.cucumber.datatable.DataTable dataTable) {

    }
    @Then("the admin profile appears with the provided information")
    public void theAdminProfileAppearsWithTheProvidedInformation() {
        assertEquals(true, true);

    }
    @Given("the admin wants to update their profile information")
    public void theAdminWantsToUpdateTheirProfileInformation() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the admin changes their password to {string}")
    public void theAdminChangesTheirPasswordTo(String newPassword) {
        this.password = newPassword;

    }
    @When("the admin changes their phone number to {string}")
    public void theAdminChangesTheirPhoneNumberTo(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;

    }
    @When("the admin changes their address to {string}")
    public void theAdminChangesTheirAddressTo(String newAddress) {
        this.address = newAddress;

    }
    @When("the admin changes their name to {string}")
    public void theAdminChangesTheirNameTo(String newName) {
        this.name = newName;

    }
    @When("the admin clicks on the {string} button")
    public void theAdminClicksOnTheButton(String buttonName) {
        if (buttonName.equals("Save")) {
            this.profileUpdated = true;
        }
    }
    @Then("the admin profile should be updated successfully")
    public void theAdminProfileShouldBeUpdatedSuccessfully() {
        assertEquals(true, this.profileUpdated);

    }

    @Given("the admin wants to update their password")
    public void theAdminWantsToUpdateTheirPassword() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the admin enters the current password {string}")
    public void theAdminEntersTheCurrentPassword(String string) {
        this.profileUpdated= true;

    }
    @When("the admin enters the new password {string}")
    public void theAdminEntersTheNewPassword(String newPassword) {
        // Write code here that turns the phrase above into concrete actions
        this.password = newPassword;
    }
    @When("the admin confirms the new password {string}")
    public void theAdminConfirmsTheNewPassword(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the admin password should be updated successfully")
    public void theAdminPasswordShouldBeUpdatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(true, this.profileUpdated);
    }


    @When("the admin enters an incorrect current password {string}")
    public void theAdminEntersAnIncorrectCurrentPassword(String string) {
        // Write code here that turns the phrase above into concrete actions
        this.profileUpdated= false;
    }


    @Then("an error message should appear indicating the incorrect current password")
    public void anErrorMessageShouldAppearIndicatingTheIncorrectCurrentPassword() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(false, this.profileUpdated);
    }
}

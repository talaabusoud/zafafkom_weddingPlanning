package AcceptanceTest;

import entity.Admin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.Test_input;
import serveses.AppLogger;
import serveses.LoginToMyAppAsAdmin;
import java.util.logging.Logger;
import database.AdminDB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class show_profile_Admin {
    LoginToMyAppAsAdmin myApp;
    Admin admin;
    private static Logger logger = LoggerUtility.getLogger();
    private String newName;
    private String newPhone;
    private String newAddress;
    private String newPassword;
    boolean re_test;

    public show_profile_Admin() {
        myApp = new LoginToMyAppAsAdmin();
        myApp.login();

    }


    @Given("that the admin is logged in")
    public void thatTheAdminIsLoggedIn() {
        assertTrue(myApp.isLoggedIn());
        logger.info("\n");

    }
    @Then("the admin profile appears with the following information:")
    public void theAdminProfileAppearsWithTheFollowingInformation() {
        AdminDB.displayAdmin(admin);
    }


    @Given("the admin wants to update their profile information")
    public void theAdminWantsToUpdateTheirProfileInformation() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("the admin changes their {string} to {string}")
    public void theAdminChangesTheirTo(String field, String value) {
        switch (field) {
            case "name":
                if(Test_input.Name(value)) {
                    re_test = true;
                    newName=value;
                } else {
                    re_test = false;
                    logger.warning("Invalid name format.");
                }
                break;
            case "phone number":
                if(Test_input.Phone(value)) {
                    re_test = true;
                    newPhone=value;
                } else {
                    re_test = false;
                    logger.warning("Invalid phone number format.");
                }
                break;
            case "address":
                if(Test_input.Name(value)) {
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

    @When("the admin clicks on the {string} button")
    public void theAdminClicksOnTheButton(String button) {
        if (button.equals("Save") && admin != null){
            if(re_test==true){
                admin.setName(newName);
                admin.setPhone(newPhone);
                admin.setAddress(newAddress);
                admin.setPassword(newPassword);
            }
        }
        else System.out.println(button+"\t"+admin);

    }
    @Then("the admin profile should be updated successfully")
    public void theAdminProfileShouldBeUpdatedSuccessfully() {
       assertEquals(true,re_test);
    }



    @Then("the admin should see {string}")
    public void theAdminShouldSee(String message) {
        if (message.equals("Profile updated successfully")) {

            logger.warning( message);

        } else {
            logger.warning("Unexpected message: " + message);
        }
    }

}

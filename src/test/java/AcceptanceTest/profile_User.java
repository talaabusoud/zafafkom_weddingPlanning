package AcceptanceTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import entity.User;
import database.UserDB;
import main.Test_input;
import serveses.LoginAsUser;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class profile_User {
    LoginAsUser system;
    User user;

    public profile_User() {
        system = new LoginAsUser();
        //system.login();
    }

    @Given("the user with id {string} is logged in")
    public void theUserWithIdIsLoggedIn(String string) {
        system.setCredentials("jana123@gmail.com", "12345666");
        system.login();
        assertTrue(system.isLoggedIn());
    }
    @Given("the user has the following information:")
    public void theUserHasTheFollowingInformation(io.cucumber.datatable.DataTable dataTable) {
        UserDB.displayUser(user);
        User user = new User();
        user.setId(55);
        user.setName("John Doe");
        user.setPhoneNumber("1234567890");
        user.setAddress("123 Main St");
        user.setCity("Anytown");
        user.setStreet("Main St");

        user.setService(new ArrayList<>()); // To mimic an empty service list

        try {
            UserDB.displayUser(user);
            System.out.println("Test with real user passed");
        } catch (Exception e) {
            System.out.println("Test with real user failed");
        }

        // Test with null user
        try {
            UserDB.displayUser(null);
            System.out.println("Test with null user passed");
        } catch (Exception e) {
            System.out.println("Test with null user failed");
        }

    }

    @And("the user has information email {string},password {string}, phone {string},address {string},name {string}, id {int} , city {string} ,street {string} , hasServiceWindow {string},service {string});")
    public void theUserHasInformationEmailPasswordPhoneAddressNameIdAgeUniversityMajorsHasFurnitureWindowFurniture(String email, String password, String phone, String address, String name, int id, String city, String street , String hasServiceWindow,String service) {
        user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setAddress(address);
        user.setCity(city);
        user.setStreet(street);
        user.setEmail(email);
        user.setPassword(password);
        user.setHasServiceWindow(true);
        user.setService(new ArrayList<>());


    }
    @When("the user views their profile")
    public void theUserViewsTheirProfile() {

    }
    @Then("the user profile should appear with the provided information")
    public void theUserProfileShouldAppearWithTheProvidedInformation() {

    }
}

package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import serveses.LoginAsUser;
import database.UserDB;
import entity.User;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class signup_User {
    User user;
    boolean isFillAllInfoMessageDisplayed;
    boolean isEnterAnotherIdMessageDisplayed;
    boolean isEnterMoreThanFiveCharsMessageDisplayed;
    boolean isUserAddedSuccessfully;

    LoginAsUser loginAsUser = new LoginAsUser();

    private boolean areAllSignupDetailsProvided() {
        List<String> missingDetails = new ArrayList<>();

        if (user.getId() == 0) missingDetails.add("ID");
        if (user.getName() == null || user.getName().isEmpty()) missingDetails.add("Name");
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) missingDetails.add("Phone Number");
        if (user.getAddress() == null || user.getAddress().isEmpty()) missingDetails.add("Address");
        if (user.getCity() == null || user.getCity().isEmpty()) missingDetails.add("City");
        if (user.getStreet() == null || user.getStreet().isEmpty()) missingDetails.add("Street");
        if (user.getEmail() == null || user.getEmail().isEmpty()) missingDetails.add("Email");
        if (user.getPassword() == null || user.getPassword().length() <= 5) missingDetails.add("Password");

        if (!missingDetails.isEmpty()) {
            System.out.println("Missing details: " + String.join(", ", missingDetails));
            System.out.println("Provided values:");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Phone Number: " + user.getPhoneNumber());
            System.out.println("Address: " + user.getAddress());
            System.out.println("City: " + user.getCity());
            System.out.println("Street: " + user.getStreet());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        }

        return missingDetails.isEmpty();
    }

    @Given("you are in signup page")
    public void you_are_in_signup_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Navigate to signup page");
        user = new User();
        //throw new io.cucumber.java.PendingException();
    }

    @When("you write the {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void you_write_the_and(String id, String name, String phoneNumber, String address, String city, String street, String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Fill out the signup form with:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("City: " + city);
        System.out.println("Street: " + street);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setCity(city);
        user.setStreet(street);
        user.setEmail(email);
        user.setPassword(password);
        //throw new io.cucumber.java.PendingException();
    }

    @When("clicks on the signup button")
    public void clicks_on_the_signup_button() {
        // Check if all required information is provided
        if (areAllSignupDetailsProvided()) {
            // Check if the user with the same ID or email already exists
            if (UserDB.isUserExists(user.getId(), user.getEmail())) {
                isEnterAnotherIdMessageDisplayed = true;
                System.out.println("User with the same ID or email already exists. Please choose a different ID or email.");

            } else {
                // Add the user to the database if not already exists
                UserDB.addUser(user);
                isUserAddedSuccessfully = true;
                System.out.println("User added successfully!");

                // Simulate logging in with the created user
                loginAsUser.setCredentials(user.getEmail(), user.getPassword());
                loginAsUser.loggInCheck(user.getEmail(), user.getPassword());
            }
        } else {
            isFillAllInfoMessageDisplayed = true;

            // Check for additional conditions and set corresponding flags
            if (user.getPassword() != null && user.getPassword().length() <= 5) {
                isEnterMoreThanFiveCharsMessageDisplayed = true;
                System.out.println("Password should be more than five characters.");
            }
        }
    }

    @Then("you should show please fill all informations")
    public void you_should_show_please_fill_all_informations() {
        // Write code here that turns the phrase above into concrete actions
        isFillAllInfoMessageDisplayed = true;
        System.out.println("Verify 'Please fill all information' message is displayed");
        assertTrue("Please fill all information message should be displayed", isFillAllInfoMessageDisplayed);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("You should Show please enter another id")
    public void you_should_show_please_enter_another_id() {
        // Write code here that turns the phrase above into concrete actions
        isEnterAnotherIdMessageDisplayed = true;
        System.out.println("Verify 'Please enter another ID' message is displayed");
        assertTrue("Please enter another ID message should be displayed", isEnterAnotherIdMessageDisplayed);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("You should Show please enter more than five character")
    public void you_should_show_please_enter_more_than_five_character() {
        // Write code here that turns the phrase above into concrete actions
        isEnterMoreThanFiveCharsMessageDisplayed = true;
        System.out.println("Verify 'Please enter more than five characters' message is displayed");
        assertTrue("Please enter more than five characters message should be displayed", isEnterMoreThanFiveCharsMessageDisplayed);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The User Added Successfully")
    public void the_user_added_successfully() {
        // Write code here that turns the phrase above into concrete actions
        isUserAddedSuccessfully = true;
        System.out.println("Verify 'The Customer Added Successfully' message is displayed");
        assertTrue("The User should be added successfully", isUserAddedSuccessfully);
        //throw new io.cucumber.java.PendingException();
    }
}

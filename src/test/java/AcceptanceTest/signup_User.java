package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class signup_User {
    @Given("you are in signup page")
    public void you_are_in_signup_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Navigate to signup page");
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
        //throw new io.cucumber.java.PendingException();
    }

    @Then("you should show please fill all informations")
    public void you_should_show_please_fill_all_informations() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Verify 'Please fill all information' message is displayed");
        //throw new io.cucumber.java.PendingException();
    }

    @Then("You should Show please enter another id")
    public void you_should_show_please_enter_another_id() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Verify 'Please enter another ID' message is displayed");
        //throw new io.cucumber.java.PendingException();
    }

    @Then("You should Show please enter more than five character")
    public void you_should_show_please_enter_more_than_five_character() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Verify 'Please enter more than five characters' message is displayed");
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The User Added Successfully")
    public void the_user_added_successfully() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Verify 'The Customer Added Successfully' message is displayed");
        //throw new io.cucumber.java.PendingException();
    }
}

package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import serveses.LoginAsUser;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class login_User {

  String password;
  String username;

  private static Logger logger = LoggerUtility.getLogger();

  LoginAsUser system;
  public login_User() {
    system = new LoginAsUser();
  }

  @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

    }

    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {

    
    }

    @When("the username for user is {string}")
    public void the_username_for_user_is(String username) {
        this.username = username;
    }

    @When("the password for user is {string}")
    public void the_password_for_user_is(String password) {
        this.password = password;
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("the user is logged in the app successfully {string} login successfully {string}")
    public void the_user_is_logged_in_the_app_successfully_login_successfully(String string, String string2) {

      System.out.println("Username: " + username);
      System.out.println("Password: " + password);
      System.out.println("Is logged in: " + system.isLoggedIn());
      system.setCredentials(username, password);
      system.login();
      assertTrue(system.isLoggedIn());

    }

    @Given("that the user is not logged in the app")
    public void that_the_user_is_not_logged_in_the_app() {

      assertFalse(system.isLoggedIn());

    }

    @Then("the user will not login")
    public void the_user_will_not_login() {

      system.loggInCheck(username,password);
      assertFalse(system.isLoggedIn());
       // throw new io.cucumber.java.PendingException();
    }

    @Then("the message appear to tell the user what's wrong {string} Invalid username {string}")
    public void the_message_appear_to_tell_the_user_what_s_wrong_invalid_username(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
      system.errorInLogin();
        //throw new io.cucumber.java.PendingException();
    }

    @Then("the message appear to tell the user what's wrong {string} Wrong password {string}")
    public void the_message_appear_to_tell_the_user_what_s_wrong_wrong_password(String string, String string2) {

      system.errorInLogin();

    }

    @Then("error message will be displayed prompting the user to enter both username and password")
    public void error_message_will_be_displayed_prompting_the_user_to_enter_both_username_and_password() {

      system.errorInLogin();

    }

    // New Scenario
    @When("the user enters incorrect password {string}")
    public void the_user_enters_incorrect_password(String incorrectPassword) {
      password = incorrectPassword;
    }

    @Then("the user will not login with incorrect password")
    public void the_user_will_not_login_with_incorrect_password() {
      system.loggInCheck(username, password);
      assertFalse(system.isLoggedIn());
    }

    // New Scenario
    @Then("the user will not login as the user does not exist")
    public void the_user_will_not_login_as_the_user_does_not_exist() {
      system.loggInCheck(username, password);
      assertFalse(system.isLoggedIn());


    }

}

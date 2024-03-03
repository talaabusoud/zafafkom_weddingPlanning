package AcceptanceTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import entity.User;
import database.UserDB;
import serveses.LoginAsUser;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class editAccount_User {
    LoginAsUser system;
    User user;

    public editAccount_User(){
        system = new LoginAsUser();
        system.login();
    }

    @When("the user modifies his information with new values:")
    public void the_user_modifies_his_information_with_new_values(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> userData = dataTable.asMaps(String.class, String.class);

        // Assuming there's only one row of data
        Map<String, String> newUserInfo = userData.get(0);

        // Update user object with new information
        user.setName(newUserInfo.get("name"));
        user.setPhoneNumber(newUserInfo.get("PhoneNumber"));
        user.setAddress(newUserInfo.get("address"));
        user.setCity(newUserInfo.get("city"));
        user.setStreet(newUserInfo.get("street"));
        user.setEmail(newUserInfo.get("email"));
        user.setPassword(newUserInfo.get("password"));

        // Update user information in the system
        UserDB.updateUser(user);
        System.out.println("User is modifying information with new values: " + dataTable.asList());
        //throw new io.cucumber.java.PendingException();
    }

    @When("the user ensures the modification is without errors")
    public void the_user_ensures_the_modification_is_without_errors() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(system.validateUserInformation(user));
        System.out.println("User ensures the modification is without errors.");
    }

    @Then("the user's information is successfully updated")
    public void the_user_s_information_is_successfully_updated() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        UserDB.updateUser(user);
        System.out.println("User's information is successfully updated.");
    }

    @When("the user wants to delete his account")
    public void the_user_wants_to_delete_his_account() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        System.out.println("User wants to delete his account.");
    }

    @When("provides the credentials:")
    public void provides_the_credentials(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        //throw new io.cucumber.java.PendingException();
        //System.out.println("User provides the following credentials: " + dataTable.asList());
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);

        String username = credentials.get("username");
        String password = credentials.get("password");

        user = UserDB.getUserByUsernameAndPassword(username, password);
        System.out.println("User provides the following credentials: " + dataTable.asList());

    }

    @When("clicks the confirmation button")
    public void clicks_the_confirmation_button() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        System.out.println("User clicks the confirmation button.");

    }

    @Then("the account is successfully deleted from the system")
    public void the_account_is_successfully_deleted_from_the_system() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();System.out.println("Account is successfully deleted from the system.");
        System.out.println("Account is successfully deleted from the system.");
        assertFalse(system.isLoggedIn());
    }

    @And("the user's information is updated in the system")
    public void the_users_information_is_updated_in_the_system() {
        // Implement additional verification if needed
        // Replace the following line with your actual implementation
        assertTrue(system.validateUserInformation(user));
        System.out.println("User's information is updated in the system.");
    }

}

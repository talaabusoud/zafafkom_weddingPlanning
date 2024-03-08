package AcceptanceTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import entity.User;
import database.UserDB;
import serveses.LoginAsUser;

import java.util.ArrayList;

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
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @Given("the user has the following information:")
    public void theUserHasTheFollowingInformation(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        UserDB.displayUser(user);
        //throw new io.cucumber.java.PendingException();
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
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @Then("the user profile should appear with the provided information")
    public void theUserProfileShouldAppearWithTheProvidedInformation() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
}

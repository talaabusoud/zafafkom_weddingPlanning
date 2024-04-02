package AcceptanceTest;

import entity.Reserve;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import entity.User;
import database.UserDB;
import serveses.LoginAsUser;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class editAccount_User {
    LoginAsUser system;
    User user;
    private Reserve reservation;
    public editAccount_User(){
        system = new LoginAsUser();
        system.login();
        user = new User();

        reservation = new Reserve(); // Assuming Reserve has a default constructor
        reservation.setServiceId(1);
    }

    @When("the user modifies his information with new values:")
    public void the_user_modifies_his_information_with_new_values(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps(String.class, String.class);
        if (!userData.isEmpty()) {
            Map<String, String> newUserInfo = userData.get(0);

            user.setName(newUserInfo.get("Name"));
            user.setPhoneNumber(newUserInfo.get("PhoneNumber"));
            user.setAddress(newUserInfo.get("Address"));
            user.setCity(newUserInfo.get("City"));
            user.setStreet(newUserInfo.get("Street"));
            user.setEmail(newUserInfo.get("Email"));
            user.setPassword(newUserInfo.get("Password"));

            assertEquals(newUserInfo.get("City"), user.getCity());
            assertEquals(newUserInfo.get("Address"), user.getAddress());
            assertEquals(newUserInfo.get("Street"), user.getStreet());

            User user1 = new User();
            user1.setId(20);
            user1.setEmail("test@example.com");
            UserDB.addUser(user1);
            assertTrue(UserDB.isUserExists(20, "nonexistent@example.com"));
            assertTrue(UserDB.isUserExists(-1, "test@example.com"));
        } else {
            throw new IllegalArgumentException("No data provided for user modification.");
        }

    }

    @When("the user ensures the modification is without errors")
    public void the_user_ensures_the_modification_is_without_errors() {

        assertTrue(system.validateUserInformation(user));
        System.out.println("User ensures the modification is without errors.");
    }

    @Then("the user's information is successfully updated")
    public void the_user_s_information_is_successfully_updated() {
        UserDB.updateUser(user);
        System.out.println("User's information is successfully updated.");



        List<Reserve> reservations = user.getReservations();
        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());

        user.addReservation(reservation);
        assertThat(user.getReservations(), hasItem(reservation));
        user.addReservation(reservation);
        assertTrue(user.hasReservedService(1));
        user.addReservation(reservation);
        assertFalse(user.hasReservedService(2));

        UserDB.updateUser(user);
        System.out.println("User is modifying information with new values: " + user);

    }


    @When("provides the credentials:")
    public void provides_the_credentials(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);
        String username = credentials.get("username");
        String password = credentials.get("password");

}
    @When("clicks the confirmation button")
    public void clicks_the_confirmation_button() {
        System.out.println("User clicks the confirmation button.");

    }

    @And("the user's information is updated in the system")
    public void the_users_information_is_updated_in_the_system() {
        assertTrue(system.validateUserInformation(user));
        System.out.println("User's information is updated in the system.");
    }

}

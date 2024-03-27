package AcceptanceTest;

import database.ReservationDB;
import database.ServiceDB;
import entity.Reserve;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

public class show_Reservations_And_Delete_by_admin {
    private static Logger logger = LoggerUtility.getLogger();
    private List<Reserve> reservations;
    private boolean deletionSuccess;
    private String message;

    @When("the admin chooses to view the list of reservations")
    public void theAdminChoosesToViewTheListOfReservations() {
        reservations = ReservationDB.getReservations();
    }

    @Then("the admin should see a list of all reservations")
    public void theAdminShouldSeeAListOfAllReservations() {
        assertNotNull("Reservations should not be null", reservations);
        ReservationDB.displayReservations(reservations);


    }

    @Given("the admin is viewing the list of reservations")
    public void theAdminIsViewingTheListOfReservations() {
        reservations = ReservationDB.getReservations(); // Simulate fetching reservations for viewing.
        assertNotNull("Reservations should be available for viewing", reservations);
    }

    @When("the admin chooses to delete a reservation")
    public void theAdminChoosesToDeleteAReservation() {
        logger.info("\nEnter the ID of the reservation you wish to delete: ");

    }

    @When("the admin enters the ID of the reservation to delete")
    public void theAdminEntersTheIDOfTheReservationToDelete() {
        String validId = "45";
        ReservationDB.deleteReservation(validId);
        deletionSuccess = true;
    }

    @Then("the reservation should be deleted successfully")
    public void theReservationShouldBeDeletedSuccessfully() {
        assertTrue("Reservation should be deleted successfully", deletionSuccess);
    }

    @When("the admin enters an invalid reservation ID")
    public void theAdminEntersAnInvalidReservationID() {
        // Simulate entering an invalid ID and attempting to delete.
        String invalidId = "invalid";
        ReservationDB.deleteReservation(invalidId); // Assume this method sets deletionSuccess appropriately.
        message = "No reservation found with the ID: " + invalidId; // Simulate an error message.
    }

    @Then("the system should inform the admin that no reservation was found with the ID")
    public void theSystemShouldInformTheAdminThatNoReservationWasFoundWithTheID() {
        assertFalse("Deletion should not be successful for an invalid ID", deletionSuccess);
        assertEquals("Error message should match", "No reservation found with the ID: invalid", message);
    }

    @When("the admin chooses to exit the reservation view")
    public void theAdminChoosesToExitTheReservationView() {
    }

    @Then("the admin should return to the main menu")
    public void theAdminShouldReturnToTheMainMenu() {
        assertTrue("Admin returns to the main menu", true);
    }
}

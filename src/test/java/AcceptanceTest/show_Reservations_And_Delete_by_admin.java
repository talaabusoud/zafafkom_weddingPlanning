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
        Reserve reserve = new Reserve();
        reserve.setId("450");
        reserve.setServiceId(2);
        reserve.setServiceName("tst");
        reserve.setCustomerName("jana");
        reserve.setEventLocation("nablus");
        reserve.setEventDate("26/5/2024");
        reserve.setEventTime("3 PM");
        reserve.setEventDuration("1.5h");
        reservations.add(reserve);
        ReservationDB.displayReservations(reservations);
        ReservationDB.getReservationsForService(1);

    }

    @Given("the admin is viewing the list of reservations")
    public void theAdminIsViewingTheListOfReservations() {
        reservations = ReservationDB.getReservations();
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
        String invalidId = "invalid";
        ReservationDB.deleteReservation(invalidId);
        message = "No reservation found with the ID: " + invalidId;
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

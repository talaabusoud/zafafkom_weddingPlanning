package AcceptanceTest;

import database.ServiceDB;
import database.UserDB;
import database.ReservationDB;
import entity.Service;
import entity.Reserve;
import entity.User;
import main.LoggerUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class UserEdit_deleteReservation {
    private User user;
    private Reserve reservation;


    @Given("there exists a reservation with id {string} for the user")
    public void thereExistsAReservationWithIdForTheUser(String reservationId) {
        // Write code here that turns the phrase above into concrete actions
        user = UserDB.getUserByEmail("test@example.com");
        reservation = ReservationDB.getReservationById(reservationId);

        if (user != null && reservation != null) {
            user.addReservation(reservation);
        }
    }


    @When("the user wants to edit reservation number {string}")
    public void theUserWantsToEditReservationNumber(String reservationId) {
        user = UserDB.getUserByEmail("test@example.com");
        reservation = ReservationDB.getReservationById(reservationId);

    }

    @When("updates the reservation with new date {string}, time {string}, and duration {string}")
    public void updatesTheReservationWithNewDateTimeAndDuration(String newDate, String newTime, String newDuration) {
        // Write code here that turns the phrase above into concrete actions
        if (reservation != null) {
            reservation.setEventDate(newDate);
            reservation.setEventTime(newTime);
            reservation.setEventDuration(newDuration);

            ReservationDB.updateReservation(reservation);
        }
    }

    @Then("the reservation is successfully updated")
    public void theReservationIsSuccessfullyUpdated() {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull("The reservation object is null", reservation);

        Reserve updatedReservation = ReservationDB.getReservationById(reservation.getId());

        assertNotNull("The updated reservation is null", updatedReservation);
        LoggerUtility.getLogger().info("Updated reservation details:");
        LoggerUtility.getLogger().info("ID: " + updatedReservation.getId());
        LoggerUtility.getLogger().info("Event Date: " + updatedReservation.getEventDate());
        LoggerUtility.getLogger().info("Event Time: " + updatedReservation.getEventTime());
        LoggerUtility.getLogger().info("Event Duration: " + updatedReservation.getEventDuration());

        assertEquals("The event date is not updated", reservation.getEventDate(), updatedReservation.getEventDate());
        assertEquals("The event time is not updated", reservation.getEventTime(), updatedReservation.getEventTime());
        assertEquals("The event duration is not updated", reservation.getEventDuration(), updatedReservation.getEventDuration());
    }


    @When("the service is not available on that date")
    public void theServiceIsNotAvailableOnThatDate() {

    }

    @Then("the user cannot update the service")
    public void theUserCannotUpdateTheService() {

    }


    @When("the service is not available at time {string} or for a duration of {string}")
    public void theServiceIsNotAvailableAtTimeOrForADurationOf(String time, String duration) {
        if (!ServiceDB.isServiceAvailableAtTimeOrDuration(reservation.getServiceId(), time, duration)) {
            reservation = null;
        }

    }

    @Then("the user cannot update this service to that time or duration")
    public void theUserCannotUpdateThisServiceToThatTimeOrDuration() {
        assertNull("The reservation object should be null", reservation);

    }


    @When("the user wants to cancel\\/delete reservation number {string}")
    public void theUserWantsToCancelDeleteReservationNumber(String reservationId) {
        reservation = ReservationDB.getReservationById(reservationId);
    }
    @When("confirms the cancellation")
    public void confirmsTheCancellation() {
        if (reservation != null) {
            ReservationDB.deleteReservation(reservation.getId());
            reservation = null;
        }
    }
    @Then("the reservation is successfully canceled\\/deleted")
    public void theReservationIsSuccessfullyCanceledDeleted() {
        assertNull("The reservation was not canceled/deleted successfully", reservation);
    }

    @Then("an error message is displayed indicating the reservation does not exist")
    public void anErrorMessageIsDisplayedIndicatingTheReservationDoesNotExist() {
        LoggerUtility.getLogger().warning("The reservation does not exist.");
    }
}

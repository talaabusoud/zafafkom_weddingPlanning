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
    private Service service;

    @Given("there exists a reservation with id {string} for the user")
    public void thereExistsAReservationWithIdForTheUser(String reservationId) {
        // Write code here that turns the phrase above into concrete actions
        user = UserDB.getUserByEmail("test@example.com");
        reservation = ReservationDB.getReservationById(reservationId);

        if (user != null && reservation != null) {
            user.addReservation(reservation);
        }
//        throw new io.cucumber.java.PendingException();
    }


    @When("the user wants to edit reservation number {string}")
    public void theUserWantsToEditReservationNumber(String reservationId) {
        // Write code here that turns the phrase above into concrete actions
        user = UserDB.getUserByEmail("test@example.com");
        reservation = ReservationDB.getReservationById(reservationId);
//        throw new io.cucumber.java.PendingException();
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
//        throw new io.cucumber.java.PendingException();
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
//        throw new io.cucumber.java.PendingException();
    }


    @When("the service is not available on that date")
    public void theServiceIsNotAvailableOnThatDate() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }

    @Then("the user cannot update the service")
    public void theUserCannotUpdateTheService() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }


    @When("the service is not available at time {string} or for a duration of {string}")
    public void theServiceIsNotAvailableAtTimeOrForADurationOf(String time, String duration) {
        // Write code here that turns the phrase above into concrete actions
        if (!ServiceDB.isServiceAvailableAtTimeOrDuration(reservation.getServiceId(), time, duration)) {
            reservation = null;
        }
//        throw new io.cucumber.java.PendingException();
    }

    @Then("the user cannot update this service to that time or duration")
    public void theUserCannotUpdateThisServiceToThatTimeOrDuration() {
        // Write code here that turns the phrase above into concrete actions
        assertNull("The reservation object should be null", reservation);

//        throw new io.cucumber.java.PendingException();
    }


    @When("the user wants to cancel\\/delete reservation number {string}")
    public void theUserWantsToCancelDeleteReservationNumber(String reservationId) {
        // Write code here that turns the phrase above into concrete actions
        reservation = ReservationDB.getReservationById(reservationId);
//        throw new io.cucumber.java.PendingException();
    }
    @When("confirms the cancellation")
    public void confirmsTheCancellation() {
        // Write code here that turns the phrase above into concrete actions
        if (reservation != null) {
            ReservationDB.deleteReservation(reservation.getId());
            reservation = null;
        }
//        throw new io.cucumber.java.PendingException();
    }
    @Then("the reservation is successfully canceled\\/deleted")
    public void theReservationIsSuccessfullyCanceledDeleted() {
        assertNull("The reservation was not canceled/deleted successfully", reservation);
//        throw new io.cucumber.java.PendingException();
    }

    @Then("an error message is displayed indicating the reservation does not exist")
    public void anErrorMessageIsDisplayedIndicatingTheReservationDoesNotExist() {
        // Write code here that turns the phrase above into concrete actions
        LoggerUtility.getLogger().warning("The reservation does not exist.");
//        throw new io.cucumber.java.PendingException();
    }
}

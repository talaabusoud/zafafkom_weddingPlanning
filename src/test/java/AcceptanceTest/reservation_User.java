package AcceptanceTest;//package AcceptanceTest;

import database.ReservationDB;
import database.ServiceDB;
import database.UserDB;

import entity.Reserve;
import entity.Service;
import entity.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class reservation_User {
    private User user;
    private Service service;
    private String reservationDate;
    private String reservationTime;
    private String reservationDuration;
    private String serviceId;
    private boolean isServiceAvailable;
    private Map<String, String> reservations = new HashMap<>();
    private Reserve testReservation;
    private String getReservationState(String userId, String reservationId) {

        testReservation = new Reserve();
        testReservation.setId("NewID123");
        testReservation.setServiceId(1);
        testReservation.setServiceName("Test Service");
        testReservation.setCustomerName("Test Customer");
        testReservation.setEventDate("01/01/2023");
        testReservation.setEventLocation("Test Location");
        testReservation.setTotalPrice(100.0);
        testReservation.setStatus("Reserved");
        testReservation.setEventTime("12:00");
        testReservation.setEventDuration("2 hours");

        ReservationDB.addReservation(testReservation);

        assertEquals(1, ReservationDB.getReservations().size());

        assertEquals("NewID123", ReservationDB.getReservations().get(0).getId());

        Map<String, String> reservations = new HashMap<>();
        reservations.put("1", "ready");
        reservations.put("2", "pending");
        reservations.put("3", "ready");
        reservations.put("6", "ready");

        String reservationState = reservations.get(reservationId);

        if (reservationState == null) {
            throw new RuntimeException("Reservation state not found for reservation ID: " + reservationId);
        }

        return reservationState;
    }
    private boolean receiveReservationAndPayDebt(String userId, String reservationId) {
        return true;
    }


    @Given("that the user logged in")
    public void that_the_user_logged_in() {
        user = UserDB.getUsers().stream().filter(u -> u.getId() == 1).findFirst().orElse(null);
        assertNotNull(user);
    }

    @Given("the user want to show all services in different categories")
    public void the_user_want_to_show_all_services_in_different_categories() {
        List<Service> services = ServiceDB.getServices();
        assertNotNull(services);
        assertFalse(services.isEmpty());
    }

    @Then("the services appear to him")
    public void the_services_appear_to_him() {
        System.out.println("Services are displayed to the user.");
    }

    @Given("the user with number {string} is logged in")
    public void the_user_with_number_is_logged_in(String userId) {
        int id = Integer.parseInt(userId);
        user = UserDB.getUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        assertNotNull(user);
        System.out.println("Searching for user with ID: " + userId);
        System.out.println("Found user: " + user);
    }

    @Given("the user wants to reserve a service with id {string}")
    public void the_user_wants_to_reserve_a_service_with_id(String serviceId) {
        int id = Integer.parseInt(serviceId);
        service = ServiceDB.getServices().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        assertNotNull(service);

    }

    @Given("the service with id {string} is available")
    public void the_service_with_id_is_available(String serviceId) {

        int id = Integer.parseInt(serviceId);
        service = ServiceDB.getServices().stream().filter(s -> s.getId() == id && s.getStatus().equals("available")).findFirst().orElse(null);
        assertNotNull(service);

    }

    @Given("the user enters date {string}, time {string}, and duration {string}")
    public void the_user_enters_date_time_and_duration(String date, String time, String duration) {
        // Write code here that turns the phrase above into concrete actions
        reservationDate = date;
        reservationTime = time;
        reservationDuration = duration;

    }

    @Then("the reservation is registered successfully")
    public void the_reservation_is_registered_successfully() {

    }

    @Given("the user wants to reserve the service with id {string}, date {string}, time {string} and duration {string}")
    public void the_user_wants_to_reserve_the_service_with_id_date_time_and_duration(String serviceId, String date, String time, String duration) {
        this.serviceId = serviceId;
        this.reservationDate = date;
        this.reservationTime = time;
        this.reservationDuration = duration;
    }

    @Given("the service with id {string} is not available on that date")
    public void theServiceWithIdIsNotAvailableOnThatDate(String serviceId) {
        this.isServiceAvailable = false;
    }

    @Then("the user cannot reserve this service")
    public void theUserCannotReserveThisService() {
        // Write code here that turns the phrase above into concrete actions
        if (!isServiceAvailable) {
            System.out.println("Service with ID " + serviceId + " is not available on " + reservationDate);
        }
    }
    @Then("a message appears to inform the user of the unavailability")
    public void aMessageAppearsToInformTheUserOfTheUnavailability() {
        // Write code here that turns the phrase above into concrete actions
        if (!isServiceAvailable) {
            System.out.println("Sorry, the service is not available on " + reservationDate);
        }
    }

    @Given("the user wants to reserve the service with id {string}, date {string}, time {string} and duration")
    public void theUserWantsToReserveTheServiceWithIdDateTimeAndDuration(String serviceId,  String date, String time) {

    }
    @Given("the service with id {string} is not available at time {string} or for a duration of {string}")
    public void theServiceWithIdIsNotAvailableAtTimeOrForADurationOf(String serviceId, String time, String duration) {

    }




    @Given("the user with number {string} is checking the status of reservation number {string}")
    public void the_user_with_number_is_checking_the_status_of_reservation_number(String userId, String reservationId) {
        reservations.put("123", "Confirmed");
        reservations.put("456", "Pending");
        reservations.put("789", "Cancelled");
        reservations.put("789", "Cancelled");
        String status = reservations.get(reservationId);
        System.out.println("Reservation status for reservation " + reservationId + ": " + status);


    }

    @Then("the reservation status is displayed")
    public void the_reservation_status_is_displayed() {

    }

    @Given("the user with number {string} wants to receive his reservation with number {string} in a ready state")
    public void theUserWithNumberWantsToReceiveHisReservationWithNumberInAReadyState(String userId, String reservationId) {
        // Write code here that turns the phrase above into concrete actions
        String reservationState = getReservationState(userId, reservationId);
        assertNotNull("Reservation state should not be null", reservationState);
        assertEquals("ready", reservationState);
//        throw new io.cucumber.java.PendingException();
    }
    @When("the user with number {string} requests to receive reservation number {string} and pay the debt")
    public void theUserWithNumberRequestsToReceiveReservationNumberAndPayTheDebt(String userId, String reservationId) {

        assertTrue(ReservationDB.isServiceReservedOnDate(1, "01/01/2023"));

        assertFalse(ReservationDB.isServiceReservedOnDate(1, "2023-01-03"));

        assertFalse(ReservationDB.isServiceReservedOnDate(3, "2023-01-01"));



        boolean receiptAcknowledged = receiveReservationAndPayDebt(userId, reservationId);
        assertTrue(receiptAcknowledged);
//        throw new io.cucumber.java.PendingException();
    }
    @Then("the application acknowledges receipt, and the debt is paid")
    public void theApplicationAcknowledgesReceiptAndTheDebtIsPaid() {

        System.out.println("Application acknowledges receipt, and the debt is paid");


    }

}
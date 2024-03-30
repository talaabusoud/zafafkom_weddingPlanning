package database;
import entity.Reserve;
import main.LoggerUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReservationDB {


    private String id;
     static String temp;
    private static final Logger logger = LoggerUtility.getLogger();
    private static List<Reserve> reservations = new ArrayList<>();

    static{
        Reserve reserve = new Reserve();
        reserve.setId("45");
        reserve.setServiceId(1);
        reserve.setServiceName("tst");
        reserve.setCustomerName("jana");
        reserve.setEventLocation("nablus");
        reserve.setEventDate("26/5/2024");
        reserve.setEventTime("3 PM");
        reserve.setEventDuration("1.5h");
        reservations.add(reserve);
    }
    private ReservationDB() {
        throw new IllegalStateException("Utility class");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void addReservation(Reserve reservation) {
        reservations.add(reservation);
    }

    public static List<Reserve> getReservations() {
        return reservations;
    }

    public static Reserve getReservationById(String reservationId) {
        for (Reserve reservation : reservations) {
            if (reservation.getId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;// no matching
    }

    public static void updateReservation(Reserve reservation) {
        for (Reserve existingReservation : reservations) {
            // Check if the IDs match
            if (existingReservation.getId().equals(reservation.getId())) {
                // Update
                existingReservation.setEventDate(reservation.getEventDate());
                existingReservation.setEventTime(reservation.getEventTime());
                existingReservation.setEventDuration(reservation.getEventDuration());
                existingReservation.setEventLocation(reservation.getEventLocation());
                existingReservation.setTotalPrice(reservation.getTotalPrice());
                existingReservation.setStatus(reservation.getStatus());

                LoggerUtility.getLogger().info("Reservation updated: " + reservation.getId());

                return;
            }
        }
        LoggerUtility.getLogger().warning("No reservation found for update: " + reservation.getId());
    }

    public static List<Reserve> getConflictingReservations(int serviceId, String time, String duration) {
        List<Reserve> conflictingReservations = new ArrayList<>();
        for (Reserve reservation : reservations) {
            // Check if the reservation conflicts with the specified time and duration
            if (reservation.getServiceId() == serviceId &&
                    reservation.getEventTime().equals(time) &&
                    reservation.getEventDuration().equals(duration)) {
                conflictingReservations.add(reservation);
            }
        }
        return conflictingReservations;
    }

    public static void deleteReservation(String reservationId) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(reservationId)) {
                reservations.remove(i);
                 temp = "\nReservation deleted: " + reservationId;
                LoggerUtility.getLogger().info(temp);
                return;
            }
        }
        temp= "\nNo reservation found for deletion: " + reservationId;
        LoggerUtility.getLogger().warning(temp);
    }

    public static void displayReservations(List<Reserve> reservations) {

        String leftAlignFormat = "| %-5s | %-15s | %-10s | %-15s | %-10s | %-10s |\n";

String line = "+-------+-----------------+------------+-----------------+------------+------------+\n";
        logger.info(line);
        logger.info("| ID    | Service Name    | Service ID | Customer Name   | Location   | Date       |\n");
        logger.info(line);

        for (Reserve reservation : reservations) {
            temp=String.format(leftAlignFormat,
                    reservation.getId(),
                    reservation.getServiceName(),
                    reservation.getServiceId(),
                    reservation.getCustomerName(),
                    reservation.getEventLocation(),
                    reservation.getEventDate());
            logger.info(temp);
        }


        logger.info(line);
    }

    public static List<Reserve> getReservationsForService(int serviceId) {
        List<Reserve> reservationsForService = new ArrayList<>();
        for (Reserve reservation : reservations) {
            if (reservation.getServiceId() == serviceId) {
                reservationsForService.add(reservation);
            }
        }
        return reservationsForService;
    }
    public static boolean isServiceReservedOnDate(int serviceId, String date) {
        return reservations.stream()
                .anyMatch(reservation -> reservation.getServiceId() == serviceId &&
                        reservation.getEventDate().equals(date));
    }

}

package database;

import entity.Service;
import entity.Reserve;
import main.LoggerUtility ;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServiceDB {
    private static final List<Service> SERVICES = new ArrayList<>();

    private static final Logger logger = LoggerUtility.getLogger();

    private ServiceDB() {
        throw new IllegalStateException("Utility class");
    }

    public static void addService(Service service) {
        if (service != null) {
            SERVICES.add(service);
        } else {
            logger.warning("Ignoring null service addition");
        }
    }
    public static List<Service> getServices() {
        return SERVICES;
    }

    public static void deleteService(int id) {
        if (SERVICES != null) {
            SERVICES.removeIf(s -> s.getId() == id);
        }
    }

    public static void displayService(Service service) {
        if (service == null) {
            displaying();
            logger.warning("|This Service is not exist                                   |\n");
            logger.info("|__________________________________________________________|\n");
        } else {
            logger.info("Type: " + service.getType() + "\n"
                    + "Name: " + service.getName() + "\n"
                    + "Price: " + service.getPrice() + "\n"
                    + "Phone: " + service.getPhone() + "\n"
                    + "Image: " + service.getImage() + "\n");
            displaying();
            logger.info("|Reservations for this service:\n");
            displaying();
            if (service.getReservations() != null) {
                for (Reserve reservation : service.getReservations()) {
                    logger.info("Reservation ID: " + reservation.getId() + "\n"
                            + "Service Name: " + reservation.getServiceName() + "\n"
                            + "Customer Name: " + reservation.getCustomerName() + "\n"
                            + "Event Date: " + reservation.getEventDate() + "\n"
                            + "Event Location: " + reservation.getEventLocation() + "\n"
                            + "Total Price: " + reservation.getTotalPrice() + "\n"
                            + "Status: " + reservation.getStatus() + "\n");
                    displaying();
                }
            }
        }
    }

    public static void displayServices(List<Service> services) {
        if (services != null) {
            for (Service service : services) {
                displayService(service);
            }
        }
    }

    public static void displaying() {
        logger.info("------------------------------------------------------------\n");
    }
}

package database;

import entity.Service;
import entity.Reserve;
import entity.ServiceProvider;
import main.LoggerUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RequestToAddServiceDB {
    private static List<Service> services = new ArrayList<>();
    private static final Logger logger = LoggerUtility.getLogger();
    static ServiceProvider serviceProvider = new ServiceProvider();

    private RequestToAddServiceDB() {
        throw new IllegalStateException("Utility class");
    }

    public static void addService(Service service) {
        services.add(service);
    }

    public static List<Service> getServices() {
        return services;
    }

    public static void displayService(Service service) {
        logger.info("Type: " + service.getType() + "\n"
                + "Name: " + service.getName() + "\n"
                + "Price: " + service.getPrice() + "\n"
                + "Phone: " + service.getPhone() + "\n"
                + "Image: " + service.getImage() + "\n");

        display();
        logger.info("Reservations for this service:\n");
        for (Reserve reservation : service.getReservations()) {
            logger.info("Reservation ID: " + reservation.getId() + "\n"
                    + "Service Name: " + reservation.getServiceName() + "\n"
                    + "Customer Name: " + reservation.getCustomerName() + "\n"
                    + "Event Date: " + reservation.getEventDate() + "\n"
                    + "Event Location: " + reservation.getEventLocation() + "\n"
                    + "Total Price: " + reservation.getTotalPrice() + "\n"
                    + "Status: " + reservation.getStatus() + "\n");
            display();
        }
    }

    public static void displayServices(List<Service> services) {
        logger.info("------------Services------------\n");

        for (Service service : services) {
            displayService(service);
        }
    }

    public static void clearTheRequestList() {
        services.clear();
    }

    public static void cancelRequest(int id) {
        services.removeIf(service -> service.getId() == id);
    }

    public static void display() {
        logger.info("------------------------\n");
    }
}

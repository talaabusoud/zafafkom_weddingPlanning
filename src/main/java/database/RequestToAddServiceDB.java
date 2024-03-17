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
        String dataFormat = "| %-15d | %-10s | %-20s | %-10.2f | %-15s | %-30s |\n";
        logger.info(String.format(dataFormat,
                service.getId(),
                service.getType(),
                service.getName(),
                service.getPrice(),
                service.getPhone(),
                service.getImage()));
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+\n");
    }


    public static void displayServices(List<Service> services) {
        logger.info("-------------------------------------------------- Services ---------------------------------------------------------- \n");
        String headerFormat = "| %-15s | %-10s | %-20s | %-10s | %-15s | %-30s |\n";
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+\n");
        logger.info(String.format(headerFormat, "ID", "Type", "Name", "Price", "Phone", "Image"));
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+\n");

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

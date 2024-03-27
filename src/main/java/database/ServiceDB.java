package database;

import entity.Service;
import entity.Reserve;
import entity.ServiceProvider;
import main.LoggerUtility ;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServiceDB {
    private ServiceDB() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    static ServiceProvider serviceProvider1 =ServiceProviderDB.getServiceProviderById(1);
    static ServiceProvider serviceProvider2 = ServiceProviderDB.getServiceProviderById(2);

    private static final List<Service> SERVICES = new ArrayList<>();
    static {
        SERVICES.add(new Service(1, "Hebron", "Hall", "available", "Royal", 15000, serviceProvider1));
        SERVICES.add(new Service(2, "Bethlehem", "Food", "not available", "Himuni", 10000, serviceProvider2));
        SERVICES.add(new Service(3, "Jenin", "Dj", "not_available", "Himxni", 10000, serviceProvider2));

    }

    private static final Logger logger = LoggerUtility.getLogger();
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

    public static boolean deleteService(int id) {
        int initialSize = SERVICES.size();
        SERVICES.removeIf(s -> s.getId() == id);
        int newSize = SERVICES.size();
        return initialSize > newSize; // إرجاع true إذا تغير حجم القائمة (أي تم حذف عنصر)
    }

    public static void displayService(Service service) {
        // Ensure the dataFormat string includes all the necessary placeholders for the service details
        String dataFormat = "| %-5d | %-15s | %-10.2f | %-12s | %-15s | %-15s | %-30s | %-15s |\n";

        // Log the service details. Make sure to include the service's status and location in the output
        logger.info(String.format(dataFormat,
                service.getId(),          // Service ID
                service.getName(),        // Service Name
                service.getPrice(),       // Service Price
                service.getStatus(),      // Service Status
                service.getLocation(),    // Service Location
                service.getOwner().getName(), // Service Owner
                service.getImage(),       // Service Image URL
                service.getType()));      // Service Type

        // Header and Footer for better readability
        logger.info("+------+-----------------+----------+--------------+-----------------+-----------------+--------------------------------+-----------------+\n");
    }

    public static void displayServices(List<Service> services) {
        logger.info("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        String headerFormat = "| %-5s | %-15s | %-10s | %-12s | %-15s | %-15s | %-30s | %-15s |\n";
        logger.info(String.format(headerFormat, "ID", "Name", "Price", "Status", "Location", "Owner", "Image URL", "Type"));
        logger.info("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");

        for (Service service : services) {
            displayService(service);
        }
    }


    public static List<Service> getServicesByProvider(int providerId) {
        return SERVICES.stream()
                .filter(service -> service.getOwner().getId() == providerId)
                .collect(Collectors.toList());
    }

    public static void updateService(Service updatedService) {
        SERVICES.replaceAll(service -> service.getId() == updatedService.getId() ? updatedService : service);
    }

    public static Service getServiceById(int serviceId) {
        for (Service service : SERVICES) {
            if (service.getId() == serviceId) {
                return service;
            }
        }
        return null;
    }

    public static boolean isServiceAvailableAtTimeOrDuration(int serviceId, String time, String duration) {
        // Fetch the service from the database
        Service service = getServiceById(serviceId);
        if (service != null) {
            List<Reserve> conflictingReservations = ReservationDB.getConflictingReservations(serviceId, time, duration);
            return conflictingReservations.isEmpty(); // no conflicts, service is available
        }
        return false; // Service not found
    }

}

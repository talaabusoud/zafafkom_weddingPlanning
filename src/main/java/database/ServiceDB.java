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
    static ServiceProvider serviceProvider1 =ServiceProviderDB.getServiceProviderById(1);
    static ServiceProvider serviceProvider2 = ServiceProviderDB.getServiceProviderById(2);

    private static final List<Service> SERVICES = new ArrayList<>();
    static {
        SERVICES.add(new Service(1, "Hebron", "Hall", "available", "Royal", 15000, serviceProvider1));
        SERVICES.add(new Service(2, "Hebron", "Food", "available", "Himuni", 10000, serviceProvider2));
    }

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

    public static boolean deleteService(int id) {
        int initialSize = SERVICES.size();
        SERVICES.removeIf(s -> s.getId() == id);
        int newSize = SERVICES.size();
        return initialSize > newSize; // إرجاع true إذا تغير حجم القائمة (أي تم حذف عنصر)
    }


    public static void displayService(Service service) {
        String dataFormat = "| %-15d | %-10s | %-20s | %-10.2f | %-15s | %-30s | %-20s |\n";
        logger.info(String.format(dataFormat,
                service.getId(),
                service.getType(),
                service.getName(),
                service.getPrice(),
                service.getPhone(),
                service.getImage(),
                service.getOwner().getName()));
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+----------------------+\n");
    }

    public static void displayServices(List<Service> services) {
        logger.info("------------------------------------------------------ Services ------------------------------------------------------------------------------ \n");
        String headerFormat = "| %-15s | %-10s | %-20s | %-10s | %-15s | %-30s | %-20s |\n";
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+----------------------+\n");
        logger.info(String.format(headerFormat, "ID", "Type", "Name", "Price", "Phone", "Image", "Provider"));
        logger.info("+-----------------+------------+----------------------+------------+-----------------+--------------------------------+----------------------+\n");

        for (Service service : services) {
            displayService(service);
        }
    }
    public static List<Service> getServicesByProvider(int providerId) {
        return SERVICES.stream()
                .filter(service -> service.getOwner().getId() == providerId)
                .collect(Collectors.toList());
    }


    public static void displaying() {
        logger.info("------------------------------------------------------------\n");
    }
}

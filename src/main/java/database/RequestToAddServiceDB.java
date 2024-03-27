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
    public static void addService(Service service) {
        services.add(service);
    }

    public static List<Service> getServices() {
        return services;
    }


    public static void clearTheRequestList() {
        services.clear();
    }

    public static void cancelRequest(int id) {
        services.removeIf(service -> service.getId() == id);
    }


}

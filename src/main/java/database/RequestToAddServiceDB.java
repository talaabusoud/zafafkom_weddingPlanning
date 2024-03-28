package database;

import entity.Service;
import java.util.ArrayList;
import java.util.List;
public class RequestToAddServiceDB {
   // private RequestToAddServiceDB() {

     //   throw new UnsupportedOperationException("This class is not intended to be instantiated");
   // }
    private static List<Service> services = new ArrayList<>();
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

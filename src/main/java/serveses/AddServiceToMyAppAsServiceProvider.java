package serveses;
import entity.Service;

import static database.RequestToAddServiceDB.addService;

public class AddServiceToMyAppAsServiceProvider {

    public static void addServiceToRequestList(Service s) {
        addService(s);
    }
    private AddServiceToMyAppAsServiceProvider() {
    }
    }

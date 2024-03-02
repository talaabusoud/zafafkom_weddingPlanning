package database;

import entity.Admin;
import main.LoggerUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class AdminDB {
    private static Logger logger = LoggerUtility.getLogger();

    static List <Admin> admins= new ArrayList<Admin>();
    private AdminDB() {
        throw new IllegalStateException("Utility class");
    }
    static{
        admins.add(new Admin("1234","mohamad", "0595429100","Hebron","Mohamad",3));
        admins.add(new Admin("farah","002"));
        admins.add(new Admin("jana","JaNa"));
        admins.add(new Admin("lemara","lemo"));
    }
    public static void addAdmin(String email,String password) {

        admins.add(new Admin(email, password));
    }

    public static List<Admin> getAdmins() {
        return admins;
    }


    public static void displayAdmin(Admin admin) {
        if (admin == null) {
            logger.warning("This owner is not exist\n");
        } else {
            String spaces = "|%12s";
            String AdminInfo = String.format("|%12s", admin.getId()) +
                    String.format(spaces, admin.getName()) +
                    String.format(spaces, admin.getPhone()) +
                    String.format("|%14s", admin.getAddress()) +
                    String.format(spaces, admin.getEmail()) + "|\n";

            logger.info(AdminInfo);

        }
    }
}

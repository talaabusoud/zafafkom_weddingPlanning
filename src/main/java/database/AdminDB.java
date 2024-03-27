package database;

import entity.Admin;
import main.LoggerUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class AdminDB {
    private static Logger logger = LoggerUtility.getLogger();

    static List<Admin> admins = new ArrayList<>();
    private AdminDB() {
        throw new IllegalStateException("Utility class");
    }
    static{
        admins.add(new Admin("1234","mohamad", "0595425100","Hebron","Mohamad",3));
        admins.add(new Admin("1","1", "0592429100","Bethlehem","Ahmad",3));
        admins.add(new Admin("correctpassword","admin@example.com", "0595421100","Jenin","Kamal",3));
    }
    public static void addAdmin(String password, String email, String phone, String address, String name, int id) {

        admins.add(new Admin(password, email,phone,address,name,id));
    }

    public static List<Admin> getAdmins() {
        return admins;
    }


    public static void displayAdmin(Admin admin) {
        if (admin == null) {
            logger.warning("This owner is not exist\n");
        } else {
            String format = "|%-12s|%12s|%15s|%14s|%20s|%20s|\n";
            LoggerUtility.logInfo(logger, format, "ID", "Name", "Phone", "Address", "Email", "Password");
            LoggerUtility.logInfo(logger, format,
                    admin.getId(),
                    admin.getName(),
                    admin.getPhone(),
                    admin.getAddress(),
                    admin.getEmail(),
                    admin.getPassword()
            );

        }
    }


    public static void updateAdmin(Admin loggedInUser) {
        if (loggedInUser == null) {
            logger.warning("\nAdmin object is null. Cannot update.\n");
            return;
        }

        for (Admin admin : AdminDB.getAdmins()) {
            if (admin.getId() == loggedInUser.getId()) {
                admin.setName(loggedInUser.getName());
                admin.setEmail(loggedInUser.getEmail());
                admin.setPhone(loggedInUser.getPhone());
                admin.setAddress(loggedInUser.getAddress());
                admin.setPassword(loggedInUser.getPassword()); // افترض وجود طريقة لتشفير كلمة المرور قبل الحفظ

                logger.info("\nAdmin profile has been updated successfully.\n");
                return;
            }

    }
}

}

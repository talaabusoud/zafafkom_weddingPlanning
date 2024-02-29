package database;

import entity.Admin;


import java.util.ArrayList;
import java.util.List;

public class AdminDB {
    static List <Admin> admins= new ArrayList<Admin>();
    private AdminDB() {
        throw new IllegalStateException("Utility class");
    }
    static{
        admins.add(new Admin("mohamad","1234"));
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

}

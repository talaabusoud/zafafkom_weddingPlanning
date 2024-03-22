package database;

import entity.User;
import main.LoggerUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

public class UserDB {
    private static final Logger logger = LoggerUtility.getLogger();
    static List<User> users= new ArrayList<>();
    private UserDB() {

        throw new IllegalStateException("Utility class");
    }
    static{
        User user = new User();
        user.setId(1);
        user.setName("jana");
        user.setPhoneNumber("0599456789");
        user.setAddress("Building20");
        user.setCity("Nablus");
        user.setStreet("rafedya");
        user.setEmail("jana123@gmail.com");

        // Hash pass before storing it
        String hashedPassword = BCrypt.hashpw("12345666", BCrypt.gensalt());
        user.setPassword(hashedPassword);

        user.setHasServiceWindow(false);
        user.setService(new ArrayList<>());
        users.add(user);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

    ////////////////////// must edit
    public static void displayUser(User user) {
        if(user == null)
        {
            logger.warning("This user does not exist");
        }
        else{
            String userInfo = String.format("|%3s", user.getId()) +
                    String.format("|%10s", user.getName()) +
                    String.format("|%12s", user.getPhoneNumber()) +
                    String.format("|%8s", user.getAddress()) +
                    String.format("|%8s", user.getCity()) +
                    String.format("|%10s", user.getStreet()) +
                    String.format("|%30s", user.getService()) + "|\n";

            logger.info(userInfo);
        }
    }

    public static void updateUser(User updatedUser) {
        // Find the user in the list based on the user's ID
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                // Update the user's information
                users.set(i, updatedUser);
                LoggerUtility.getLogger().info("User information updated: " + updatedUser.getEmail());
                return;
            }
        }

        // If the user is not found, log a warning
        LoggerUtility.getLogger().warning("User not found for update: " + updatedUser.getEmail());
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        // Iterate through the list of users and find the matching user
        for (User user : users) {
            if (user.getEmail().equals(username) && BCrypt.checkpw(password, user.getPassword()))  {
                // Return the matching user
                LoggerUtility.getLogger().info("User found for login: " + username);
                return user;
            }
        }

        // If no matching user is found, log a warning and return null
        LoggerUtility.getLogger().warning("User not found for login: " + username);
        return null;
    }

    // Add this method to UserDB class
    public static boolean isUserExists(int userId, String email) {
        for (User existingUser : users) {
            if (existingUser.getId() == userId || existingUser.getEmail().equals(email)) {
                return true; // User with the same ID or email already exists
            }
        }
        return false; // User does not exist
    }

    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null; // no matching
    }

    public static boolean authenticateUser(String username, String password) {
        // Logic to authenticate user
        // For example, fetching user from the database and checking credentials
        User user = getUserByUsernameAndPassword(username,password);
        return user != null && user.getPassword().equals(password);
    }

    public static void displayUsers(List<User> users) {
        logger.info("-------------------------------------------------User----------------------------------------------\n");
        logger.info("|  id   |    Name    |PhoneNumber|  Address  |  City  |  Street  |            Service             |\n");
        for(User t:users)
        {
            displayUser(t);
        }

    }

}

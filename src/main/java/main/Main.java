package main;

import database.*;
import entity.*;
import serveses.LoginToMyAppAsAdmin;
import serveses.LoginToMyAppAsServiceProvider;
import serveses.LoginAsUser;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import static database.ServiceProviderDB.displayServiceProvider;

public class Main {
    private static final String MSG ="|              ENTER THE NUMBER OF ACTION YOU WANT TO TAKE              |\n" ;
    private static final String MSG_LOGIN_FAILED ="|   Login failed! Please check your email and password and try again.   |\n" ;
    private static final String MSG_RE_ENTER ="|                 1- Re-enter email and password                        |\n" ;
    private static final String MSG_LOG_IN_FIRST ="| User not logged in. Please log in first.                              |\n" ;
    private static final String MSG_INVALID_INPUT ="|                            Invalid input.                             |\n" ;
    private static final String STARS ="         ****************************************************         \n" ;
    private static final String LINE ="+-------+-----------------+------------+-----------------+------------+------------+\n" ;

    private static User user;
    private static Admin admin;
    private static ServiceProvider serviceProvider;
    private static final Logger logger = LoggerUtility.getLogger();

    public static void displayEnterValidNumber(){
        logger.warning("|                  PLEASE ENTER VALID NUMBER :)                         |\n");
    }
    public static void displayUpLine(){
        logger.info("________________________________________________________________________\n");
    }
    public static void displayDownLine(){
        logger.info("|_______________________________________________________________________|\n");
    }
    public static void displayEmpty(){
        logger.info("|                                                                       |\n");
    }
    public static void displaySTARSLine(){
        logger.info("|        *******************************************************        |\n");
    }
    //Home Page
    public static void menu(){
        displayUpLine();
        displayEmpty();
        displaySTARSLine();
        logger.info("|        *               Welcome to Zafafcom  :)               *        |\n");
        logger.info("|        *               Wedding Planning System               *        |\n");
        displaySTARSLine();
        displayEmpty();
        logger.info(MSG);
        displaySTARSLine();
        displayEmpty();
        logger.info("|               1- Login to System as an Admin                          |\n");
        logger.info("|               2- Login to System as a Service Provider                |\n");
        logger.info("|               3- Login to System as a User                            |\n");
        logger.info("|               4- Create a new account (sign up to the system)         |\n");
        displayDownLine();
        logger.info("\n");
    }
    // Hash the password using BCrypt and return the hashed value
    private static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    // login page form
    public static String[] loginPage(){
        displayUpLine();
        displayEmpty();
        displaySTARSLine();
        logger.warning("|        *               WELCOME TO LOGIN PAGE:)               *        |\n");
        displaySTARSLine();
        displayDownLine();
        logger.info("\n");

        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        logger.info(" - Enter your email: ");
        input[0] = scanner.next().toLowerCase(Locale.ROOT); // Convert to lowercase
        logger.info(" - Enter your password: ");
        input[1] = scanner.next();

        return input;
    }
    //login page (for user)
    public static void userLogin(String email, String enteredPassword) {
        LoginAsUser userLogin = new LoginAsUser();
        user = userLogin.loggInCheck(email, enteredPassword);

        if (user != null) {
            userPage(user);
        } // end of successfully logged in
        else {
            // Login failed due to incorrect password
            displayUpLine();
            logger.warning(MSG_LOGIN_FAILED);
            logger.warning(MSG_RE_ENTER);
            logger.warning("|                 2- Don't have an account? Sign up for a new account   |\n");
            logger.warning("|                 3- Back to home page                                  |\n");
            displayDownLine();
            logger.info("\n");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Re-enter email and password
                    String[] loginInfo = loginPage();
                    userLogin(loginInfo[0], loginInfo[1]);
                    break;
                case 2:
                    // Sign up for a new account
                    signUpPage();
                    break;
                case 3:
                    // Back to the main menu
                    menu();
                    break;
                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
        }// end of failed logging in
    }// end of login page for user
    // user page
    public static void userPage(User loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        // Check if loggedInUser is null
        if (loggedInUser == null) {
            displayUpLine();
            logger.warning(MSG_LOG_IN_FIRST);
            displayDownLine();
            return;
        }

            displayUpLine();
            displayEmpty();
            displaySTARSLine();
            logger.warning("|        *                   WELCOME " + loggedInUser.getName() + ":)                    *        |\n");
            displaySTARSLine();
            displayEmpty();
            logger.info(MSG);
            displaySTARSLine();
            displayEmpty();
            logger.info("|------------------------------- User Page -----------------------------|\n");
            logger.info("|               1- Go To Services Page                                  |\n");
            logger.info("|               2- My Profile                                           |\n");
            logger.info("|               3- Log out                                              |\n");
            displayDownLine();
            logger.info("\n");

            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Clear buffer (avoid infinite loop)
                scanner.nextLine();
                displayUpLine();
                logger.warning(MSG_INVALID_INPUT);
                logger.warning("|                   Please enter a number (1 to 4).                    |\n");
                displayDownLine();
                userChoice = -1;
            }

            switch (userChoice) {
                case 1:
                    if (user != null) {
                        servicesPage(user);
                    } else {
                        displayUpLine();
                        logger.warning(MSG_LOG_IN_FIRST);
                        displayDownLine();
                    }
                    break;

                case 2:
                    if (user != null) {
                        userProfile(user);
                    } else {
                        displayUpLine();
                        logger.warning(MSG_LOG_IN_FIRST);
                        displayDownLine();
                    }
                    break;


                case 3:
                    logout();
                    break;

                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
    }
    //sign up page (register user)
    public static void signUpPage(){
        displayUpLine();
        displayEmpty();
        displaySTARSLine();
        logger.warning("|        *               WELCOME TO SIGNUP PAGE FOR USER:)              *        |\n");
        displaySTARSLine();
        displayDownLine();
        logger.info("\n");

        user = new User();
        Scanner scanner = new Scanner(System.in);

        boolean validId = false;
        boolean validPhoneNumber = false;
        boolean validEmail = false;

        do {
            try {
                logger.info(" - Enter your ID (numbers only): ");
                user.setId(Integer.parseInt(scanner.next()));
                validId = true;
            } catch (NumberFormatException e) {
                displayUpLine();
                logger.warning("|                 Please enter a valid numerical ID.                    |\n");
                logger.warning("|                   **YOU NEED TO SIGN UP AGAIN**                       |\n");
                displayDownLine();
            }
        } while (!validId);

        logger.info(" - Enter your Name: ");
        user.setName(scanner.next());

        do {
            logger.info(" - Enter your Phone Number (numbers only): ");
            String phoneNumber = scanner.next();
            if (phoneNumber.matches("\\d+")) {
                user.setPhoneNumber(phoneNumber);
                validPhoneNumber = true;
            } else {
                displayUpLine();
                logger.warning("|             Please enter a valid numerical Phone Number.              |\n");
                logger.warning("|                   **YOU NEED TO SIGN UP AGAIN**                       |\n");
                displayDownLine();
            }
        } while (!validPhoneNumber);

        logger.info(" - Enter your Address: ");
        user.setAddress(scanner.next());

        logger.info(" - Enter your City: ");
        user.setCity(scanner.next());

        logger.info(" - Enter your Street: ");
        user.setStreet(scanner.next());

        do {
            logger.info(" - Enter your Email: ");
            String email = scanner.next();

            // Check email format using regular expression
            if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                user.setEmail(email);
                validEmail = true;
            } else {
                displayUpLine();
                logger.warning("|             Please enter a valid email address.                       |\n");
                logger.warning("|     example for the correct format: tala12@gmail.com                  |\n");
                logger.warning("|               **YOU NEED TO SIGN UP AGAIN**                           |\n");
                displayDownLine();
            }
        } while (!validEmail);

        String password;
        // Validate password length
        do {
            logger.info(" - Enter your Password (must be at least 5 characters): ");
            password = scanner.next();

            if (password.length() < 5) {
                displayUpLine();
                logger.warning("|            Password must be at least 6 characters long.               |\n");
                logger.warning("|                  Please enter a stronger password.                    |\n");
                displayDownLine();
            }
        } while (password.length() < 5);
        // Hash the user's password before saving it to UserDB
        user.setPassword(hashPassword(password));

        // Check if the user with the same ID or email already exists
        while (UserDB.isUserExists(user.getId(), user.getEmail())) {
            displayUpLine();
            logger.warning("|           User with the same ID or email already exists.              |\n");
            logger.warning("|               Please re-enter your ID and Email.                      |\n");
            logger.warning("|                   **YOU NEED TO SIGN UP AGAIN**                       |\n");
            displayDownLine();
            do {
                try {
                    logger.info(" - Re-enter your ID (numbers only): ");
                    user.setId(Integer.parseInt(scanner.next()));
                    validId = true;
                } catch (NumberFormatException e) {
                    displayUpLine();
                    logger.warning("|           Please enter a valid numerical ID.              |\n");
                    logger.warning("|                   **YOU NEED TO SIGN UP AGAIN**           |\n");
                    displayDownLine();
                    validId = false;
                }
            } while (!validId);

            boolean validEmailReEnter = false;
            do {
                logger.info(" - Re-enter your Email: ");
                String reEnteredEmail = scanner.next();

                // Check email format using regular expression
                if (reEnteredEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    user.setEmail(reEnteredEmail);
                    validEmailReEnter = true;
                } else {
                    displayUpLine();
                    logger.warning("|             Please enter a valid email address.                       |\n");
                    logger.warning("|     example for the correct format: tala12@gmail.com                  |\n");
                    logger.warning("|               **YOU NEED TO SIGN UP AGAIN**                           |\n");
                    displayDownLine();
                }
            } while (!validEmailReEnter);
        }

        displayUpLine();
        logger.info(STARS);
        logger.info("         * Thank you for signing up! Here are your details: *         \n");
        logger.info(STARS);
        logger.info("               ID: "+user.getId()+"                                                      \n");
        logger.info("               Name: "+user.getName()+"                                                    \n");
        logger.info("               Phone Number: "+user.getPhoneNumber()+"                                            \n");
        logger.info("               Address: "+user.getAddress()+"                                                 \n");
        logger.info("               City: "+user.getCity()+"                                                    \n");
        logger.info("               Street: "+user.getStreet()+"                                                  \n");
        logger.info("               Email: "+user.getEmail()+"                                                   \n");
        logger.info("               Password: "+ "*".repeat(Math.max(0, password.length())) +"                                                \n");

        int choice;
        do {
            try {
                logger.info(STARS);
                logger.info("         *    ENTER THE NUMBER OF ACTION YOU WANT TO TAKE   *         \n");
                logger.info(STARS);
                logger.info("               1- Confirm Information to Sign Up                      \n");
                logger.info("               2- Edit Information                                    \n");
                logger.info("               3- Don't save and back Home                            \n");
                displayUpLine();
                logger.info("\n");

                choice = scanner.nextInt();
            }catch (InputMismatchException e){
                // Clear buffer (avoid infinite loop)
                scanner.nextLine();
                displayUpLine();
                logger.warning(MSG_INVALID_INPUT);
                logger.warning("|                   Please enter a number (1, 2, or 3).                 |\n");
                displayDownLine();
                choice = -1;
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        if (choice == 1) {
            // Confirm sign up
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setName(user.getName());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setAddress(user.getAddress());
            newUser.setCity(user.getCity());
            newUser.setStreet(user.getStreet());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());

            // Add the user to the database if not already exists
            UserDB.addUser(newUser);

            displayUpLine();
            logger.info("|                  Account created added successfully!                  |\n");
            //UserDB.displayUser(newUser); // Need to edit function in UserDB
            displayDownLine();
            logger.info("\n");
        } else if (choice == 2){
            // Edit information
            signUpPage(); // Re-run the sign-up page for editing
        } else {
            //Home
            logger.info("\n");
        }
    }
    // logout method
    public static void logout() {
        displayUpLine();
        logger.info("|               Logout successful. Returning to the main menu.          |\n");
        displayDownLine();
        logger.info("\n");

        // Clear user data
        user = null;
    }
    // user profile page
    public static void userProfile(User loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        int profileChoice;

        do {
            displayUpLine();
            displayEmpty();
            displaySTARSLine();
            logger.info("|         *               USER PROFILE - " + loggedInUser.getName() + "                 *         |\n");
            displaySTARSLine();
            displayDownLine();
            logger.info("|               1- Show Account Information                             |\n");
            logger.info("|               2- Edit Information                                     |\n");
            logger.info("|               3- Back to User Page                                    |\n");
            displayDownLine();
            logger.info("\n");

            try {
                profileChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Clear buffer (avoid infinite loop)
                scanner.nextLine();
                displayUpLine();
                logger.warning(MSG_INVALID_INPUT);
                logger.warning("|                   Please enter a number (1, 2, or 3).                 |\n");
                displayDownLine();
                profileChoice = -1;
            }

            switch (profileChoice) {
                case 1:
                    // Show Account Information
                    displayUpLine();
                    UserDB.displayUser(loggedInUser);
                    displayDownLine();
                    break;

                case 2:
                    // Edit Information
                    editUserProfile(loggedInUser);
                    break;

                case 3:
                    userPage(loggedInUser);
                    return;
//                  break;


                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
        } while (true);
    }
    // edit user profile page
    public static void editUserProfile(User loggedInUser) {
        Scanner scanner = new Scanner(System.in);

        // Provide options for editing different fields
        int editChoice;
        do {
            displayUpLine();
            displayEmpty();
            displaySTARSLine();
            logger.info("|      *               EDIT USER PROFILE - " + loggedInUser.getName() + "          *      |\n");
            displaySTARSLine();
            displayDownLine();
            logger.info("\n");

            logger.info("|               1- Edit Name                                            |\n");
            logger.info("|               2- Edit Phone Number                                    |\n");
            logger.info("|               3- Edit Address                                         |\n");
            logger.info("|               4- Edit City                                            |\n");
            logger.info("|               5- Edit Street                                          |\n");
            logger.info("|               6- Edit Email                                           |\n");
            logger.info("|               7- Edit Password                                        |\n");
            logger.info("|               8- Back to User Profile Page                            |\n");
            displayDownLine();
            logger.info("\n");

            try {
                editChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Clear buffer (avoid infinite loop)
                scanner.nextLine();
                displayUpLine();
                logger.warning(MSG_INVALID_INPUT);
                logger.warning("|                   Please enter a number (1 to 8).                    |\n");
                displayDownLine();
                editChoice = -1;
            }

            switch (editChoice) {
                case 1:
                    logger.info(" - Enter new Name: ");
                    loggedInUser.setName(scanner.next());
                    break;

                case 2:
                    logger.info(" - Enter new Phone Number: ");
                    loggedInUser.setPhoneNumber(scanner.next());
                    break;

                case 3:
                    logger.info(" - Enter new Address: ");
                    loggedInUser.setAddress(scanner.next());
                    break;

                case 4:
                    logger.info(" - Enter new City: ");
                    loggedInUser.setCity(scanner.next());
                    break;

                case 5:
                    logger.info(" - Enter new Street: ");
                    loggedInUser.setStreet(scanner.next());
                    break;

                case 6:
                    logger.info(" - Enter new Email: ");
                    loggedInUser.setEmail(scanner.next());
                    break;

                case 7:
                    // Hash and set new password
                    logger.info(" - Enter new Password (must be at least 5 characters): ");
                    String newPassword = scanner.next();
                    while (newPassword.length() < 5) {
                        displayUpLine();
                        logger.warning("|            Password must be at least 6 characters long.               |\n");
                        logger.warning("|                  Please enter a stronger password.                    |\n");
                        displayDownLine();
                        logger.info(" - Enter new Password (must be at least 5 characters): ");
                        newPassword = scanner.next();
                    }
                    loggedInUser.setPassword(hashPassword(newPassword));
                    break;

                case 8:
                    // Back to User Profile Page
                    return;

                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
        } while (true);
    }

    public static void servicesPage(User loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        int serviceChoice;
        while (true) {
            displayServicesMenu();
            serviceChoice = getUserChoice(scanner);

            switch (serviceChoice) {
                case 1:
                    displayAllServices();
                    break;
                case 2:
                    reserveService(loggedInUser, scanner);
                    break;
                case 3:
                    showUserReservations(loggedInUser);
                    break;
                case 4:
                    searchForService(scanner);
                    break;
                case 5:
                    userPage(loggedInUser);
                    return;
                default:
                    displayInvalidOptionWarning();
                    break;
            }
        }
    }

    private static void displayServicesMenu() {
        displayUpLine();
        logger.info("|         *                   SERVICES  PAGE                 *          |\n");
        logger.info("|              1- Show Wedding Planning Services                        |\n");
        logger.info("|              2- Reserve a Service                                     |\n");
        logger.info("|              3- Show Details of my Reservations                       |\n");
        logger.info("|              4- Search for a Service                                  |\n");
        logger.info("|              5- Back to User Page                                     |\n");
        displayDownLine();
        logger.info("\n");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer to avoid infinite loop
            displayInvalidInputWarning();
            choice = -1;
        }
        return choice;
    }

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static void reserveService(User loggedInUser, Scanner scanner) {
        if (loggedInUser == null) {
            logger.warning("| You must be logged in to reserve a service. |\n");
            return;
        }

        displayAllServices();
        logger.info("| Please Enter the ID of the service you want to Reserve: |\n");
        int serviceReserveID = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Service serviceToReserve = ServiceDB.getServiceById(serviceReserveID);
        if (serviceToReserve == null) {
            logger.warning("| The service does not exist. |\n");
            return;
        }

        if ("not available".equalsIgnoreCase(serviceToReserve.getStatus())) {
            logger.warning("| The service is currently not available. |\n");
            return;
        }

        logger.info("What day do you want to book? (format: dd/MM/yyyy): ");
        String bookingDateStr = scanner.nextLine();

        LocalDate bookingDate;
        try {
            bookingDate = LocalDate.parse(bookingDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            logger.warning("| The date format is incorrect. Please use the format dd/MM/yyyy. |\n");
            return;
        }

        if (bookingDate.isBefore(LocalDate.now())) {
            logger.warning("| You cannot book a service in the past. |\n");
            return;
        }

        if (ReservationDB.isServiceReservedOnDate(serviceReserveID, bookingDateStr)) {
            logger.warning("| Reservation failed: The service is already booked on this date. |\n");
            return;
        }

        Reserve newReservation = new Reserve();
        // Assuming Reserve has a method to automatically generate and set an ID
        String reservationId = generateNewReservationId();
        newReservation.setId(reservationId);
        newReservation.setServiceId(serviceReserveID);
        newReservation.setServiceName(serviceToReserve.getName());
        newReservation.setCustomerName(loggedInUser.getName());
        newReservation.setEventDate(bookingDateStr);
        newReservation.setEventLocation(serviceToReserve.getLocation()); // Assuming Service has a getLocation() method
        newReservation.setTotalPrice(serviceToReserve.getPrice()); // Assuming Service has a getPrice() method
        newReservation.setStatus("Reserved");
        // Assuming additional details are set here

        ReservationDB.addReservation(newReservation);
        logger.info(String.format("Reservation successful: Service is booked for %s%n", bookingDateStr));
    }

    private static String generateNewReservationId() {
        // Generate a new ID based on the size of the current reservations list
        return String.valueOf(ReservationDB.getReservations().size() + 1);
    }

    private static void displayAllServices() {
        displayUpLine();
        ServiceDB.displayServices(ServiceDB.getServices());
        displayDownLine();
    }

    private static void searchForService(Scanner scanner) {
        logger.info("| Please Enter the name or type of the service you want to search for: |\n");
        String searchTerm = scanner.nextLine();
        List<Service> searchResults = searchService(searchTerm);
        if (searchResults.isEmpty()) {
            logger.warning("| No services found matching the search term. |\n");
        } else {
            ServiceDB.displayServices(searchResults);
        }
    }

    private static void displayInvalidInputWarning() {
        displayUpLine();
        logger.warning(MSG_INVALID_INPUT);
        logger.warning("| Please enter a number (1 to 5). |\n");
        displayDownLine();
    }

    private static void displayInvalidOptionWarning() {
        displayUpLine();
        displayEnterValidNumber();
        displayDownLine();
    }

    private static void showUserReservations(User loggedInUser) {
        List<Reserve> userReservations = getUserReservations(loggedInUser);
        if (userReservations.isEmpty()) {
            logger.info("| You have no reservations. |\n");
        } else {
            displayReservationsHeader();
            for (Reserve reservation : userReservations) {
                displayReservation(reservation);
            }
        }
    }

    private static List<Reserve> getUserReservations(User loggedInUser) {
        // Assuming each Reserve object has a customerName or userId to identify the user who made the reservation
        return ReservationDB.getReservations().stream()
                .filter(reservation -> reservation.getCustomerName().equals(loggedInUser.getName()))
                .collect(Collectors.toList());
    }

    private static void displayReservationsHeader() {
        logger.info(LINE);        logger.info("| ID    | Service Name    | Service ID | Customer Name   | Location   | Date       |\n");
        logger.info(LINE);    }

    private static void displayReservation(Reserve reservation) {
        String leftAlignFormat = "| %-5s | %-15s | %-10s | %-15s | %-10s | %-10s |\n";
        logger.info(String.format(leftAlignFormat,
                reservation.getId(),
                reservation.getServiceName(),
                reservation.getServiceId(),
                reservation.getCustomerName(),
                reservation.getEventLocation(),
                reservation.getEventDate()));
        logger.info(LINE);    }

    private static List<Service> searchService(String searchTerm) {
        List<Service> services = ServiceDB.getServices();
        List<Service> searchResults = new ArrayList<>();

        for (Service service : services) {
            // Perform case-insensitive search by name or type
            if (service.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    service.getType().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(service);
            }
        }

        return searchResults;
    }
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (true) {
            try {
                menu(); // Display the menu options
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
            } catch (InputMismatchException e) {
                displayUpLine();
                displayEnterValidNumber();
                displayDownLine();
                scanner.nextLine(); // Clear scanner's buffer
                continue;
            }
            if (option == 0) {
                logger.info("Exiting program...");
                break;
            }
//___________ADMIN______________________________________________________________________________________________________
            // login as Admin
            if (option == 1){
                String[] loginInfo = loginPage();
                adminLogin(loginInfo[0], loginInfo[1]);
            }

//___________SERVICE_PROVIDER___________________________________________________________________________________________
            // login as service provider
            else if (option == 2) {
                String[] loginInfo = loginPage();
                Service_Provider_Login(loginInfo[0], loginInfo[1]);
            }// end of option 2 (login as service provider)

//___________USER_______________________________________________________________________________________________________
            // login as user
            else if (option == 3 ) {
                String[] loginInfo = loginPage();
                userLogin(loginInfo[0], loginInfo[1]);
            }// end of option 3 (login as user)

//___________SIGNUP_____________________________________________________________________________________________________
            // sign up user
            else if (option == 4) {
                signUpPage();
            }// end of option 4 (sing up)

//______________________________________________________________________________________________________________________
            // wrong input
            else {
                displayUpLine();
                displayEnterValidNumber();
                displayDownLine();
                //menu();
            }

        }// end of while
    }// end of static main


    //--------------------------------admin function--------------------------------//
    public static void adminLogin(String email , String password) {
        LoginToMyAppAsAdmin adminlogin = new LoginToMyAppAsAdmin();
        admin = adminlogin.loggInCheck(email,password);
        if (admin != null) {
            adminPage(admin);
        }
        else {
            displayUpLine();
            logger.warning(MSG_LOGIN_FAILED);
            logger.warning(MSG_RE_ENTER);
            logger.warning("|                 2- Back To Home Page                                  |\n");
            displayDownLine();
            logger.info("\n");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String[] loginInfo = loginPage();
                    adminLogin(loginInfo[0], loginInfo[1]);
                    break;
                case 2:
                    menu();
                    break;
                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
        }



    }
    public static void adminPage(Admin loggedInUser) {
        displayUpLine();
        displayEmpty();
        displaySTARSLine();
        logger.warning("|        *                   WELCOME " + loggedInUser.getEmail() + ":)                    *        |\n");
        displaySTARSLine();
        displayEmpty();
        logger.info(MSG);
        displaySTARSLine();
        Adminmenu(loggedInUser );
    }
    private static void Adminmenu(Admin loggedInUser){
    Scanner scanner = new Scanner(System.in);
    int adminChoice;

    logger.info("\n|------------------------------- Admin Page -----------------------------|\n");
    logger.info("|                 1- add new user / service provider / Admin             |\n");
    logger.info("|                 2- Show service provider                               |\n");
    logger.info("|                 3- Show users                                          |\n");
    logger.info("|                 4- Show services & delete                              |\n");
    logger.info("|                 5- show reservations & delete                          |\n");
    logger.info("|                 6- profile                                             |\n");
    logger.info("|                 7- requests list                                       |\n");
    logger.info("|                 8- logout                                              |\n");
    logger.info("-------------------------------------------------------------------------|\n");
    logger.info("\n");

    try {
        adminChoice = scanner.nextInt();
    } catch (InputMismatchException e) {
        // Clear buffer (avoid infinite loop)
        scanner.nextLine();
        displayUpLine();
        logger.warning(MSG_INVALID_INPUT);
        logger.warning("|                   Please enter a number (1 to 8).                    |\n");
        displayDownLine();
        adminChoice = -1;
    }

    switch (adminChoice) {
        case 1: // إضافة مستخدم جديد أو مقدم خدمة
            addNewUser();
            break;
        case 2: // عرض مقدمي الخدمات
                showServiceProviders();
            break;
        case 3: // عرض المستخدمين
               showUsers();
            break;
        case 4: // عرض الخدمات والحذف
             showServicesAndDeleteOption();
            break;
        case 5: // عرض الحجوزات وخيار الحذف
            showReservationsAndDeleteOption();
            break;
        case 6: // عرض الملف الشخصي للإدارة
            showAdminProfile(loggedInUser);
            break;
        case 7: // عرض قائمة الطلبات
            showRequestsList();
            break;
        case 8: // تسجيل الخروج
            logout();
            break;

        default:
            displayUpLine();
            displayEnterValidNumber();
            displayDownLine();
            break;
    }
}
    private static void addNewUser() {


        logger.info("Please select the type of user you want to add:\n");
        logger.info("1- Admin\n");
        logger.info("2- ServiceProvider\n");
        logger.info("3- User\n");
        logger.info("4- Exist\n");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addAdmin();
                break;
            case "2":
                addServiceProvider();
                break;
            case "3":
                signUpPage();
                break;
            case "4":
                Adminmenu(admin);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                break;
        }
    }
    private static void addAdmin() {
        logger.info("---------------------------Adding new Admin...---------------------------\n");
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter Admin's Name:");
        String name = scanner.nextLine();
        while (!TestInput.isValidName(name)) {
            logger.info("Invalid name. Please enter a valid name (characters only):");
            name = scanner.nextLine();
        }

        logger.info("Enter Admin's Email:");
        String email = scanner.nextLine();
        while (!email.contains("@") || !email.contains(".")) {
            logger.info("Invalid email. Please enter a valid email:");
            email = scanner.nextLine();
        }

        logger.info("Enter Admin's Phone:");
        String phone = scanner.nextLine();
        while (!TestInput.isValidPhone(phone)) {
            logger.info("Invalid phone number. Please enter a valid phone number (10 digits):");
            phone = scanner.nextLine();
        }

        logger.info("Enter Admin's Address:");
        String address = scanner.nextLine();

        logger.info("Enter Admin's Password:");
        String password = scanner.nextLine();
        while (password.length() < 6) {
            logger.warning("Password must be at least 6 characters long. Please enter a stronger password:");
            password = scanner.nextLine();
        }

        int id = AdminDB.getAdmins().size() + 1;
        Admin newAdmin = new Admin(password,email , phone, address, name, id);
        AdminDB.addAdmin(password,email, phone, address, name, id);
        AdminDB.displayAdmin(newAdmin);
        Adminmenu(admin);

    }
    private static void addServiceProvider() {
        logger.info("---------------------------Adding new ServiceProvider...---------------------------\n");
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter ServiceProvider's Name:");
        String name = scanner.nextLine();
        while (!TestInput.isValidName(name)) {
            logger.info("Invalid name. Please enter a valid name (characters only):");
            name = scanner.nextLine();
        }

        logger.info("Enter ServiceProvider's Email:");
        String email = scanner.nextLine();
        while (!email.contains("@") || !email.contains(".")) {
            logger.info("Invalid email. Please enter a valid email:");
            email = scanner.nextLine();
        }

        logger.info("Enter ServiceProvider's Phone:");
        String phone = scanner.nextLine();
        while (!TestInput.isValidPhone(phone)) {
            logger.info("Invalid phone number. Please enter a valid phone number (10 digits):");
            phone = scanner.nextLine();
        }

        logger.info("Enter ServiceProvider's Address:");
        String address = scanner.nextLine();

        logger.info("Enter Admin's Password:");
        String password = scanner.nextLine();
        while (password.length() < 6) {
            logger.warning("Password must be at least 6 characters long. Please enter a stronger password:");
            password = scanner.nextLine();
        }
        int id = AdminDB.getAdmins().size() + 1;
        // Assuming ServiceProvider class has a constructor that accepts these parameters
        ServiceProvider newServiceProvider = new ServiceProvider(password,email, phone, address, name,id);
        ServiceProviderDB.addServiceProvider(newServiceProvider);
        displayServiceProvider(newServiceProvider);
    }
    public static void showServiceProviders() {
        List<ServiceProvider> serviceProviders = ServiceProviderDB.getServiceProviders();
        if (serviceProviders.isEmpty()) {
            logger.info("No service providers found.");
        } else {
            String format = "|%-12s|%12s|%15s|%14s|%20s|%20s|\n";
            logger.info(String.format(format, "ID", "Name", "Phone", "Address", "Email", "password"));

            logger.info(new String(new char[100]).replace('\0', '-'));
            logger.info("\n");
            for (ServiceProvider serviceProvider : serviceProviders) {
                logger.info(String.format(format,
                        serviceProvider.getId(),
                        serviceProvider.getName(),
                        serviceProvider.getPhone(),
                        serviceProvider.getAddress(),
                        serviceProvider.getEmail(),
                        serviceProvider.getPassword()
                ));
            }
        }
        Adminmenu(admin);
    }
    private static void showReservationsAndDeleteOption() {
        Scanner scanner = new Scanner(System.in);

        List<Reserve> reservations = ReservationDB.getReservations();
        if (reservations.isEmpty()) {
            logger.info("\nThere are no reservations to display.\n");
            return;
        }

        ReservationDB.displayReservations(reservations);

        // Options for the user
        logger.info("\nOptions:");
        logger.info("\n1 - Delete a reservation");
        logger.info("\n2 - Exit");

        logger.info("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                logger.info("\nEnter the ID of the reservation you wish to delete: ");
                String reservationId = scanner.nextLine();
                Reserve reservationToDelete = ReservationDB.getReservationById(reservationId);
                if (reservationToDelete != null) {
                    ReservationDB.deleteReservation(reservationId);
                    logger.info("\nReservation deleted successfully.");
                    Adminmenu(admin);
                } else {
                    logger.info("\nNo reservation found with the ID: " + reservationId);
                    showReservationsAndDeleteOption();
                }

                break;
            case 2:
                logger.info("\nExiting...");
                break;
            default:
                logger.info("\nInvalid choice. Please enter 1 or 2.");
                showReservationsAndDeleteOption();
                break;
        }
        Adminmenu(admin);
    }
    private static void showUsers() {
        List<User> users = UserDB.getUsers();
        if (users.isEmpty()) {
            logger.info("No users found.");
            return;
        }
        String headerFormat = "| %-10s | %-20s | %-20s | %-15s | %-30s |\n";
        logger.info(String.format(headerFormat, "ID", "Name", "Phone Number", "City", "Email"));
        logger.info(new String(new char[112]).replace('\0', '-'));
        logger.info("\n");
        String userFormat = "| %-10d | %-20s | %-20s | %-15s | %-30s |\n";
        for (User user : users) {
            logger.info(String.format(userFormat,
                    user.getId(),
                    user.getName(),
                    user.getPhoneNumber(),
                    user.getCity(),
                    user.getEmail()));
        }
        Adminmenu(admin);
    }
    private static void showServicesAndDeleteOption() {
        logger.info("Please choose an action:\n1- Show Services\n2- Delete a Service\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayServices(ServiceDB.getServices());
                Adminmenu(admin);

                break;
            case 2:
                logger.info("Enter the ID of the service you want to delete:");
                int serviceId = scanner.nextInt();
                scanner.nextLine();
                deleteService(serviceId);
                Adminmenu(admin);
                break;
            default:
                logger.warning("Invalid choice. Please select 1 or 2.");
                break;
        }
    }
    public static void displayServices(List<Service> services) {
        if (services.isEmpty()) {
            logger.info("No services found.");
        } else {
            String headerFormat = "| %-10s | %-20s | %-20s | %-15s | %-30s |\n";
            logger.info("\n"+String.format(headerFormat, "ID", "Name", "Type", "Status", "Price"));

            // عرض كل خدمة
            for (Service service : services) {
                logger.info(String.format(headerFormat,
                        service.getId(), service.getName(), service.getType(), service.getStatus(), service.getPrice()));
            }
        }
    }
    public static void deleteService(int id) {
        boolean removed = ServiceDB.deleteService(id);
        if (removed) {
            logger.info("\nService with ID " + id + " was deleted successfully.\n");
        } else {
            logger.warning("\nService with ID " + id + " could not be found or deleted.\n");
        }
    }


    private static void showAdminProfile(Admin loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        AdminDB.displayAdmin(loggedInUser);

        logger.info("Do you want to update your profile information? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            updateAdminProfile(loggedInUser, scanner);
            AdminDB.updateAdmin(loggedInUser);
            logger.info("Your profile has been updated successfully.");
        } else if ("no".equals(response)) {
            logger.info("No changes have been made to your profile.");
        } else {
            logger.info("\nInvalid response.\n");
        }
        Adminmenu(admin); // Assuming this is meant to call some kind of menu display method for the admin.
    }

    private static void updateAdminProfile(Admin loggedInUser, Scanner scanner) {
        updatePhone(loggedInUser, scanner);
        updateAddress(loggedInUser, scanner);
        updatePassword(loggedInUser, scanner);
    }

    private static void updatePhone(Admin admin, Scanner scanner) {
        logger.info("Enter the new phone number (leave blank to keep current): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty() && TestInput.isValidPhone(newPhone)) {
            admin.setPhone(newPhone);
        } else if (!newPhone.isEmpty()) {
            logger.warning("Invalid phone number. Keeping the current phone number.\n");
        }
    }

    private static void updateAddress(Admin admin, Scanner scanner) {
        logger.info("Enter the new address (leave blank to keep current): ");
        String newAddress = scanner.nextLine().trim();
        if (!newAddress.isEmpty()) {
            admin.setAddress(newAddress);
        }
    }

    private static void updatePassword(Admin admin, Scanner scanner) {
        logger.info("Enter the new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine().trim();
        if (!newPassword.isEmpty()) {
            while (newPassword.length() < 6) {
                logger.warning("Password must be at least 6 characters long. Please enter a stronger password:");
                newPassword = scanner.nextLine().trim();
            }
            admin.setPassword(newPassword);
        }
    }

    private static void showRequestsList() {
        Scanner scanner = new Scanner(System.in);
        List<Service> requests = RequestToAddServiceDB.getServices(); // الحصول على الطلبات

        if (requests.isEmpty()) {
            logger.info("\nThere are no pending service requests.\n");

        }else{

        ServiceDB.displayServices(requests);

        logger.info("Options:\n");
        logger.info("1- Approve All Requests\n");
        logger.info("2- Approve a Specific Request\n");
        logger.info("3- Reject a Specific Request\n");
        logger.info("4- Exit\n");
        logger.info("Enter your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                RequestToAddServiceDB.getServices().forEach(ServiceDB::addService);
                RequestToAddServiceDB.clearTheRequestList();
                logger.info("All requests have been approved.");
                break;
            case 2:
                logger.info("Enter the ID of the service to approve:");
                int approveId = scanner.nextInt();
                Service serviceToApprove = requests.stream()
                        .filter(s -> s.getId() == approveId)
                        .findFirst()
                        .orElse(null);
                if (serviceToApprove != null) {
                    ServiceDB.addService(serviceToApprove);
                    RequestToAddServiceDB.cancelRequest(approveId);
                    logger.info("Service with ID " + approveId + " has been approved.");
                } else {
                    logger.info("Service with ID " + approveId + " not found.");
                }
                break;
            case 3:
                logger.info("Enter the ID of the service to reject:");
                int rejectId = scanner.nextInt();
                if (RequestToAddServiceDB.getServices().removeIf(s -> s.getId() == rejectId)) {
                    logger.info("Service with ID " + rejectId + " has been rejected.");
                } else {
                    logger.info("Service with ID " + rejectId + " not found.");
                }
                break;
            case 4: break;
            default:
                logger.info("Invalid choice.");
                break;
        }}
        Adminmenu(admin);
    }

    //--------------------------------service provider function--------------------------------//
    public static void Service_Provider_Login(String email, String password) {
        LoginToMyAppAsServiceProvider serviceProviderLogin = new LoginToMyAppAsServiceProvider();
        serviceProvider = serviceProviderLogin.loggInCheck(email, password);
        if (serviceProvider != null) {
            Service_Provider_Page(serviceProvider);
        } else {
            displayUpLine();
            logger.warning(MSG_LOGIN_FAILED);
            logger.warning(MSG_RE_ENTER);
            logger.warning("|                 2- Back To Home Page                                  |\n");
            displayDownLine();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String[] loginInfo = loginPage();
                    Service_Provider_Login(loginInfo[0], loginInfo[1]);
                    break;
                case 2:
                    menu();
                    break;
                default:
                    displayUpLine();
                    displayEnterValidNumber();
                    displayDownLine();
                    break;
            }
        }




    }
    public static void Service_Provider_Page(ServiceProvider loggedInUser) {
        displayUpLine();
        displayEmpty();
        displaySTARSLine();
        logger.warning("|        *                   WELCOME " + loggedInUser.getEmail() + ":)                    *        |\n");
        displaySTARSLine();
        displayEmpty();
        logger.info(MSG);
        displaySTARSLine();
        Service_Provider_menu(loggedInUser );
    }
    private static void Service_Provider_menu(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        logger.info("\n" +
                "|------------------- Service Provider Page -----------------------------|\n" +
                "| 1- Add new service                                                    |\n" +
                "| 2- Show services & delete                                             |\n" +
                "| 3- Show reservations (details + delete)                               |\n" +
                "| 4- Profile                                                            |\n" +
                "| 5- Logout                                                             |\n" +
                "|-----------------------------------------------------------------------|\n" +
                "Please enter your choice:");

        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer to avoid infinite loop
            logger.warning("Invalid input. Please enter a number (1 to 5).");
            return;
        }

        switch (choice) {
            case 1:
                addNewService(loggedInUser);
                break;
            case 2:
                showAndDeleteServices(loggedInUser);
                break;
            case 3:
                // Call method to show and delete reservations
                showAndDeleteReservations(loggedInUser);
                break;
            case 4:
                // Call method to edit profile
                editServiceProviderProfile(loggedInUser);
                break;
            case 5:
                break;

            default:
                logger.warning("Invalid choice. Please enter a number (1 to 5).");
                break;
        }
    }


    private static void addNewService(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);

        String type;
        do {
            logger.info("Enter Service Type (e.g., Hall, Food, DJ, zaffa, decoration): ");
            type = scanner.nextLine();
            if (!TestInput.type(type)) {
                logger.warning("Invalid type. Please enter a valid service type.");
            }
        } while (!TestInput.type(type));

        String name;
        do {
            logger.info("Enter Service Name: ");
            name = scanner.nextLine();
            if (!TestInput.isValidName(name)) {
                logger.warning("Invalid name. Names must not contain digits.");
            }
        } while (!TestInput.isValidName(name));

        logger.info("Enter Service Location: ");
        String location = scanner.nextLine(); // Assuming location does not need validation

        String status;
        logger.info("Enter Service Status: \n1- Available\n2- Not Available");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();
        status = (statusChoice == 2) ? "Not Available" : "Available";
        String priceStr;
        double price = 0;
        do {
            logger.info("Enter Service Price: ");
             priceStr = scanner.nextLine();
            if (TestInput.isValidPrice(priceStr)) {
                price = Double.parseDouble(priceStr);
            } else {
                logger.warning("Invalid price. Please enter a numeric value.");
            }
        } while (!TestInput.isValidPrice(priceStr));

        String phone;
        do {
            logger.info("Enter Service Phone: ");
            phone = scanner.nextLine();
            if (!TestInput.isValidPhone(phone)) {
                logger.warning("Invalid phone number. Please enter a 10-digit number.");
            }
        } while (!TestInput.isValidPhone(phone));

        String image;
        do {
            logger.info("Enter Service Image URL: ");
            image = scanner.nextLine();
            if (!TestInput.imge(image)) {
                logger.warning("Invalid image URL. Please enter a valid URL ending with .png or .jpg.");
            }
        } while (!TestInput.imge(image));

        // Creating a new service object
        Service newService = new Service();
        newService.setId(RequestToAddServiceDB.getServices().size() + 1 +ServiceDB.getServices().size() + 1); // Assuming IDs are sequential
        newService.setType(type);
        newService.setName(name);
        newService.setLocation(location);
        newService.setStatus(status);
        newService.setPrice(price);
        newService.setPhone(phone);
        newService.setImage(image);
        newService.setOwner(loggedInUser);

        // Adding the service to the request list
        RequestToAddServiceDB.addService(newService);

        logger.info("\nNew service has been added to the request list successfully.\n");
        Service_Provider_menu(serviceProvider);
    }
    private static void showAndDeleteServices(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);

        List<Service> providerServices = ServiceDB.getServicesByProvider(loggedInUser.getId()); // تنفيذ هذه الدالة لتحصل على الخدمات

        if (providerServices.isEmpty()) {
            logger.info("\nYou have no services listed.\n");
            Service_Provider_menu(serviceProvider);
            return;
        } else {
            logger.info("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
            String headerFormat = "| %-5s | %-15s | %-10s | %-12s | %-15s | %-15s | %-30s | %-15s |\n";
            logger.info(String.format(headerFormat, "ID", "Name", "Price", "Status", "Location", "Owner", "Image URL", "Type"));
            logger.info("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");

            // طباعة الخدمات بشكل جدول
            providerServices.forEach(ServiceDB::displayService);


            boolean isRunning = true;

                logger.info("Options: \n1- Edit a Service\n2- Delete a Service\n3- Exit\n");
                logger.info("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        editService(loggedInUser);
                        break;
                    case 2:
                        // حذف خدمة
                        logger.info("Enter the ID of the service you want to delete:");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // تنظيف البافر
                        boolean serviceExists = false;
                        for (Service service : providerServices) {
                            if (service.getId() == deleteId) {
                                serviceExists = true;
                                break;
                            }
                        }
                        if (serviceExists && ServiceDB.deleteService(deleteId)) {
                            logger.info("Service deleted successfully.");
                        } else {
                            logger.info("Service could not be found or does not belong to you.");
                        }

                        break;
                    case 3:
                        break;
                    default:
                        logger.info("Invalid option. Please enter a valid choice.");
                        break;
                }
            Service_Provider_menu(serviceProvider);

        }
    }
    private static void showAndDeleteReservations(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);



            List<Reserve> reservationsForProvider = ReservationDB.getReservationsForService(loggedInUser.getId());

            if (reservationsForProvider.isEmpty()) {
                logger.info("\nNo reservations found.\n");
                Service_Provider_menu(serviceProvider);
            }

            ReservationDB.displayReservations(reservationsForProvider);

            logger.info("\nOptions:");
            logger.info("\n1 - Delete a reservation");
            logger.info("\n2 - Exit");

            logger.info("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Delete a reservation
                    logger.info("\nEnter reservation ID to delete: ");
                    String reservationIdToDelete = scanner.nextLine();
                    ReservationDB.deleteReservation(reservationIdToDelete);
                    break;

                case 2: // Exit

                    break;

                default:
                    logger.info("\nInvalid option, please try again.");
            }
        Service_Provider_menu(serviceProvider);

    }

    private static void editService(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);
        logger.info("\nEnter the ID of the service you want to edit:");
        int serviceId = scanner.nextInt();
        scanner.nextLine(); // لتنظيف البافر

        // البحث عن الخدمة بناءً على مُعرف الخدمة ومُعرف مقدم الخدمة
        Service serviceToEdit = null;
        for (Service service : ServiceDB.getServicesByProvider(loggedInUser.getId())) {
            if (service.getId() == serviceId) {
                serviceToEdit = service;
                break;
            }
        }

        if (serviceToEdit == null) {
            logger.info("\nService not found or does not belong to you.\n");
            return;
        }

        // تعديل اسم الخدمة
        logger.info("\nEnter the new name (leave blank to keep current):");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty() && TestInput.isValidName(newName)) {
            serviceToEdit.setName(newName);
        } else if (!newName.isEmpty()) {
            logger.info("\nInvalid name. Keeping the current name.");
        }

        // تعديل نوع الخدمة
        logger.info("\nEnter the new type (e.g., Hall, Food, DJ, Zaffa, Decoration) (leave blank to keep current):");
        String newType = scanner.nextLine().trim();
        if (!newType.isEmpty() && TestInput.type(newType)) {
            serviceToEdit.setType(newType);
        } else if (!newType.isEmpty()) {
            logger.info("\nInvalid type. Keeping the current type.");
        }

        // تعديل الموقع
        logger.info("\nEnter the new location (leave blank to keep current):");
        String newLocation = scanner.nextLine().trim();
        if (!newLocation.isEmpty()) {
            serviceToEdit.setLocation(newLocation);
        }

        // تعديل الحالة
        logger.info("\nEnter the new status (\n1- for Available, \n2 -for Not Available  ");
        String statusChoice = scanner.nextLine().trim();
        if ("1".equals(statusChoice)) {
            serviceToEdit.setStatus("Available");
        } else {
            serviceToEdit.setStatus("Not Available");
        }

        // تعديل السعر
        logger.info("\nEnter the new price (leave blank to keep current):");
        String newPriceStr = scanner.nextLine().trim();
        if (!newPriceStr.isEmpty() && TestInput.isValidPrice(newPriceStr)) {
            double newPrice = Double.parseDouble(newPriceStr);
            serviceToEdit.setPrice(newPrice);
        } else if (!newPriceStr.isEmpty()) {
            logger.info("\nInvalid price. Keeping the current price.");
        }

        // تعديل الهاتف
        logger.info("\nEnter the new phone (leave blank to keep current):");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty() && TestInput.isValidPhone(newPhone)) {
            serviceToEdit.setPhone(newPhone);
        } else if (!newPhone.isEmpty()) {
            logger.info("\nInvalid phone. Keeping the current phone.");
        }

        // تعديل URL الصورة
        logger.info("\nEnter the new image URL (leave blank to keep current):");
        String newImageURL = scanner.nextLine().trim();
        if (!newImageURL.isEmpty() && TestInput.imge(newImageURL)) {
            serviceToEdit.setImage(newImageURL);
        } else if (!newImageURL.isEmpty()) {
            logger.info("\nInvalid image URL. Keeping the current image URL.");
        }

        // تحديث الخدمة في قاعدة البيانات
        ServiceDB.updateService(serviceToEdit);
        logger.info("\nService updated successfully.\n");
    }
    private static void editServiceProviderProfile(ServiceProvider loggedInUser) {
        Scanner scanner = new Scanner(System.in);

        // Display current service provider profile
        ServiceProviderDB.displayServiceProvider(loggedInUser);

        // Ask if the service provider wants to update their information
        logger.info("\nDo you want to update your profile information? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            // Name update
            logger.info("\nEnter the new name (leave blank to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty() && TestInput.isValidName(newName)) {
                loggedInUser.setName(newName);
            } else if (!newName.isEmpty()) {
                logger.info("\nInvalid name. Keeping the current name.");
            }

            // Phone update
            logger.info("\nEnter the new phone number (leave blank to keep current): ");
            String newPhone = scanner.nextLine().trim();
            if (!newPhone.isEmpty() && TestInput.isValidPhone(newPhone)) {
                loggedInUser.setPhone(newPhone);
            } else if (!newPhone.isEmpty()) {
                logger.info("\nInvalid phone number. Keeping the current phone number.");
            }

            // Address update
            logger.info("\nEnter the new address (leave blank to keep current): ");
            String newAddress = scanner.nextLine().trim();
            if (!newAddress.isEmpty()) {
                loggedInUser.setAddress(newAddress);
            }

            // Password update
            logger.info("\nEnter the new password (leave blank to keep current): ");
            String newPassword = scanner.nextLine().trim();
            if (!newPassword.isEmpty()){
                while (newPassword.length() < 6) {
                    logger.warning("\nPassword must be at least 6 characters long. Please enter a stronger password:");
                    newPassword = scanner.nextLine().trim();
                }
                loggedInUser.setPassword(newPassword);
            }

            // Update the service provider in the database
            ServiceProviderDB.updateServiceProvider(loggedInUser);

            logger.info("\nYour profile has been updated successfully.");
        } else if ("no".equals(response)) {
            logger.info("\nNo changes have been made to your profile.");
        } else {
            logger.info("\nInvalid response.\n");
        }


        Service_Provider_menu(serviceProvider);
    }



}












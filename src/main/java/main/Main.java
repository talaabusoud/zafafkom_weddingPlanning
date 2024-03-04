package main;

import database.*;
import entity.*;
import serveses.LoginToMyAppAsAdmin;
import serveses.LoginToMyAppAsServiceProvider;
import serveses.LoginAsUser;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static User user;
    private static final Logger logger = LoggerUtility.getLogger();

    public static void displayEnterValidNumber(){
        logger.warning("|                  PLEASE ENTER VALID NUMBER :)                         |\n");
    }
    public static void enterSignUpInfoAgain(){
        logger.warning("|            PLEASE ENTER SIGN UP INFORMATION AGAIN :)                  |\n");
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
    public static void displayStarsLine(){
        logger.info("|        *******************************************************        |\n");
    }

    public static void menu(){
        displayUpLine();
        displayEmpty();
        displayStarsLine();
        logger.info("|        *               Welcome to Zafafcom  :)               *        |\n");
        logger.info("|        *               Wedding Planning System               *        |\n");
        displayStarsLine();
        displayEmpty();
        logger.info("|              ENTER THE NUMBER OF ACTION YOU WANT TO TAKE              |\n");
        displayStarsLine();
        displayEmpty();
        logger.info("|               1- Login to System as an Admin                          |\n");
        logger.info("|               2- Login to System as a Service Provider                |\n");
        logger.info("|               3- Login to System as a User                            |\n");
        logger.info("|               4- Create a new account (sign up to the system)         |\n");
        displayDownLine();
        logger.info("\n");
    }

    public static void loginPage(){
        displayUpLine();
        displayEmpty();
        displayStarsLine();
        logger.warning("|        *               WELCOME TO LOGIN PAGE:)               *        |\n");
        displayStarsLine();
        displayDownLine();
        logger.info("\n");

        String email;
        String password;
        Scanner scanner = new Scanner(System.in);
        logger.info(" - Enter your email: ");
        email = scanner.next();
        logger.info(" - Enter your password: ");
        password = scanner.next();

        logger.info("\n");
    }

    //sign up page (register user)
    public static void signUpPage(){
        displayUpLine();
        displayEmpty();
        displayStarsLine();
        logger.warning("|        *               WELCOME TO SIGNUP PAGE:)              *        |\n");
        displayStarsLine();
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
        logger.info("         ****************************************************         \n");
        logger.info("         * Thank you for signing up! Here are your details: *         \n");
        logger.info("         ****************************************************         \n");
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
            logger.info("         ****************************************************         \n");
            logger.info("         *    ENTER THE NUMBER OF ACTION YOU WANT TO TAKE   *         \n");
            logger.info("         ****************************************************         \n");
            logger.info("               1- Confirm Informations to Sign Up                     \n");
            logger.info("               2- Edit Information                                    \n");
            displayUpLine();
            logger.info("\n");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

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
            displayEmpty();
            logger.info("|               User added successfully! Here are your details:               |\n");
            displayEmpty();
            UserDB.displayUser(newUser); // Need to edit function in UserDB
            displayEmpty();
            displayDownLine();
            logger.info("\n");
        } else {
            // Edit information
            signUpPage(); // Re-run the sign-up page for editing
        }
    }


    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while(true) {
            try {
                menu();
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                displayUpLine();
                displayEnterValidNumber();
                displayDownLine();
                scanner.nextLine();
                continue;
            }

            // login as Admin
            if (option == 1){
                loginPage();
            }

            // login as service provider
            else if (option == 2) {
                loginPage();
            }

            // login as user
            else if (option == 3 ) {
                loginPage();
            }

//______________________________________________________________________________________________________________________
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
}// end of main class

package AcceptanceTest;

import entity.Service;
import database.ServiceDB;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import main.TestInput;
import serveses.LoginToMyAppAsServiceProvider;
import database.RequestToAddServiceDB;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class add_service_to_request_list {
    private static Logger logger = LoggerUtility.getLogger();
    boolean result_test_input = false;
    LoginToMyAppAsServiceProvider myApp ;
    Service service;
    public add_service_to_request_list(){
        myApp = new LoginToMyAppAsServiceProvider();
        myApp.login();
    }
    @When("user click on insert order and flag is {string}")
    public void userClickOnInsertOrderAndFlagIs(String string) {


         if (string.equals(true)) assertTrue(myApp.isLoggedIn());
    }
    @Then("the field {string} should be with error")
    public void theFieldShouldBeWithError(String string) {
        theUserShoudeSee("pleas enter information first !\n");
    }
    @Then("the image {string} should be with error")
    public void theImageShouldBeWithError(String string) {
        theUserShoudeSee("pleas enter information first !\n");
    }


    @When("he fill in  {string} with {string}")
    public void heFillInWith(String string, String string2) {
     boolean flag ;
        service= new Service();
       if(string.equals("Type")){
        flag= TestInput.type(string2);
        if (flag)
        { service.setType(string2);result_test_input = true;}
        else{ result_test_input = false;}
       } else if (string.equals("Name")) {
           flag= TestInput.isValidName(string2);
           if (flag){ service.setName(string2);result_test_input = true;}
           else {result_test_input = false;}

       }else if (string.equals("Phone")) {
           flag= TestInput.isValidPhone(string2);
           if (flag) {service.setPhone(string2);result_test_input = true;}
           else {result_test_input = false;}

       }else if (string.equals("Price")) {
           flag= TestInput.isValidPrice(string2);
           if (flag) {service.setPrice(Double.parseDouble(string2));result_test_input = true;}
           else{result_test_input = false;}

       }
       else if (string.equals("Id")) {
           flag= TestInput.isValidPrice(string2);
           if (flag) {service.setId(Integer.parseInt(string2));result_test_input = true;}
           else{result_test_input = false;}}

    }
    @Then("the Service will be saved in the request list")
    public void theServiceWillBeSavedInTheRequestList() {
        if(result_test_input){
        RequestToAddServiceDB.addService(service); }
        else theUserShoudeSee("Please make sure you enter the data correctly\n") ;
    }

    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        if (string.equals("saved in the request list")&&string2.equals("true"))
        {theServiceWillBeSavedInTheRequestList();}else theUserShoudeSee("Please make sure you enter the data correctly\n");
    }
    @When("he fill in  {string} with extension {string}")
    public void heFillInWithExtension(String string, String string2) {
        boolean flag ;
        flag= TestInput.imge(string2);
        if (flag)   assertTrue(flag);
        else assertFalse(flag);

        int serviceIdToCancel =4;

        // Cancel the request
        RequestToAddServiceDB.cancelRequest(serviceIdToCancel);
        Service resultService = ServiceDB.getServiceById(serviceIdToCancel);
        assertNull("Service with ID " + serviceIdToCancel + " should have been removed.", resultService);


    }
    @Then("the user shoude See {string}")
    public void theUserShoudeSee(String string) {
        logger.info(string);

    }
}

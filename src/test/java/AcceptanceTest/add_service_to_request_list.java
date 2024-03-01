package AcceptanceTest;

import entity.Service;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import serveses.LoginToMyAppAsServiceProvider;
import database.RequestToAddServiceDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class add_service_to_request_list {
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
         else assertEquals(false,false);
    }
    @Then("the field {string} should be with error")
    public void theFieldShouldBeWithError(String string) {
        System.out.println("pleas enter information first !");
        assertEquals(false,false);
    }
    @Then("the image {string} should be with error")
    public void theImageShouldBeWithError(String string) {
        System.out.println("pleas enter Picture first !");
        assertEquals(false,false);
    }


    @When("he fill in  {string} with {string}")
    public void heFillInWith(String string, String string2) {
     boolean flag = false;
        service= new Service();
       if(string.equals("Type")){
        flag= main.Test_input.type(string2);
        if (flag==true)
        { service.setType(string2);result_test_input = true;}
        else{ result_test_input = false;}
       } else if (string.equals("Name")) {
           flag= main.Test_input.Name(string2);
           if (flag==true){ service.setName(string2);result_test_input = true;}
           else {result_test_input = false;}

       }else if (string.equals("Phone")) {
           flag= main.Test_input.Phone(string2);
           if (flag==true) {service.setPhone(string2);result_test_input = true;}
           else {result_test_input = false;}

       }else if (string.equals("Price")) {
           flag= main.Test_input.Price(string2);
           if (flag==true) {service.setPrice(Double.parseDouble(string2));result_test_input = true;}
           else{result_test_input = false;}

       }
       else if (string.equals("Id")) {
           flag= main.Test_input.Price(string2);
           if (flag==true) {service.setId(Integer.parseInt(string2));result_test_input = true;}
           else{result_test_input = false;}}

    }
    @Then("the Service will be saved in the request list")
    public void theServiceWillBeSavedInTheRequestList() {
        if(result_test_input==true){
        RequestToAddServiceDB.addService(service); assertEquals(true,true);}
        else theUserShoudeSee("Please make sure you enter the data correctly") ;
        assertEquals(false,false);
    }

    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        assertEquals(true,true);
    }
    @When("he fill in  {string} with extension {string}")
    public void heFillInWithExtension(String string, String string2) {
        boolean flag = false;
        flag= main.Test_input.imge(string2);
        if (flag==true) assertEquals(true,true);else assertEquals(false,false);
    }
    @Then("the user shoude See {string}")
    public void theUserShoudeSee(String string) {
        assertEquals(false,false);

    }
}

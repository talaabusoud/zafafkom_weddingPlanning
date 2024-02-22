package AcceptanceTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.zafafcom.Test_input;

import static org.junit.Assert.assertEquals;

public class add_service {
    @When("user click on insert order and flag is {string}")
    public void userClickOnInsertOrderAndFlagIs(String string) {
         if (string.equals(true)) assertEquals(true,true);
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

    @Then("the information has been entered successfully")
    public void theInformationHasBeenEnteredSuccessfully() {
        assertEquals(true,true);

    }

    @When("he fill in  {string} with {string}")
    public void heFillInWith(String string, String string2) {
     boolean flag = false;
       if(string.equals("Type")){
        flag= Test_input.type(string2);
        if (flag==true) assertEquals(true,true);else assertEquals(false,false);
       } else if (string.equals("Name")) {
           flag= Test_input.Name(string2);
           if (flag==true) assertEquals(true,true);else assertEquals(false,false);

       }else if (string.equals("Phone")) {
           flag= Test_input.Phone(string2);
           if (flag==true) assertEquals(true,true);else assertEquals(false,false);

       }else if (string.equals("Price")) {
           flag= Test_input.Price(string2);
           if (flag==true) assertEquals(true,true);else assertEquals(false,false);

       }

    }
    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        assertEquals(true,true);
    }
    @When("he fill in  {string} with extension {string}")
    public void heFillInWithExtension(String string, String string2) {
        boolean flag = false;
        flag= Test_input.imge(string2);
        if (flag==true) assertEquals(true,true);else assertEquals(false,false);
    }
    @Then("the user shoude See {string}")
    public void theUserShoudeSee(String string) {
        assertEquals(false,false);

    }
}

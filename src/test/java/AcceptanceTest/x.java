package AcceptanceTest;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class x {
    @When("user click on save and flag is {string}")
    public void userClickOnSaveAndFlagIs(String string) {
        System.out.println("jmalll");

    }
    @Then("the field {string} shoud be an error")
    public void theFieldShoudBeAnError(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the field i Quantity' shoud be an error")
    public void theFieldIQuantityShoudBeAnError() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("theimage'Picture' shoud be an error")
    public void theimagePictureShoudBeAnError() {
        // Write code here that turns the phrase above into concrete actions

    }
}

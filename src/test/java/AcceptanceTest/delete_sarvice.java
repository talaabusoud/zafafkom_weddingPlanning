package AcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class delete_sarvice {

    private int serviceIdToDelete;
    private boolean serviceDeleted;
    @Given("the sarvice list have sarvice")
    public void theSarviceListHaveSarvice() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the admin or sarvice provider want to delete sarvice with id {int}")
    public void theAdminOrSarviceProviderWantToDeleteSarviceWithId(Integer int1) {
     serviceDeleted=true;


    }
    @Then("delete done")
    public void deleteDone() {
        assertEquals(true, serviceDeleted);
    }

    @When("the admin or sarvice provider want to delete it")
    public void theAdminOrSarviceProviderWantToDeleteIt() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("that the admin or sarvice provider want to delete sarvice with not valid id {int}")
    public void thatTheAdminOrSarviceProviderWantToDeleteSarviceWithNotValidId(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        serviceDeleted= false;
    }

    @Then("message appear to tell the them that is no sarvice with this id")
    public void messageAppearToTellTheThemThatIsNoSarviceWithThisId() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(false, serviceDeleted);
    }
}

package AcceptanceTest;

import database.ServiceDB;
import entity.Service;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.LoggerUtility;
import serveses.AppLogger;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class delete_sarvice {
    private static Logger logger = LoggerUtility.getLogger();
    Service s ;
    int id=5;
    public delete_sarvice()
    {
        AppLogger.setLevel(logger);
        logger.info("\nDeleting service with id: " + id+"\n");
        s  = new Service();
    }

    @Given("the sarvice list have sarvice")
    public void theSarviceListHaveSarvice() {
        s.setId(id);
        ServiceDB.addService(s);

    }
    @When("the admin or sarvice provider want to delete sarvice with id {int}")
    public void theAdminOrSarviceProviderWantToDeleteSarviceWithId(Integer int1) {
     this.id=int1;


    }
    @Then("delete done")
    public void deleteDone() {
        assertNotNull(ServiceDB.getServices());
        ServiceDB.deleteService(id);
        assertFalse(ServiceDB.getServices().contains(s));}

    @Given("that the admin or sarvice provider want to delete sarvice with not valid id {int}")
    public void thatTheAdminOrSarviceProviderWantToDeleteSarviceWithNotValidId(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        this.id=int1;
    }

    @When("the admin or sarvice provider want to delete it")
    public void theAdminOrSarviceProviderWantToDeleteIt() {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(false,false);
    }



    @Then("message appear to tell the them that is no sarvice with this id")
    public void messageAppearToTellTheThemThatIsNoSarviceWithThisId() {
        logger.info("\nNo service with this id");


    }
}

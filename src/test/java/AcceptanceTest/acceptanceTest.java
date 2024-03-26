package AcceptanceTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "Cases",monochrome = true,snippets = SnippetType.CAMELCASE,glue = {"AcceptanceTest"})
public class acceptanceTest {
}


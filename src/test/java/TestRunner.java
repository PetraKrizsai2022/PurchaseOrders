import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        dryRun = true
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}

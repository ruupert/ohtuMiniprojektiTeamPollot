package tip;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCukesTest {
//    @ClassRule
//    public static ServerRule server = new ServerRule(8080);  ServerRule classia ei muka löydy vaikka sen pitäisi olla samassa pakkauksessa...
}
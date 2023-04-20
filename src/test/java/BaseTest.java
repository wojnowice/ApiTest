import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.RestAssuredUtil;

public class BaseTest {

    @BeforeClass
    public void setup() {
        //Test Setup
        RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("/v2/pet"); //Setup Base Path
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssuredUtil.resetBaseURI();
        RestAssuredUtil.resetBasePath();
    }
}
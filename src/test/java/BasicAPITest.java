import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAPITest extends BaseTest {

    @Test
    public void createNewPet()
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", 0);
        jsonObject.put("name", "doggie");
        jsonObject.put("status", "available");


        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(jsonObject.toString())
                .post();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("content-type"),"application/json");

        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("id"));
        Assert.assertTrue(responseBodyAsString.contains("name"));

        Assert.assertTrue(responseBodyAsString.contains("status"));

        String name = JsonPath.from(responseBodyAsString).get("name");
        Assert.assertEquals(name,"doggie");
        String status = JsonPath.from(responseBodyAsString).get("status");
        Assert.assertEquals(status,"available");
        long id = JsonPath.from(responseBodyAsString).get("id");
        System.out.println("The ID is "+id);
    }
}
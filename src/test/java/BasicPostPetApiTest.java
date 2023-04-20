import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicPostPetApiTest extends BaseTest {

    @Test
    public void testCreateNewPet() {
        // Create a new pet object using the Pet POJO
        Pet newPet = new Pet(
                0,
                new Category(0, "string"),
                "doggie",
                new String[]{"string"},
                new Tag[]{new Tag(0, "string")},
                "available"
        );

        // Send the POST request to add a new pet to the store
        Response response = given()
                .contentType(ContentType.JSON)
                .body(newPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .response();
        response.prettyPrint();

        // Verify the response code and make assertions for response
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
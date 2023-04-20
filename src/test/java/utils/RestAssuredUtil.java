package utils;

import io.restassured.RestAssured;

public class RestAssuredUtil {
    //Sets Base URI
    public static void setBaseURI() {
        RestAssured.baseURI = "https://petstore.swagger.io";
    }

    //Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }
}
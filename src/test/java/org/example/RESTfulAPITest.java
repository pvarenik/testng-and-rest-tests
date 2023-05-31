package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class RESTfulAPITest {

    @Test
    public void checkEntityTest() {
        RestAssured.baseURI = "https://api.publicapis.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/entries");
        JsonPath body = response.jsonPath();
        List<HashMap> entries = body.get("entries");
        int numberOfAuth = 0;
        for (HashMap entry : entries) {
            if (entry.get("Category").toString().equals("Authentication & Authorization")) {
                numberOfAuth += 1;
                System.out.println(entry);
            }
        }
        Assert.assertTrue(body.getInt("count") > numberOfAuth, "Number of specific entities is bigger than counted number in response" + numberOfAuth);
    }
}

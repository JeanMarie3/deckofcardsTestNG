import Library.ListenersTypes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@Listeners(ListenersTypes.class)


public class mainTests {
    public static String requestBody = "{\n " +
            "\"title\" : \"Created by Jmv\",\n" +
            "\"body\" : \"POST Testing\" , \n" +
            "\"userId\" : \"11\", \n " +
//            "\"id\" : \"101\", \n "+
            "}";

    String baseURI1= RestAssured.baseURI = "https://deckofcardsapi.com/";
    String baseURI2 = RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

    @BeforeMethod
    public void config(){

    }

    @Test(priority = 1, description = "Test 1", enabled=true)
    public void checkStatusCode(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(baseURI1 + "api/deck/new/draw/?count={id}",2);

        System.out.println((response.getBody().asPrettyString() + " " + ": \nBody is displayed"));
        System.out.println(response.getStatusCode() +" " +  ": Status code returned");
        System.out.println(response.getStatusLine() +" " +  ": Status line returned");

        System.out.println(response.getTime() +" " +  ": Time is printed");
        System.out.println(response.getSessionId()+" " +  ": Session Id is Null");


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);


    }

    @Test(priority = 2, description = "Test 2", enabled=true)
    public void testGetHeader(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(baseURI1 + "api/deck/new/draw/?count={id}",2);

        String checkHeader = response.getHeader("content-type");

        when().
                get(baseURI1+ "api/deck/new/draw/?count={id}",2)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();



        System.out.println(checkHeader +" " +  ": Header is checked");
        //OR
        System.out.println(response.getHeader("content-type") +" " +  ": Header is displayed");

        Assert.assertEquals(checkHeader, "application/json");
    }

    @Test(priority = 3, description = "Test 3", enabled=true)

public void postRequest(){

        String requestBody = "{\n" +
                "  \"title\": \"Created by Jmv\",\n" +
                "  \"body\": \"POST Testing\",\n" +
                "  \"userId\": \"11\" \n}";

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("Created by Jmv", response.jsonPath().getString("title"));
        Assert.assertEquals("POST Testing",response.jsonPath().getString("body"));
        Assert.assertEquals("11", response.jsonPath().getString("userId"));
        Assert.assertEquals("101", response.jsonPath().getString("id"));


        System.out.println("Status code is: " + response.getStatusCode());

}

    @Test(priority = 4, description = "Test 4")
    public void postRequest2() {
        String requestBody = "{\n" +
                "  \"title\": \"Created by Jmv 2\",\n" +
                "  \"body\": \"POST Testing 2\",\n" +
                "  \"userId\": \"11\" \n}";

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("Created by Jmv 2", response.jsonPath().getString("title"));
        Assert.assertEquals("POST Testing 2", response.jsonPath().getString("body"));
        Assert.assertEquals("11", response.jsonPath().getString("userId"));
        Assert.assertEquals("101", response.jsonPath().getString("id"));
        Object charset = "UTF-8";
        String Object = "charset=utf-8";
        Assert.assertEquals(response.getContentType(), "application/json" + "; "+ Object);


        System.out.println("Status code is: " + response.getStatusCode());

    }
}

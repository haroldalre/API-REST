import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAPITest {

    @Test
    public void testRestAPI() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Get a random user (user ID) and print their email address
        Response userResponse = given()
                .when()
                .get("/users/" + getRandomUserId())
                .then()
                .extract()
                .response();

        String userEmail = userResponse.jsonPath().getString("email");
        System.out.println("User's Email Address: " + userEmail);

        // Get the associated posts from the user and verify valid post IDs
        given()
                .when()
                .get("/posts?userId=" + getRandomUserId())
                .then()
                .body("id", everyItem(greaterThan(0)))
                .body("id", everyItem(lessThan(101));

        // Make a post using the same user ID with a non-empty title and body
        given()
                .body("{ \"userId\": " + getRandomUserId() + ", \"title\": \"Test Title\", \"body\": \"Test Body\" }")
                .when()
                .post("/posts")
                .then()
                .statusCode(anyOf(is(201), is(200))); // Check the documentation for the expected response code
    }

    private int getRandomUserId() {
        return (int) (Math.random() * 10) + 1; // Assuming there are 10 users in the API
    }
}

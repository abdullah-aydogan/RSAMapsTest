package tr.abdullah.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tr.abdullah.resources.APIResources;
import tr.abdullah.resources.TestDataBuild;
import tr.abdullah.resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("{string} isminde {string} dilinde ve {string} adresinde yeni bir mekan olustur")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("{string} API kullanarak {string} HTTP request gonder")
    public void user_calls_with_http_request(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        // System.out.println("Kullanılan API : " + resourceAPI.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        }

        else if(method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }

        else if(method.equalsIgnoreCase("PUT")) {
            response = res.when().put(resourceAPI.getResource());
        }
    }

    @Then("Request sonrası status kodun {int} oldugunu kontrol et")
    public void the_api_call_got_success_with_status_code(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("Donen response uzerinde {string} degerinin {string} oldugunu kontrol et")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }
}
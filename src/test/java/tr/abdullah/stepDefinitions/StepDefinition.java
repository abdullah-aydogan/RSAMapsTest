package tr.abdullah.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.abdullah.resources.APIResources;
import tr.abdullah.resources.TestDataBuild;
import tr.abdullah.resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    private static final Logger logger = LoggerFactory.getLogger(StepDefinition.class);

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;
    static String address;

    @Given("{string} isminde {string} dilinde ve {string} adresinde yeni bir mekan olustur")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        logger.info(name + " isminde " + language + " dilinde ve " + address + " adresinde yeni bir mekan olusturuluyor...");
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("{string} API kullanarak {string} HTTP request gonder")
    public void user_calls_with_http_request(String resource, String method) throws IOException {

        APIResources resourceAPI = APIResources.valueOf(resource);
        // System.out.println("Kullanılan API : " + resourceAPI.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST")) {

            logger.info(resource + " API kullanılarak POST request gonderiliyor...");
            response = res.when().post(resourceAPI.getResource());
        }

        else if(method.equalsIgnoreCase("GET")) {

            logger.info(resource + " API kullanılarak GET request gonderiliyor...");

            response = given().spec(requestSpecification())
                    .queryParam("place_id", place_id).when().get(resourceAPI.getResource());
        }

        else if(method.equalsIgnoreCase("PUT")) {

            logger.info(resource + " API kullanılarak PUT request gonderiliyor...");

            response = given().spec(requestSpecification())
                    .body(data.updatePlacePayload(place_id, address))
                    .when().put(resourceAPI.getResource());
        }

        else if(method.equalsIgnoreCase("DELETE")) {

            logger.info(resource + " API kullanılarak DELETE request gonderiliyor...");

            response = given().spec(requestSpecification())
                    .body(data.deletePlacePayload(place_id))
                    .when().delete(resourceAPI.getResource());
        }
    }

    @Then("Request sonrasi status kodun {int} oldugunu kontrol et")
    public void the_api_call_got_success_with_status_code(int statusCode) {

        logger.info("Request sonrasi status kodun " + statusCode + " oldugu kontrol ediliyor...");
        assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("Donen response uzerinde {string} degerinin {string} oldugunu kontrol et")
    public void in_response_body_is(String keyValue, String expectedValue) {

        logger.info("Donen response uzerinde " + keyValue + " degerinin " + expectedValue + " oldugu kontrol ediliyor...");
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @When("Request sonrasi mekanin ID bilgisini al")
    public void getPlaceID() {

        logger.info("Request sonrasi mekanin ID bilgisi aliniyor...");
        place_id = getJsonPath(response, "place_id");
    }

    @When("Mekan adresini {string} olarak guncellemek icin veri hazirla")
    public void updatePlaceName(String newAddress) {

        logger.info("Mekan adresini " + newAddress + " olarak guncellemek icin veri hazirlaniyor...");
        address = newAddress;
    }
}
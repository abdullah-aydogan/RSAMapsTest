package tr.abdullah.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    public static RequestSpecification request;

    public RequestSpecification requestSpecification() throws IOException {

        if(request == null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam(getGlobalValue("queryParameterName"),
                            getGlobalValue("queryParameterValue"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();

            return request;
        }

        return request;
    }

    public static String getGlobalValue(String key) throws IOException {

        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "/src/test/java/tr/abdullah/resources/global.properties");

        properties.load(fis);

        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {

        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);

        return js.get(key).toString();
    }
}
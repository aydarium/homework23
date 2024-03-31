package ru.aydar.helpers;

import ru.aydar.config.DriverConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BrowserstackVideo {
    static DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());

    public static String videoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .auth().basic(config.userName(), config.accessKey())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}

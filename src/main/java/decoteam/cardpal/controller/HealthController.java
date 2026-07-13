package decoteam.cardpal.controller;

import io.sentry.Sentry;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Controller", description = "This is for testing only")
public class HealthController {

    private final String appEnv;

    public HealthController(@Value("${APP_ENV:local}") String appEnv) {
        this.appEnv = appEnv;
    }

    @GetMapping("/")
    public String root() {
        switch (appEnv) {
            case "local":
                return "Please visit <a href='http://localhost:8080/api-docs'>/api-docs</a> for API documentations";
            case "dev":
                return "Please visit <a href='https://cardpal-be-dev.up.railway.app/api-docs'>/api-docs</a> for API documentations";
            default:
                break;
        }
        return "";
    }

    @GetMapping("/info-sentry")
    public ResponseEntity<?> info() {
        Sentry.logger().info("Hello world from Sentry 111.1");
        return new ResponseEntity<>("Hello world from Sentry", HttpStatus.OK);
    }

    @GetMapping("/error-sentry")
    public String error() {
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return "";
    }

}

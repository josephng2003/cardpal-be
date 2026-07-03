package decoteam.cardpal.controller;

import io.sentry.Sentry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

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

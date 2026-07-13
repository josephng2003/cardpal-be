package decoteam.cardpal.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "APP_ENV=test-env")
@AutoConfigureMockMvc
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAppEnvFromConfiguredProperty() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("test-env"));
    }

    @Test
    void shouldReturnOkForInfoSentryEndpoint() throws Exception {
        mockMvc.perform(get("/info-sentry"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkForErrorSentryEndpoint() throws Exception {
        mockMvc.perform(get("/error-sentry"))
                .andExpect(status().isOk());
    }

}

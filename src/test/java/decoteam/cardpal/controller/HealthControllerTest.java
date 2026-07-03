package decoteam.cardpal.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

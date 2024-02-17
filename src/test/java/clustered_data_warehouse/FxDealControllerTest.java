package clustered_data_warehouse;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

public class FxDealControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FxDealService fxDealService;

    @InjectMocks
    private FxDealController fxDealController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(fxDealController).build();
    }

    @Test
    public void testCreateDeal() throws Exception {
        mockMvc.perform(post("/fxdeals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fromCurrencyISOCode\":\"USD\",\"toCurrencyISOCode\":\"EUR\",\"dealTimestamp\":\"2024-02-18T10:00:00\",\"dealAmount\":1000.00}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreateInvalidDeal_thenShouldReturnBadRequest() throws Exception {
        // Setup
        String dealJson = "{\"fromCurrencyISOCode\":\"12\",\"toCurrencyISOCode\":\"34\",\"dealTimestamp\":\"2024-02-18T10:00:00\",\"dealAmount\":-500.00}";

        // Execution & Verification
        mockMvc.perform(post("/fxdeals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dealJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetDealByIdThatDoesNotExist_thenShouldReturnNotFound() throws Exception {
        // Setup
        long nonExistingId = 999L;
        when(fxDealService.findById(nonExistingId)).thenReturn(Optional.empty());

        // Execution & Verification
        mockMvc.perform(get("/fxdeals/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }
}

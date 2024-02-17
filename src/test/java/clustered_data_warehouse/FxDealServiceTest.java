package clustered_data_warehouse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class FxDealServiceTest {

    @Mock
    private FxDealRepository fxDealRepository;

    @InjectMocks
    private FxDealService fxDealService;

    @Test
    void whenSaveDeal_thenItShouldReturnSavedDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyISOCode("USD");
        fxDeal.setToCurrencyISOCode("EUR");
        fxDeal.setDealTimestamp(LocalDateTime.now());
        fxDeal.setDealAmount(1000.00);

        when(fxDealRepository.save(any(FxDeal.class))).thenReturn(fxDeal);

        FxDeal savedFxDeal = fxDealService.saveDeal(fxDeal);

        assertThat(savedFxDeal).isNotNull();
        verify(fxDealRepository).save(fxDeal);
    }

    @Test
    void whenUpdateNonExistingDeal_thenItShouldThrowException() {
        // Setup
        FxDeal updatedFxDeal = new FxDeal();
        updatedFxDeal.setDealId(999L); // Non-existing ID
        updatedFxDeal.setFromCurrencyISOCode("GBP");
        updatedFxDeal.setToCurrencyISOCode("JPY");
        updatedFxDeal.setDealTimestamp(LocalDateTime.now());
        updatedFxDeal.setDealAmount(2000.00);

        // Execution & Verification
        assertThrows(EntityNotFoundException.class, () -> {
            fxDealService.updateDeal(999L, updatedFxDeal);
        });
    }

    @Test
    void whenCreateNewDeal_thenItShouldBeSaved() {
        // Setup
        FxDeal newFxDeal = new FxDeal();
        newFxDeal.setFromCurrencyISOCode("CAD");
        newFxDeal.setToCurrencyISOCode("INR");
        newFxDeal.setDealTimestamp(LocalDateTime.now());
        newFxDeal.setDealAmount(5000.00);

        when(fxDealRepository.save(any(FxDeal.class))).thenReturn(newFxDeal);

        // Execution
        FxDeal savedFxDeal = fxDealService.saveDeal(newFxDeal);

        // Verification
        assertThat(savedFxDeal).isNotNull();
        verify(fxDealRepository).save(newFxDeal);
    }
}

package clustered_data_warehouse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class FxDealRepositoryTest {

    @Autowired
    private FxDealRepository fxDealRepository;

    @Test
    void whenSaveFxDeal_thenItShouldBeAccessible() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyISOCode("USD");
        fxDeal.setToCurrencyISOCode("EUR");
        fxDeal.setDealTimestamp(LocalDateTime.now());
        fxDeal.setDealAmount(1000.00);

        FxDeal savedFxDeal = fxDealRepository.save(fxDeal);
        assertThat(savedFxDeal).isNotNull();
        assertThat(savedFxDeal.getDealId()).isNotNull();
    }

    @Test
    void whenFindByFromCurrencyISOCode_thenItShouldReturnDeals() {
        // Setup
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyISOCode("USD");
        fxDeal.setToCurrencyISOCode("EUR");
        fxDeal.setDealTimestamp(LocalDateTime.now());
        fxDeal.setDealAmount(1000.00);
        fxDealRepository.save(fxDeal);

        // Execution
        List<FxDeal> result = fxDealRepository.findByFromCurrencyISOCode("USD");

        // Verification
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFromCurrencyISOCode()).isEqualTo("USD");
    }

    @Test
    void whenSaveInvalidFxDeal_thenItShouldFail() {
        // Setup
        FxDeal fxDeal = new FxDeal(); // Missing required fields

        // Execution & Verification
        assertThrows(ConstraintViolationException.class, () -> {
            fxDealRepository.saveAndFlush(fxDeal);
        });
    }
}

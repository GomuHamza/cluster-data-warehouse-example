package clustered_data_warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FxDealRepository extends JpaRepository<FxDeal, Long> {

    List<FxDeal> findByFromCurrencyISOCode(String fromCurrencyISOCode);

}

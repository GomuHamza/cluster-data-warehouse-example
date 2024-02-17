package clustered_data_warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FxDealService {
    
    private final FxDealRepository fxDealRepository;

    @Autowired
    public FxDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FxDeal saveDeal(FxDeal fxDeal) {
        // Add any business logic here before saving the deal
        return fxDealRepository.save(fxDeal);
    }

    public List<FxDeal> getAllDeals() {
        return fxDealRepository.findAll();
    }

    public Optional<FxDeal> findById(Long id) {
        return fxDealRepository.findById(id);
    }

    public boolean deleteDeal(Long id) {
        boolean exists = fxDealRepository.existsById(id);
        if (exists) {
            fxDealRepository.deleteById(id);
        }
        return exists;
    }
    
    public FxDeal updateDeal(Long id, FxDeal updatedDeal) {
        return fxDealRepository.findById(id).map(deal -> {
            deal.setFromCurrencyISOCode(updatedDeal.getFromCurrencyISOCode());
            deal.setToCurrencyISOCode(updatedDeal.getToCurrencyISOCode());
            deal.setDealTimestamp(updatedDeal.getDealTimestamp());
            deal.setDealAmount(updatedDeal.getDealAmount());
            return fxDealRepository.save(deal);
        }).orElseThrow(() -> new EntityNotFoundException("FxDeal not found with id " + id));
    }
}

package com.hamza.clustereddatawarehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fxdeals")
public class FxDealController {

    @Autowired
    private FxDealService fxDealService;

    @Autowired
    public FxDealController(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping
    public ResponseEntity<?> createDeal(@Valid @RequestBody FxDeal fxDeal) {
        FxDeal createdFxDeal = fxDealService.saveDeal(fxDeal);
        return new ResponseEntity<>(createdFxDeal, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FxDeal> getAllDeals() {
        return fxDealService.getAllDeals();
    }

    // GET a single deal by ID
    @GetMapping("/{id}")
    public ResponseEntity<FxDeal> getDealById(@PathVariable Long id) {
        Optional<FxDeal> fxDeal = fxDealService.findById(id);
        return fxDeal.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE a single deal by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeal(@PathVariable Long id, @Valid @RequestBody FxDeal fxDealDetails) {
        FxDeal updatedFxDeal = fxDealService.updateDeal(id, fxDealDetails);
        return updatedFxDeal != null ? ResponseEntity.ok(updatedFxDeal) : ResponseEntity.notFound().build();
    }

    // DELETE a single deal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeal(@PathVariable Long id) {
        boolean wasDeleted = fxDealService.deleteDeal(id);
        return wasDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

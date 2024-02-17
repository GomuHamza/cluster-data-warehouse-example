package com.hamza.clustereddatawarehouse;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deals")
public class FxDeal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;

    @Column(name = "from_currency_iso_code", nullable = false, length = 3)
    @NotNull(message = "From Currency ISO Code is mandatory")
    @Size(min = 3, max = 3, message = "From Currency ISO Code must be 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "From Currency ISO Code must consist of 3 uppercase letters")
    private String fromCurrencyISOCode;

    @Column(name = "to_currency_iso_code", nullable = false, length = 3)
    @NotNull(message = "To Currency ISO Code is mandatory")
    @Size(min = 3, max = 3, message = "To Currency ISO Code must be 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "To Currency ISO Code must consist of 3 uppercase letters")
    private String toCurrencyISOCode;

    @Column(name = "deal_timestamp", nullable = false)
    @NotNull(message = "Deal Timestamp is mandatory")
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount", nullable = false)
    @NotNull(message = "Deal Amount is mandatory")
    @Positive(message = "Deal Amount must be positive")
    private Double dealAmount;

    // Getters
    public Long getDealId() {
        return dealId;
    }

    public String getFromCurrencyISOCode() {
        return fromCurrencyISOCode;
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    // Setters
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
        this.fromCurrencyISOCode = fromCurrencyISOCode;
    }

    public void setToCurrencyISOCode(String toCurrencyISOCode) {
        this.toCurrencyISOCode = toCurrencyISOCode;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }
}

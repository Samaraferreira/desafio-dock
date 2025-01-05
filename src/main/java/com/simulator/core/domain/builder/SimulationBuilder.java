package com.simulator.core.domain.builder;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SimulationBuilder {

    private UUID id;
    private String borrowerId;
    private String lenderId;
    private BigDecimal amount;
    private int installments;
    private double interestRate;
    private BigDecimal totalAmount;
    private BigDecimal totalInterestAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiration;
    private SimulationStatus status;

    public SimulationBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public SimulationBuilder borrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
        return this;
    }

    public SimulationBuilder lenderId(String lenderId) {
        this.lenderId = lenderId;
        return this;
    }

    public SimulationBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SimulationBuilder installments(int installments) {
        this.installments = installments;
        return this;
    }

    public SimulationBuilder interestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public SimulationBuilder totalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public SimulationBuilder totalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
        return this;
    }

    public SimulationBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SimulationBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public SimulationBuilder expiration(LocalDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    public SimulationBuilder status(SimulationStatus status) {
        this.status = status;
        return this;
    }

    public SimulationDomain build() {
        return new SimulationDomain(id, borrowerId, lenderId, amount, installments, interestRate, totalAmount,
                totalInterestAmount, createdAt, updatedAt, expiration, status);
    }
}
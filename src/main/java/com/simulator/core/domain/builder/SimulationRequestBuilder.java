package com.simulator.core.domain.builder;

import com.simulator.core.domain.SimulationRequestDomain;

import java.math.BigDecimal;

public class SimulationRequestBuilder {

    private String borrowerId;
    private String lenderId;
    private BigDecimal amount;
    private int installments;

    public SimulationRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SimulationRequestBuilder borrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
        return this;
    }

    public SimulationRequestBuilder lenderId(String lenderId) {
        this.lenderId = lenderId;
        return this;
    }

    public SimulationRequestBuilder installments(int installments) {
        this.installments = installments;
        return this;
    }


    public SimulationRequestDomain build() {
        return new SimulationRequestDomain(borrowerId, lenderId, amount, installments);
    }
}
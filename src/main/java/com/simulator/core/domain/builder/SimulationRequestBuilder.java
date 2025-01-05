package com.simulator.core.domain.builder;

import com.simulator.core.domain.SimulationRequestDomain;

import java.math.BigDecimal;

public class SimulationRequestBuilder {

    private String clientId;
    private String creditorId;
    private BigDecimal amount;
    private int installments;

    public SimulationRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SimulationRequestBuilder clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public SimulationRequestBuilder creditorId(String creditorId) {
        this.creditorId = creditorId;
        return this;
    }

    public SimulationRequestBuilder installments(int installments) {
        this.installments = installments;
        return this;
    }


    public SimulationRequestDomain build() {
        return new SimulationRequestDomain(clientId, creditorId, amount, installments);
    }
}
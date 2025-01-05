package com.simulator.adapter.entrypoint.controller.dto;

import com.simulator.core.domain.SimulationDomain;

import java.math.BigDecimal;
import java.util.UUID;

public record SimulationResponse(UUID id,
                                 BigDecimal totalAmount,
                                 double interestRate,
                                 BigDecimal totalInterestAmount) {

    public static SimulationResponse fromDomain(SimulationDomain simulationDomain) {
        return new SimulationResponse(
                simulationDomain.id(),
                simulationDomain.totalAmount(),
                simulationDomain.interestRate(),
                simulationDomain.totalInterestAmount()
        );
    }
}

package com.simulator.mock;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.builder.SimulationBuilder;

import java.math.BigDecimal;

public class SimulationMock {


    public static SimulationDomain getMockedSimulation() {
        return new SimulationBuilder()
                .amount(BigDecimal.valueOf(10000))
                .installments(12)
                .interestRate(0.05)
                .totalInterestAmount(BigDecimal.valueOf(272.90))
                .totalAmount(BigDecimal.valueOf(10272.90))
                .build();
    }

    public static SimulationDomain createSimulation(double interestRate,
                                                     BigDecimal totalInterestAmount, BigDecimal totalAmount) {
        return new SimulationBuilder()
                .borrowerId("1222")
                .lenderId("21344")
                .amount(BigDecimal.valueOf(2000))
                .interestRate(interestRate)
                .installments(12)
                .totalInterestAmount(totalInterestAmount)
                .totalAmount(totalAmount)
                .build();
    }
}

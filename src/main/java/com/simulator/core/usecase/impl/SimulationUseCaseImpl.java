package com.simulator.core.usecase.impl;


import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationBuilder;
import com.simulator.core.exception.BusinessException;
import com.simulator.core.gateway.AntiFraudGateway;
import com.simulator.core.gateway.InterestRateGateway;
import com.simulator.core.gateway.NotificationGateway;
import com.simulator.core.gateway.SimulationGateway;
import com.simulator.core.usecase.SimulationUseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimulationUseCaseImpl implements SimulationUseCase {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final NotificationGateway notificationGateway;
    private final SimulationGateway simulationGateway;
    private final InterestRateGateway interestRateGateway;
    private final AntiFraudGateway antiFraudGateway;

    public SimulationUseCaseImpl(NotificationGateway notificationGateway,
                                 SimulationGateway simulationGateway,
                                 InterestRateGateway interestRateGateway,
                                 AntiFraudGateway antiFraudGateway) {
        this.notificationGateway = notificationGateway;
        this.simulationGateway = simulationGateway;
        this.interestRateGateway = interestRateGateway;
        this.antiFraudGateway = antiFraudGateway;
    }

    @Override
    public SimulationDomain create(SimulationRequestDomain simulationRequestDomain) {

        boolean hasFraud = antiFraudGateway.validate(simulationRequestDomain.borrowerId(), simulationRequestDomain.amount());

        if (hasFraud) {
            throw new BusinessException("Simulação não pode ser concluída");
        }

        double interestRate = interestRateGateway.getMonthlyInterestRate(simulationRequestDomain.lenderId());

        BigDecimal totalInterestAmount = calculateTotalInterestAmount(simulationRequestDomain.amount(), interestRate,
                simulationRequestDomain.installments());

        BigDecimal totalAmount = calculateTotalAmount(simulationRequestDomain.amount(),
                totalInterestAmount);

        SimulationDomain simulationDomain = buildSimulationDomain(simulationRequestDomain, totalInterestAmount,
                interestRate, totalAmount);

        simulationGateway.create(simulationDomain);
        notificationGateway.notify(simulationDomain);

        return simulationDomain;
    }

    private BigDecimal calculateTotalInterestAmount(BigDecimal amount, double monthlyRate, int installments) {
        return amount
                .multiply(BigDecimal.valueOf(monthlyRate))
                .multiply(BigDecimal.valueOf(installments));
    }

    private BigDecimal calculateTotalAmount(BigDecimal amount, BigDecimal totalInterestAmount) {
        return setScale(amount.add(totalInterestAmount));
    }

    private SimulationDomain buildSimulationDomain(SimulationRequestDomain simulationRequestDomain,
                                                   BigDecimal totalInterestAmount,
                                                   double creditorInterestRate,
                                                   BigDecimal totalAmount) {
        return new SimulationBuilder()
                .amount(simulationRequestDomain.amount())
                .installments(simulationRequestDomain.installments())
                .totalInterestAmount(totalInterestAmount)
                .interestRate(creditorInterestRate)
                .totalAmount(totalAmount)
                .build();
    }

    private BigDecimal setScale(BigDecimal amount) {
        return amount.setScale(SCALE, ROUNDING_MODE);
    }
}

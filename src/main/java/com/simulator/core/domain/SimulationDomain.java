package com.simulator.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SimulationDomain(
        UUID id,
        String borrowerId,
        String lenderId,
        BigDecimal amount,
        int installments,
        double interestRate,
        BigDecimal totalAmount,
        BigDecimal totalInterestAmount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime expiration,
        SimulationStatus status
) {}
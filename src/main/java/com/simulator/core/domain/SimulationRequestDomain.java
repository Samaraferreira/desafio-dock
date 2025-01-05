package com.simulator.core.domain;

import java.math.BigDecimal;

public record SimulationRequestDomain(
        String borrowerId,
        String lenderId,
        BigDecimal amount,
        int installments
) {}

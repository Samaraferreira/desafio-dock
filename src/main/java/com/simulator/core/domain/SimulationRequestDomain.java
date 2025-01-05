package com.simulator.core.domain;

import java.math.BigDecimal;

public record SimulationRequestDomain(
        String clientId,
        String creditorId,
        BigDecimal amount,
        int installments
) {}

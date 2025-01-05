package com.simulator.infrastructure.entrypoint.controller.dto;

import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationRequestBuilder;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SimulationRequest(
        @NotNull
        String clientId,

        @NotNull
        int installments,

        @NotNull
        @Positive
        BigDecimal amount
) {

        public SimulationRequestDomain toDomain(String creditorId) {
                return new SimulationRequestBuilder()
                        .amount(amount)
                        .installments(installments)
                        .clientId(clientId)
                        .creditorId(creditorId)
                        .build();
        }
}

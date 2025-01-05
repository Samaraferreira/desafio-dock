package com.simulator.adapter.entrypoint.controller.dto;

import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationRequestBuilder;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SimulationRequest(
        @NotNull
        String borrowerId,

        @NotNull
        int installments,

        @NotNull
        @Positive
        BigDecimal amount
) {

        public SimulationRequestDomain toDomain(String lenderId) {
                return new SimulationRequestBuilder()
                        .amount(amount)
                        .installments(installments)
                        .borrowerId(borrowerId)
                        .lenderId(lenderId)
                        .build();
        }
}

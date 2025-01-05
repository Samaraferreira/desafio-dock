package com.simulator.adapter.entrypoint.controller.dto;

import java.time.LocalDateTime;

public record TokenRequest(
        String lenderId,
        String scope,
        LocalDateTime expiration
) {
}

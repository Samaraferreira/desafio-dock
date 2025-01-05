package com.simulator.adapter.entrypoint.controller.dto;

import java.time.LocalDateTime;

public record TokenRequest(
        String creditorId,
        String scope,
        LocalDateTime expiration
) {
}

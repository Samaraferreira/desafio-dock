package com.simulator.infrastructure.rest.antifraud;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AntiFraudClient {

    boolean validate(@RequestParam String borrowerId, @RequestParam BigDecimal amount);
}

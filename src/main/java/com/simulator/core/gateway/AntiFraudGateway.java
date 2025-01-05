package com.simulator.core.gateway;

import java.math.BigDecimal;

public interface AntiFraudGateway {
    boolean validate(String clientId, BigDecimal amount);
}

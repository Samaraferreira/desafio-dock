package com.simulator.infrastructure.gateway;

import com.simulator.core.gateway.AntiFraudGateway;
import com.simulator.infrastructure.rest.antifraud.AntiFraudClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AntiFraudGatewayImpl implements AntiFraudGateway {

    private AntiFraudClient antiFraudClient;

    @Override
    public boolean validate(String clientId, BigDecimal amount) {
        return antiFraudClient.validate(clientId, amount);
    }
}

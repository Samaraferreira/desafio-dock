package com.simulator.adapter.gateway;

import com.simulator.core.gateway.AntiFraudGateway;
import com.simulator.infrastructure.rest.antifraud.AntiFraudClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AntiFraudGatewayImpl implements AntiFraudGateway {

    private AntiFraudClient antiFraudClient;

    public AntiFraudGatewayImpl(@Autowired AntiFraudClient antiFraudClient) {
        this.antiFraudClient = antiFraudClient;
    }

    @Override
    public boolean validate(String borrowerId, BigDecimal amount) {
        return antiFraudClient.validate(borrowerId, amount);
    }
}

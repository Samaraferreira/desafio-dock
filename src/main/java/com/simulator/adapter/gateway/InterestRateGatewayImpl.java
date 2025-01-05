package com.simulator.adapter.gateway;

import com.simulator.core.gateway.InterestRateGateway;
import org.springframework.stereotype.Component;

@Component
public class InterestRateGatewayImpl implements InterestRateGateway {

    public double getMonthlyInterestRate(String creditorId) {
        return 0.04;
    }
}

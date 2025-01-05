package com.simulator.infrastructure.configuration;

import com.simulator.core.gateway.AntiFraudGateway;
import com.simulator.core.gateway.InterestRateGateway;
import com.simulator.core.gateway.NotificationGateway;
import com.simulator.core.gateway.SimulationGateway;
import com.simulator.core.usecase.SimulationUseCase;
import com.simulator.core.usecase.impl.SimulationUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SimulationUseCase addSimulationUseCase(SimulationGateway simulationGateway,
                                                  NotificationGateway notificationGateway,
                                                  InterestRateGateway interestRateGateway,
                                                  AntiFraudGateway antiFraudGateway) {
        return new SimulationUseCaseImpl(notificationGateway, simulationGateway,
                interestRateGateway, antiFraudGateway);
    }
}

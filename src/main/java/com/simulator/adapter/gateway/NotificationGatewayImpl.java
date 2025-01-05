package com.simulator.adapter.gateway;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.gateway.NotificationGateway;
import org.springframework.stereotype.Component;

@Component
public class NotificationGatewayImpl implements NotificationGateway {

    @Override
    public void notify(SimulationDomain simulationDomain) {
        System.out.println("Notificação enviada para a proposta: " + simulationDomain.id());
    }
}

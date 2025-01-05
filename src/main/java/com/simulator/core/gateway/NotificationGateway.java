package com.simulator.core.gateway;

import com.simulator.core.domain.SimulationDomain;

public interface NotificationGateway {

    void notify(SimulationDomain simulationDomain);
}

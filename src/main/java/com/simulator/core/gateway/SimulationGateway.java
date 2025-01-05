package com.simulator.core.gateway;

import com.simulator.core.domain.SimulationDomain;

public interface SimulationGateway {

    SimulationDomain create(SimulationDomain simulationDomain);
}

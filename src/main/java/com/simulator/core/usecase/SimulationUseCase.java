package com.simulator.core.usecase;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationRequestDomain;

public interface SimulationUseCase {
    SimulationDomain create(SimulationRequestDomain simulationRequestDomain);
}

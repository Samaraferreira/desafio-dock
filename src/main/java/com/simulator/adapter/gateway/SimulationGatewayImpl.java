package com.simulator.adapter.gateway;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.gateway.SimulationGateway;
import com.simulator.infrastructure.persistence.dynamodb.repository.SimulationRepository;
import com.simulator.infrastructure.persistence.dynamodb.entity.SimulationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulationGatewayImpl implements SimulationGateway {

    private final SimulationRepository simulationRepository;

    public SimulationGatewayImpl(@Autowired SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public SimulationDomain create(SimulationDomain simulationDomain) {
        SimulationEntity entity = SimulationEntity.fromDomain(simulationDomain);
        return simulationRepository.save(entity).toDomain();
    }
}

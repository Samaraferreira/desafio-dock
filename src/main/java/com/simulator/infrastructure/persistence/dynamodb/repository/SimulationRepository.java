package com.simulator.infrastructure.persistence.dynamodb.repository;

import com.simulator.infrastructure.persistence.dynamodb.entity.SimulationEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SimulationRepository extends CrudRepository<SimulationEntity, String> {
}

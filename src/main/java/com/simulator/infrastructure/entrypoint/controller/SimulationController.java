package com.simulator.infrastructure.entrypoint.controller;

import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.infrastructure.entrypoint.controller.dto.SimulationRequest;
import com.simulator.infrastructure.entrypoint.controller.dto.SimulationResponse;
import com.simulator.core.usecase.SimulationUseCase;
import com.simulator.infrastructure.entrypoint.controller.util.JWTDecode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationUseCase simulationUseCase;

    @Autowired
    public SimulationController(SimulationUseCase simulationUseCase) {
        this.simulationUseCase = simulationUseCase;
    }

    @PostMapping
    public SimulationResponse simulate(@RequestBody @Valid SimulationRequest simulationRequest,
                                       @RequestHeader("Authorization") String authorizationToken) {
        String creditorId = JWTDecode.getCreditorIdFromToken(authorizationToken);
        SimulationRequestDomain simulationDomain = simulationRequest.toDomain(creditorId);
        return SimulationResponse.fromDomain(simulationUseCase.create(simulationDomain));
    }

}

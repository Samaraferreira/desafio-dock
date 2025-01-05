package com.simulator.core.usecase.impl;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationRequestBuilder;
import com.simulator.core.gateway.AntiFraudGateway;
import com.simulator.core.gateway.InterestRateGateway;
import com.simulator.core.gateway.NotificationGateway;
import com.simulator.core.gateway.SimulationGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.simulator.mock.SimulationMock.createSimulation;
import static com.simulator.utils.Utils.round;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SimulationDomainUseCaseImplTest {

    @Mock
    private NotificationGateway notificationGateway;

    @Mock
    private SimulationGateway simulationGateway;

    @Mock
    private InterestRateGateway interestRateGateway;

    @Mock
    private AntiFraudGateway antiFraudGateway;

    @InjectMocks
    private SimulationUseCaseImpl simulationUseCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        simulationGateway = mock(SimulationGateway.class);
        interestRateGateway = mock(InterestRateGateway.class);
        antiFraudGateway = mock(AntiFraudGateway.class);
        simulationUseCase = new SimulationUseCaseImpl(notificationGateway, simulationGateway,
                interestRateGateway, antiFraudGateway);
    }

    @ParameterizedTest
    @MethodSource("provideSimulations")
    public void shouldCreateSimulation(SimulationDomain expected) {
        SimulationRequestDomain mockedRequest = buildMockedRequest(expected);

        when(interestRateGateway.getMonthlyInterestRate(mockedRequest.creditorId()))
                .thenReturn(expected.interestRate());
        when(antiFraudGateway.validate(mockedRequest.clientId(), mockedRequest.amount()))
                .thenReturn(false);

        SimulationDomain result = simulationUseCase.create(mockedRequest);

        assertNotNull(result);
        assertEquals(round(expected.totalAmount()), result.totalAmount());
        assertEquals(round(expected.totalInterestAmount()), result.totalInterestAmount());
        assertEquals(expected.interestRate(), result.interestRate());

        verify(antiFraudGateway).validate(mockedRequest.clientId(), mockedRequest.amount());
        verify(simulationGateway).create(any(SimulationDomain.class));
        verify(notificationGateway).notify(result);
    }

    public static Stream<SimulationDomain> provideSimulations() {
        return Stream.of(
                createSimulation( 0.05,
                        BigDecimal.valueOf(1200), BigDecimal.valueOf(3200)),
                createSimulation( 0.02,
                        BigDecimal.valueOf(480),  BigDecimal.valueOf(2480))
        );
    }

    private static SimulationRequestDomain buildMockedRequest(SimulationDomain expected) {
        return new SimulationRequestBuilder()
                .amount(expected.amount())
                .creditorId(expected.creditorId())
                .installments(expected.installments())
                .clientId(expected.clientId())
                .build();
    }


}
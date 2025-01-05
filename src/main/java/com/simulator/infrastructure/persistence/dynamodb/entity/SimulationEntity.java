package com.simulator.infrastructure.persistence.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationStatus;
import com.simulator.core.domain.builder.SimulationBuilder;
import com.simulator.infrastructure.persistence.dynamodb.converters.BigDecimalConverter;
import com.simulator.infrastructure.persistence.dynamodb.converters.LocalDateTimeConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName = "simulation")
public class SimulationEntity {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String clientId;

    @DynamoDBAttribute
    private String creditorId;

    @DynamoDBAttribute
    private BigDecimal amount;

    @DynamoDBAttribute
    private int installments;

    @DynamoDBAttribute
    private double interestRate;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal totalAmount;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal totalInterestAmount;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime expiration;

    @DynamoDBAttribute
    private String status;

    public SimulationEntity(String clientId, String creditorId, BigDecimal amount, int installments,
                            double interestRate, BigDecimal totalAmount, BigDecimal totalInterestAmount, LocalDateTime expiration) {
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.creditorId = creditorId;
        this.amount = amount;
        this.installments = installments;
        this.interestRate = interestRate;
        this.totalAmount = totalAmount;
        this.totalInterestAmount = totalInterestAmount;
        this.createdAt = LocalDateTime.now();
        this.status = SimulationStatus.CREATED.name();
        this.expiration = expiration;
    }

    public static SimulationEntity fromDomain(SimulationDomain simulationDomain) {
        return new SimulationEntity(
                simulationDomain.clientId(),
                simulationDomain.creditorId(),
                simulationDomain.amount(),
                simulationDomain.installments(),
                simulationDomain.interestRate(),
                simulationDomain.totalAmount(),
                simulationDomain.totalInterestAmount(),
                simulationDomain.expiration()
        );
    }

    public SimulationDomain toDomain() {
        return new SimulationBuilder()
                .id(UUID.fromString(id))
                .amount(amount)
                .clientId(clientId)
                .creditorId(creditorId)
                .installments(installments)
                .interestRate(interestRate)
                .totalAmount(totalAmount)
                .totalInterestAmount(totalInterestAmount)
                .createdAt(createdAt)
                .status(SimulationStatus.valueOf(status))
                .build();
    }
}

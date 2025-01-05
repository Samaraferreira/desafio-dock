# Simulador de empréstimo


## Como executar?

### Requisitos
- Gradle
- AWS CLI
- Docker

### Passo a passo para rodar o projeto

1. Subir container com DynamoDB
```
docker-compose up -d
```

2. Executar aplicação
```
./gradlew bootRun
```

## Requisições - Envio de simulação
Endpoint: /simulationDomain

### Request Body

```
{
	"paymentTerm": 12,
	"birthDate": "2001-12-01",
	"amount": 10000,
	"email": "test@gmail.com"
}
```

### Response Body

```
{
	"totalAmount": 10272.90,
	"monthlyPayment": 856.07,
	"totalInterestAmount": 272.90
}
```

## Explicação sobre a estrutura do projeto e decisões de arquitetura


![Clean Arch](.github/assets/clean-architecture.png)

O projeto segue princípios do Clean Architecture, buscando manter o domínio de negócio isolado, sem dependência das camadas mais externas.
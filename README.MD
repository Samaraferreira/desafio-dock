# Simulador de empréstimo

:heavy_exclamation_mark: Não está 100% funcional

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

## Explicação sobre a estrutura do projeto e decisões de arquitetura


![Clean Arch](.github/assets/clean-architecture.png)

O projeto segue princípios do Clean Architecture, buscando manter o domínio de negócio isolado, sem dependência das camadas mais externas.

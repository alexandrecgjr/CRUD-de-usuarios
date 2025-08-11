# CRUD de Usuários - Automação de Testes

## 📋 Descrição

Este projeto implementa uma automação completa de testes para uma API de gerenciamento de usuários, utilizando as melhores práticas de desenvolvimento e ferramentas modernas de teste.

## 🚀 Tecnologias Utilizadas

- **Java 11** - Linguagem principal
- **Maven** - Gerenciamento de dependências
- **REST Assured** - Framework para testes de API
- **Cucumber** - BDD (Behavior Driven Development)
- **TestNG** - Framework de testes
- **Allure** - Relatórios de teste
- **Jackson** - Serialização/Deserialização JSON

## 📁 Estrutura do Projeto

```
CRUDdeUsuarios/
├── src/
│   ├── main/java/
│   │   ├── client/
│   │   │   └── UserClient.java          # Cliente HTTP para API
│   │   ├── model/
│   │   │   ├── User.java                # Modelo de dados do usuário
│   │   │   ├── UserRequest.java         # DTO para requisições
│   │   │   └── UserResponse.java        # DTO para respostas
│   │   └── service/
│   │       └── UserService.java         # Lógica de negócio
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   ├── CucumberTestRunner.java
│       │   │   └── CucumberNegativeTestRunner.java
│       │   ├── steps/
│       │   │   ├── UserCRUDSteps.java
│       │   │   └── UserNegativeSteps.java
│       │   └── tests/
│       │       ├── BaseTest.java
│       │       ├── UserCRUDTest.java
│       │       ├── UserNegativeTest.java
│       │       └── AllureRestAssuredFilter.java
│       └── resources/
│           ├── features/
│           │   ├── user_crud.feature     # Cenários positivos
│           │   └── user_negative.feature # Cenários negativos
│           ├── testng.xml
│           └── cucumber.properties
├── allure-results/                      # Resultados dos testes
├── pom.xml
└── README.md
```

## 🎯 Funcionalidades Testadas

### Operações CRUD
- ✅ **CREATE** - Criar usuário
- ✅ **READ** - Buscar usuário por ID
- ✅ **UPDATE** - Atualizar dados do usuário
- ✅ **DELETE** - Deletar usuário

### Cenários de Teste
- **Cenários Positivos**: Validação de operações bem-sucedidas
- **Cenários Negativos**: Validação de tratamento de erros
- **Testes de Borda**: Validação com dados extremos
- **Ciclo Completo**: Teste de fluxo completo CRUD

## ⚙️ Configuração

1. **Clone o repositório**
   ```bash
   git clone https://github.com/alexandrecgjr/CRUD-de-usuarios.git
   cd CRUDdeUsuarios
   ```

2. **Instale as dependências**
   ```bash
   mvn clean install
   ```

## 🧪 Executando os Testes

### Executar todos os testes
```bash
mvn clean test
```

### Executar testes específicos
```bash
# Apenas testes positivos
mvn test -Dcucumber.filter.tags="@positive"

# Apenas testes negativos
mvn test -Dcucumber.filter.tags="@negative"

# Apenas testes de criação
mvn test -Dcucumber.filter.tags="@create"

# Testes de smoke
mvn test -Dcucumber.filter.tags="@smoke"
```

### Executar via TestNG
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

## 📊 Relatórios

### Allure Report
Após executar os testes, gere o relatório Allure:

```bash
# Gerar relatório
mvn allure:report

# Abrir relatório no navegador
mvn allure:serve
```

O relatório incluirá:
- Resumo dos testes executados
- Detalhes de cada cenário
- Screenshots e logs
- Métricas de performance
- Análise de falhas

## 🏷️ Tags de Teste

- `@smoke` - Testes essenciais
- `@crud` - Operações CRUD completas
- `@positive` - Cenários positivos
- `@negative` - Cenários negativos
- `@create` - Testes de criação
- `@read` - Testes de leitura
- `@update` - Testes de atualização
- `@delete` - Testes de exclusão
- `@boundary` - Testes de borda
- `@validation` - Validações

## 📝 Modelo de Dados

### User
```json
{
  "id": 1,
  "name": "Nome do Usuário",
  "job": "Profissão",
  "createdAt": "2024-01-01T00:00:00.000Z",
  "updatedAt": "2024-01-01T00:00:00.000Z"
}
```

## 🔧 Configurações

### Cucumber Properties
- Configurações de execução do Cucumber
- Definição de tags e filtros

### TestNG XML
- Configuração de suites de teste
- Parâmetros de execução

## 🚨 Tratamento de Erros

O projeto inclui validações para:
- Usuários inexistentes
- Dados inválidos
- Códigos de status HTTP
- Timeouts de requisição

## 📈 Métricas de Qualidade

- Cobertura de testes
- Taxa de sucesso
- Tempo de execução
- Detecção de regressões

## 👨‍💻 Autor

**Alexandre C**



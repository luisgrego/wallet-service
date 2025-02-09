# Wallet Service - API REST

## Descrição
O Wallet Service é uma API REST desenvolvida em Java 21 utilizando Spring Boot 3.4.2, responsável por gerenciar carteiras digitais de usuários. A API permite operações de criação de carteira, depósitos, saques, transferências e consulta de saldo.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.2
- Spring Data JPA (Hibernate)
- Banco de Dados H2 (para desenvolvimento)
- JUnit 5 + MockMvc (testes)
- Maven (gerenciamento de dependências)
- Tomcat Embedded (servidor web embutido)

## Estrutura do Projeto
wallet-service/  
    src/main/java/com/example/walletservice/  
        controller/ → Controladores da API  
        service/ → Regras de negócio  
        repository/ → Camada de persistência  
        model/ → Modelos das entidades  
        exception/ → Tratamento de exceções  
    src/test/java/com/example/walletservice/ → Testes automatizados  
    pom.xml → Dependências do projeto  
    README.md → Documentação do projeto  
    .gitignore → Arquivos ignorados no versionamento  

## Configuração e Execução

### Clonar o Repositório
git clone https://github.com/seu-usuario/wallet-service.git
cd wallet-service

### Configurar o Banco de Dados
O projeto já vem configurado para rodar com H2 Database. 

### Executar a Aplicação
#### Rodar com Maven
mvn spring-boot:run

#### Gerar e Executar o JAR
mvn clean package
java -jar target/wallet-service.jar


### Acessar o Banco H2 (Modo Desenvolvimento)
Abra o navegador e acesse:

http://localhost:8080/h2-console

Credenciais:
- JDBC URL: `jdbc:h2:mem:walletdb`
- User: `sa`
- Password: *(vazio)*

## Endpoints da API
### Criar uma Carteira
curl -X POST "http://localhost:8080/wallets/create?userId=testUser"

Resposta:
{
  "id": 1,
  "userId": "testUser",
  "balance": 0.0
}


### Depositar Dinheiro
curl -X POST "http://localhost:8080/wallets/deposit?userId=testUser&amount=100"

### Sacar Dinheiro
curl -X POST "http://localhost:8080/wallets/withdraw?userId=testUser&amount=50"

### Consultar Saldo
curl -X GET "http://localhost:8080/wallets/testUser"

### Transferência entre Usuários
curl -X POST "http://localhost:8080/wallets/transfer?fromUserId=testUser&toUserId=anotherUser&amount=30"

## Testes Automatizados
Para rodar os testes unitários e de integração, utilize:  mvn test

Os testes validam:
- Criação de carteira
- Depósitos e saques
- Transferências entre usuários
- Cenários de erro (saldo insuficiente, usuário inexistente)

## Referências
Para mais informações, consulte as seguintes documentações:

- [Documentação oficial do Apache Maven](https://maven.apache.org/guides/index.html)
- [Guia do Plugin Maven do Spring Boot](https://docs.spring.io/spring-boot/3.4.2/maven-plugin)
- [Criando uma imagem OCI](https://docs.spring.io/spring-boot/3.4.2/maven-plugin/build-image.html)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.2/reference/using/devtools.html)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.2/reference/web/servlet.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html#data.sql.jpa-and-spring-data)

## Guias
Os guias a seguir demonstram como utilizar alguns dos recursos deste projeto:

- [Construindo um serviço RESTful](https://spring.io/guides/gs/rest-service/)
- [Servindo conteúdo web com Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Construindo serviços REST com Spring](https://spring.io/guides/tutorials/rest/)
- [Acessando dados com JPA](https://spring.io/guides/gs/accessing-data-jpa/)

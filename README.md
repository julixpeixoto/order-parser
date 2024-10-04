# Order Parser - Projeto de desafio técnico Luizalabs

## Visão Geral
Este projeto foi desenvolvido para resolver o desafio técnico proposto pela LuizaLabs. O objetivo é integrar dois sistemas distintos: um sistema legado que armazena pedidos de forma desnormalizada e um novo sistema que necessita desses dados em formato JSON normalizado.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3
- MySQL
- Docker
- JaCoCo
- Swagger

## Variáveis de Ambiente

As configurações do projeto já possuem um valor padrão, mas podem ser alteradas via variável de ambiente:

| Variável | Descrição                        |
| --- |----------------------------------|
| MYSQL_URL | URL de conexão ao banco de dados |    
| MYSQL_USER | Usuário do banco de dados        |     
| MYSQL_PASSWORD | Senha do banco de dados          |     

## Instruções Docker

### Rodar o Contêiner
Para rodar a aplicação em um contêiner Docker:

```sh
docker-compose up --build
```

## Documentação
A documentação da aplicação está disponível pelo Swagger em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Cobertura de testes 
Para gerar o relatório de cobertura de testes através do JaCoCo execute o seguinte comando:

```sh
mvn clean test jacoco:report
```

O relatório será gerado no diretório target/site/jacoco/index.html

## Instruções de utilização
Após subir a aplicação, siga os seguintes passos para utilizar a API:

### 1. **Processar Arquivo de Pedidos**

#### **Endpoint**

`POST /order-parser/process-file`

#### **Descrição**

Esse endpoint processa um arquivo de pedidos enviado via *multipart form*. O arquivo será interpretado e os pedidos contidos nele serão inseridos no sistema.

#### **Exemplo de Requisição `curl`**

```bash
curl --location 'http://localhost:8080/order-parser/process-file' \
--form 'file=@"/caminho/para/o/arquivo.txt"'
```

### 2. **Obter Todos os Pedidos Processados**

#### **Endpoint**

`GET /order-parser/all`

#### **Descrição**

Esse endpoint retorna todos os pedidos que foram processados e armazenados no sistema, o retorno está paginado em 10 pedidos por consulta.

#### **Exemplo de Requisição `curl`**

```bash
curl --location 'http://localhost:8080/order-parser/all'
```
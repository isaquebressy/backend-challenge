# Invillia recruitment challenge

### Notas da resolução do desafio (em português)
> Infelizmente, não tive tempo suficiente de resolver completamente o desafio. Faltou-me um pouco de organização do tempo que eu tinha disponível e talvez uma abordagem diferente da resolução do problema (talvez uma abordagem top-down fosse melhor nesse caso).

> Algumas coisas gostaria de ter modificado antes da entrega, mas infelizmente não tive como: alterar o status para um tipo `Enum`; criar um arquivo `docker-compose.yml` descrevendo os containeres para a aplicação e para o banco de dados. Acabei baixando um container aqui na minha máquina e configurando na mão mesmo; Adicionar o `spring-security` para aumentar a segurança e trabalhar com tokens `JWT`; utilizar um orquestrador, como o Kubernetes, para balancear os containers.

> Algumas partes da solução precisariam ser revistas, pois não houve um entendimento muito preciso do que estava sendo pedido e acabei, por conta da abordagem escolhida, só percebendo isso mais para o final. Algumas regras como: Pode haver uma ordem sem itens, ou itens sem ordem, pode haver mais de um pagamento para uma ordem, como o reembolso é feito, etc. não ficaram claras durante o processo de desenvolvimento e acabei deixando para resolver mais para frente se houvesse tempo.

> Isaque Bressy
### Fim da nota


[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code

# Agenda API

Esta é uma API para agendamento de barbearia.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Swagger
- PostgreSQL

## Configuração

1. Certifique-se de ter o Java e o PostgreSQL instalados na sua máquina.
2. Clone este repositório: `git clone <URL_DO_REPOSITORIO>`
3. Configure as propriedades do banco de dados no arquivo `application.properties`.
4. Execute o seguinte comando para iniciar a aplicação: `./mvnw spring-boot:run`.

## Endpoints

A API possui os seguintes endpoints:

## Cliente

- `GET /api/clientes`: Retorna todos os clientes.
- `GET /api/clientes/{id}`: Retorna o cliente com o ID especificado.
- `GET /api/clientes/cadastrados/{nome}`: Retorna o Cliente pelo nome
- `POST /api/clientes`: Cria um novo cliente.
- `PUT /api/clientes/{id}`: Atualiza o cliente com o ID especificado.
- `DELETE /api/clientes/{id}`: Exclui o cliente com o ID especificado.

## Barbeiro

- `GET /api/barbeiros`: Retorna todos os barbeiros.
- `GET /api/barbeiros/{id}`: Retorna o barbeiro com o ID especificado.
- `GET /api/barbeiros/cadastrados/{nome}`: Retorna o barbeiro pelo nome
- `POST /api/barbeiros`: Cria um novo barbeiro.
- `PUT /api/barbeiros/{id}`: Atualiza o barbeiro com o ID especificado.
- `DELETE /api/barbeiros/{id}`: Exclui o barbeiro com o ID especificado.

## Agendamento
- `GET /api/agendamentos`: Retorna todos os agendamentos.
- `GET /api/agendamentos/{data}`: Retorna os agendamentos para a data especificada.
- `GET /api/agendamentos/cliente/{nome}`: Retorna os agendamentos para o cliente especificado.
- `GET /api/agendamentos/barbeiro/{nome}`: Retorna os agendamentos para o barbeiro especificado.
- `POST /api/agendamentos`: Cria um novo agendamento.
- `PUT /api/agendamentos/{id}`: Atualiza o agendamento com o ID especificado.
- `DELETE /api/agendamentos/{id}`: Exclui o agendamento com o ID especificado.


## ..

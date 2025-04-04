# Banc

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Java&message=Spring%20Boot&color=8257E5&labelColor=000000" alt="Spring Boot" />
  <img src="https://img.shields.io/static/v1?label=DB&message=H2&color=8257E5&labelColor=000000" alt="H2" />
  <img src="https://img.shields.io/static/v1?label=Messaging&message=Kafka&color=8257E5&labelColor=000000" alt="Kafka" />
</p>

Banc é uma plataforma de pagamentos simplificada feita em Java Spring e microsserviços com Kafka. Nela é possível depositar e realizar transferências de dinheiro entre usuários. Temos 2 tipos de usuários, os comuns e lojistas, ambos têm carteira com dinheiro e realizam transferências entre eles.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [Spring Kafka](https://spring.io/projects/spring-kafka)
- [Docker Compose](https://docs.docker.com/compose/)
- [H2 Database](https://www.h2database.com)
  <br />

## API

- http :8080/transaction value=100.0 payer=1 payee=200
```
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Tue, 05 Mar 2024 19:07:52 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "createdAt": "2024-03-05T16:07:50.749774",
    "id": 20,
    "payee": 2,
    "payer": 1,
    "value": 100.0
}
```

- http :8080/transaction
```
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Tue, 05 Mar 2024 19:08:13 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

[
    {
        "createdAt": "2024-03-05T16:07:50.749774",
        "id": 20,
        "payee": 2,
        "payer": 1,
        "value": 100.0
    }
]
```

# Click counter

![Docker Compose CI](https://github.com/mekhnin/click-counter/actions/workflows/.github/workflows/docker-compose.yml/badge.svg)

Приложение ведёт подсчёт кликов по кнопке на странице http://localhost:8080/.

## Сборка и запуск

```shell
./mvnw clean package
docker-compose up
```

## API Documentation

http://localhost:8080/swagger-ui.htm
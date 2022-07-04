![Docker Compose CI](https://github.com/mekhnin/click-counter/actions/workflows/.github/workflows/docker-compose.yml/badge.svg)

Приложение ведёт подсчёт кликов по кнопке на странице http://localhost:8080/.

## Сборка и запуск
- Компиляция кода и его исполнение c использованием Java 11.
- Сборка сервиса при помощи Apache Maven командой `mvn package`.
- Запуск сервиса осуществляется командой `java -jar click-counter-1.0.0.jar

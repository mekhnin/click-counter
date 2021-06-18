Приложение ведёт подсчёт кликов по кнопке на странице http://localhost:8080/.

## Сборка и запуск
- Компиляция кода и его исполнение c использованием Java 11.
- Сборка сервиса при помощи Apache Maven командой `mvn package`.
- Запуск сервиса осуществляется командой `java -jar click-counter-1.0.0.jar

## Настройка PostgreSQL
```
postgres=# create user client with encrypted password '12345';
postgres=# create database counter;
postgres=# grant all privileges on database counter to client;
```
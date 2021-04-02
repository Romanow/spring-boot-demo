# Spring Boot demo

## Сборка приложения 
```shell script
# запустить PostgreSQL в docker-контейнере
docker-compose up -d postgres

# загружает gradle wrapper 6.8
./gradlew wrapper

# сборка проекта, прогон unit-тестов, запуск приложения
./gradlew clean build bootRun
```

## Этапы демонстрации
1. Пустое веб-приложение. Ветка master.
1. Добавляем две сущности User и Address в БД и CRUD контроллер GET/POST/PATCH/DElETE.
1. Подключается OpenAPI и Spring Boot Actuator (health-чеки и инфо о commit).
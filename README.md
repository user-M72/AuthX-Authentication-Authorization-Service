# 🔐 AuthX — Authentication & Authorization Service

**AuthX** — это backend-сервис аутентификации и авторизации, реализованный на **Spring Boot**.  
Подходит как отдельный **Auth microservice** для подключения к любым backend-приложениям.

Проект демонстрирует production-подход к безопасности, JWT, ролям и управлению сессиями.

---

## 🚀 Возможности

- Регистрация и логин пользователей
- JWT Access Token
- Refresh Token
- Обновление access token
- Logout (инвалидация refresh token)
- Роли пользователей (`USER`, `ADMIN`)
- Защита эндпоинтов
- Rate limiting для защиты от brute-force атак

---

## 🛠 Технологии

- Java 21
- Spring Boot
- Spring Security
- JWT (Nimbus)
- Spring Data JPA (Hibernate)
- PostgreSQL
- Flyway
- MapStruct
- Lombok
- Redis (для refresh tokens / blacklist)
- Docker & Docker Compose
- Gradle

---

## 🧩 Архитектура
com.authx
├─ auth // регистрация, логин, refresh
├─ user // пользователи и роли
├─ token // refresh tokens
├─ security // JWT, filters, config
├─ config // общие конфигурации
└─ common // общие DTO, exception, utils
---

## 🔐 Authentication Flow

1. Пользователь логинится (`/api/auth/login`)
2. Сервер возвращает:
    - `accessToken` (короткоживущий)
    - `refreshToken` (долгоживущий)
3. Access token передаётся в `Authorization: Bearer <token>`
4. При истечении access token:
    - клиент вызывает `/api/auth/refresh`
5. Logout инвалидирует refresh token

---

## 📌 API Endpoints

### Auth

POST /api/auth/register
POST /api/auth/login
POST /api/auth/refresh
POST /api/auth/logout

### Users

GET /api/users/me
GET /api/users (ADMIN)


---

## 📥 Примеры запросов

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@mail.com",
  "password": "password123"
}
```
## 🗄 База данных
- PostgreSQL

- Миграции через Flyway

- UUID как primary key

## 🧪 Тестирование
- Unit tests (Service layer)

- Integration tests (@SpringBootTest)

- Testcontainers (PostgreSQL)
### MYFIN Backend – Microservices (Spring Boot)

MYFIN is a modular, production-ready backend built with Spring Boot and Spring Cloud. It follows a microservices architecture with service discovery, centralized configuration, an API gateway, and separate domain services for customers, admins, and notifications.

#### Modules
- `config-server`: Centralized externalized configuration for all services (files in `config-server/src/main/resources/config-repo/`).
- `eureka-server`: Service discovery registry for all microservices.
- `api-gateway`: Single entry point routing to downstream services.
- `customer-service`: Customer-facing APIs (auth, accounts, transactions, chat, loans).
- `admin-service`: Admin-facing APIs (manage customers, accounts, loans, chat, auth).
- `notification-service`: Outbound notifications (e.g., email), plus logging.

#### Key Features
- Spring Boot + Spring Cloud (Config, Gateway, Netflix Eureka)
- JWT-based authentication and authorization
- Layered architecture (controller → service → repository)
- Global exception handling and consistent API error responses
- OpenAPI/Swagger configuration for interactive API docs

---

### Prerequisites
- JDK 17+ (recommended)
- Maven 3.9+ (or use the Maven Wrapper `mvnw`/`mvnw.cmd` found in each module)
- Git

Optional:
- Docker (if you later containerize services)

---

### Repository Layout
```
MYFIN/
  admin-service/
  api-gateway/
  config-server/
    src/main/resources/config-repo/
      admin-service.yml
      api-gateway.yml
      application.yml
      customer-service.yml
      notification-service.yml
  customer-service/
  eureka-server/
  notification-service/
  README.md
```

---

### Quick Start (Local)

Run the services in this order to satisfy dependencies:
1) `config-server`
2) `eureka-server`
3) `notification-service`
4) `customer-service`
5) `admin-service`
6) `api-gateway`

Using Maven Wrapper on Windows PowerShell from each module directory:
```
./mvnw.cmd spring-boot:run
```
On macOS/Linux:
```
./mvnw spring-boot:run
```

Note: Default ports and profiles are defined in each module’s `application.yml` and, where applicable, in the central config repo under `config-server`.

---

### Build & Test

Build all modules (from repo root):
```
mvn -q -pl admin-service,api-gateway,config-server,customer-service,eureka-server,notification-service -am clean package
```

Or build and run per module:
```
cd customer-service && ./mvnw.cmd clean package
cd ..\admin-service && ./mvnw.cmd clean package
```

Run unit tests:
```
mvn -q test
```

---

### Configuration

Centralized config is stored under `config-server/src/main/resources/config-repo/`. Files are named per application (e.g., `customer-service.yml`). Services read configuration from the config server (bootstrap/process may depend on Spring Cloud Config settings). Update these YAML files to change service-level settings (datasources, security properties, feature flags, etc.).

If you change any `config-repo` file, restart `config-server` and refresh dependent services (or enable Spring Cloud Bus for live refresh if you add it later).

---

### Service Overview

- `customer-service`
  - Endpoints for registration, login (JWT), accounts, transactions, loans, and chat
  - Integrates with `notification-service` for outbound messages

- `admin-service`
  - Endpoints for admin authentication, customer/account management, loan approvals, and chat moderation

- `notification-service`
  - Sends notifications (e.g., email) and stores notification logs

- `api-gateway`
  - Central router for client requests to downstream services

- `eureka-server`
  - Service discovery registry where services register themselves and discover peers

- `config-server`
  - Serves externalized configuration to all services

---

### Security
- JWT is used for stateless auth between clients and services.
- Verify token configuration in each service’s security config and corresponding properties in the central config repo.
- The gateway can be used to centralize authentication/authorization checks if desired.

---

### API Documentation
OpenAPI/Swagger is configured in `customer-service` and `admin-service`. When services are running, navigate to the Swagger UI URL shown in the logs (commonly `/swagger-ui/index.html` or `/swagger-ui.html`). Exact paths depend on your Springdoc configuration.

---

### Development Tips
- Make sure the `config-server` and `eureka-server` are running before starting dependent services.
- Use distinct local ports for each service (see each module’s `application.yml`).
- Keep IDE and build artifacts out of Git (already ignored: `.metadata/`, `.idea/`, `.vscode/`, `**/target/`).

Common commands (Windows PowerShell):
```
cd eureka-server; ./mvnw.cmd spring-boot:run; cd ..
cd config-server; ./mvnw.cmd spring-boot:run; cd ..
cd notification-service; ./mvnw.cmd spring-boot:run; cd ..
cd customer-service; ./mvnw.cmd spring-boot:run; cd ..
cd admin-service; ./mvnw.cmd spring-boot:run; cd ..
cd api-gateway; ./mvnw.cmd spring-boot:run; cd ..
```

---

### Contributing
1) Fork the repo and create a feature branch.
2) Write clear, small commits with descriptive messages.
3) Add tests where feasible and keep build green.
4) Open a pull request with details about your change and testing notes.

---

### License
This project is licensed under the MIT License. You can adapt this as needed.



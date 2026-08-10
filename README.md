# Sistema de Citas Médicas

Proyecto Final del curso **Desarrollo de Servicios Web 2**.

El proyecto implementa un sistema de gestión de citas médicas utilizando una arquitectura basada en microservicios.

## Problemática

La gestión de citas médicas requiere controlar pacientes, especialidades, disponibilidad de médicos, reservas y notificaciones.

La solución divide estas responsabilidades en diferentes microservicios que se comunican mediante APIs HTTP.

## Microservicios

| Microservicio | Puerto | Responsabilidad |
|---|---:|---|
| ms-medico | 8083 | Gestión de horarios y disponibilidad médica |
| ms-cita | 8084 | Gestión y registro de citas |
| ms-paciente | 8085 | Gestión de pacientes |
| ms-especialidad | 8086 | Gestión de especialidades médicas |
| ms-notificacion | 8087 | Registro de notificaciones |

## Componentes de infraestructura

| Componente | Puerto | Función |
|---|---:|---|
| API Gateway | 9090 | Punto de entrada a los microservicios |
| Eureka Server | 8761 | Descubrimiento de servicios |
| Config Server | 8888 | Configuración centralizada |
| Keycloak | 8080 | Autenticación y JWT |
| PostgreSQL | 5432 | Persistencia de datos |

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA
- OpenFeign
- API Gateway
- Eureka
- Config Server
- Keycloak
- PostgreSQL
- Docker
- Docker Compose
- OpenAPI / Swagger

## Comunicación

La comunicación entre microservicios se realiza mediante OpenFeign.

- ms-medico → ms-especialidad
- ms-cita → ms-paciente
- ms-cita → ms-medico
- ms-cita → ms-notificacion

## Flujo principal

1. Se registra un paciente.
2. Se registra una especialidad.
3. Se registra la disponibilidad de un médico.
4. Se solicita una cita.
5. ms-cita valida al paciente.
6. ms-cita valida la disponibilidad del médico.
7. Se registra la cita.
8. Se genera una notificación de confirmación.

## Seguridad

El sistema utiliza Keycloak para autenticación.

El API Gateway valida tokens JWT antes de permitir el acceso a los microservicios.

Una petición sin token devuelve:

`401 Unauthorized`

Una petición con un JWT válido permite acceder a los recursos protegidos.

## Ejecución

El proyecto utiliza Docker Compose.

Desde la raíz del proyecto ejecutar:

```bash
docker compose up --build

Para verificar los contenedores:

docker compose ps
API Gateway

Los servicios pueden ser consumidos mediante:

http://localhost:9090/ms-medico/**
http://localhost:9090/ms-cita/**
http://localhost:9090/ms-paciente/**
http://localhost:9090/ms-especialidad/**
http://localhost:9090/ms-notificacion/**
Contrato de APIs

El contrato OpenAPI se encuentra en:

docs/openapi.yml

El sistema gestiona diferentes códigos HTTP:

200 OK
201 Created
400 Bad Request
401 Unauthorized
404 Not Found
409 Conflict
Arquitectura

El diagrama lógico del sistema se encuentra en:

docs/diagrama-arquitectura.png
Configuración centralizada

Las configuraciones externas de los microservicios se encuentran en el repositorio:

config_final

Estado

Proyecto funcional y desplegado mediante Docker Compose, con comunicación entre microservicios, persistencia, seguridad JWT y documentación OpenAPI.
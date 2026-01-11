# 🏥 Sistema de Gestión de Consultorio Médico – Backend

Backend empresarial desarrollado con **Java 17** y **Spring Boot 3**, orientado a la gestión integral de consultorios médicos. Permite administrar pacientes, citas médicas, personal clínico e historias médicas, con **control de acceso por roles**, **autenticación JWT** y una **arquitectura modular preparada para entornos productivos**.

---

## 🎯 Objetivo del proyecto

Diseñar e implementar un backend **escalable, mantenible y seguro** para la administración de un consultorio médico, aplicando **buenas prácticas de arquitectura**, **separación de responsabilidades** y **seguridad**, simulando un entorno real de gestión clínica moderna.

---

## 🧱 Arquitectura y principios

### Arquitectura por capas
- **Controller** – Exposición de APIs REST
- **Service** – Lógica de negocio
- **Repository** – Persistencia de datos
- **Filter / Security** – Seguridad y autenticación

### Separación clara de responsabilidades
- Entidades (**Model**)
- DTOs (Data Transfer Objects)
- Lógica de negocio
- Validaciones personalizadas
- Manejo centralizado de excepciones

### Principios aplicados
- Clean Code
- Principios SOLID
- RESTful APIs
- Diseño orientado a roles y flujos reales del dominio médico

---

## ⚙️ Stack tecnológico

### Backend
- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Security + JWT** (jjwt 0.11.5)
- **Spring Data JPA (Hibernate)**
- **MapStruct 1.5.5** (Entity ⇆ DTO)
- **Lombok 1.18.30**
- **PostgreSQL**
- **Bean Validation** (validaciones personalizadas)

### Testing
- Spring Boot Test
- Spring Security Test
- **Testcontainers (PostgreSQL)**
- Mockito 4.0.0
- JUnit Jupiter

### Infraestructura
- Maven (gestión de dependencias)
- Docker-ready
- Configuración por perfiles (**dev / prod**)
- Variables de entorno para secretos y credenciales

---

## 🔐 Seguridad

- Autenticación basada en **JWT**
- Autorización por roles:
  - `USER`
  - `ADMIN`
  - `DOCTOR`
  - `PATIENT`
- Protección de endpoints con `@PreAuthorize`
- Filtros de seguridad personalizados
- Manejo centralizado de errores y excepciones

---

## 🗂️ Estructura del proyecto

```text
src/main/java/edu/project/medicalofficemanagement
├── config
├── controller
│   ├── AppointmentController
│   ├── ConsultRoomController
│   ├── DoctorController
│   ├── MedicalRecordController
│   ├── PatientController
│   └── UserController
├── dto
├── model
│   ├── Appointment
│   ├── ConsultRoom
│   ├── Doctor
│   ├── MedicalRecord
│   ├── Patient
│   ├── Role
│   └── User
├── enums
│   ├── role
│   ├── specialization
│   └── status
├── exception
├── filter
├── repository
├── security
├── service
└── validation

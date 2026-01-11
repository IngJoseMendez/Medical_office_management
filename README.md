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
---

## 🔄 Flujo principal de negocio

1. El **Paciente** o **Recepcionista** registra y actualiza los datos del paciente.
2. Se programa una **Cita médica** asignando un **Doctor** y un **Consultorio disponible**.
3. El **Doctor** accede a la cita y registra la consulta en la **Historia Médica**.
4. El sistema:
   - Valida la disponibilidad de horarios
   - Gestiona estados de citas (*programada, en curso, completada, cancelada*)
   - Actualiza historiales médicos
   - Controla el acceso según roles
5. El **Administrador** supervisa las operaciones, gestiona usuarios y configura el sistema.

---

## 📡 API REST (resumen)

- Autenticación y autorización JWT (`/api/auth`)
- Gestión de usuarios y roles
- Gestión de pacientes
- Gestión de doctores y especializaciones
- Programación y seguimiento de citas
- Historias clínicas y registros médicos
- Administración de consultorios
- Control de acceso basado en roles

📄 **La documentación completa de endpoints se encuentra incluida en el repositorio.**

---

## 🏥 Módulos principales

### 👤 Gestión de Pacientes
- Registro y actualización de datos personales
- Consulta de historial médico
- Vinculación con citas y doctores

### 📅 Gestión de Citas
- Programación de citas médicas
- Asignación de doctor y consultorio
- Control de estados y disponibilidad
- Notificaciones y recordatorios

### 🩺 Personal Médico
- Registro de doctores y especialidades
- Gestión de disponibilidad
- Asignación de consultorios

### 📋 Historias Médicas
- Registro de consultas y diagnósticos
- Seguimiento de tratamientos
- Acceso controlado por rol

---

## 🧪 Testing y calidad

- Pruebas unitarias con **JUnit** y **Mockito**
- Pruebas de integración con **Testcontainers (PostgreSQL real)**
- Pruebas de seguridad con **Spring Security Test**
- Validación de DTOs y entidades
- Cobertura de servicios y controladores

---

## 🚀 Despliegue

- Aplicación contenerizable con **Docker**
- Compatible con **Railway**, **Render** y **Heroku**
- Preparado para pipelines **CI/CD**
- Configuración externa mediante **variables de entorno**
- Soporte para perfiles de Spring (`dev`, `prod`)

---

## 👨‍💻 Autor

**José Alberto Méndez Domínguez**  
Estudiante de Ingeniería de Software (8° semestre)  
Backend Developer – Java & Spring Boot  
📍 Santa Marta, Colombia

---

## 📝 Notas técnicas

- Integración optimizada **Lombok + MapStruct** mediante `lombok-mapstruct-binding`
- Validaciones personalizadas con **Bean Validation**
- Manejo centralizado de excepciones
- Serialización eficiente mediante **DTOs**
- Pruebas con contenedores PostgreSQL reales usando **Testcontainers**




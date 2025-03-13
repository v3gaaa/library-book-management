# 📚 Library Book Management System

---

## 👥 Equipo 42
- Sebastian Vega A01637397
- Marcela Beatriz De La Rosa Barrios A01637239
- Ángela Estefanía Aguilar Medina  A01637703
- Axel Daniel Padilla Reyes A01642700
- Diana Nicole Arana Sánchez A01642924

## 🚀 Video Demostracion


---

## 📥 Clonar el Proyecto
```bash
git clone https://github.com/TU_USUARIO/library-book-management.git
cd library-book-management
```

# CORRER SIN BASE DE DATOS

## 🛠 Backend (Spring Boot)

### 1️⃣ Requisitos Previos
Asegúrate de tener instalado:
- Java 17 o superior 
- Maven


### 2️⃣ Script para ignorar la db

Copiar este codigo dentro de:  backen/src/main/java/com/example/library/config/DatabaseConfig.java

```java
package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.library");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}
```

### 3️⃣ Ejecutar el Backend
```bash
cd backend
mvn spring-boot:run
```

Si todo está bien, se mostrara en consola algo asi:

```bash
Tomcat started on port(s): 8080
```

Prueba que la API funciona accediendo a:
http://localhost:8080/api/books

## 🎨 Frontend (React con Vite)

### 1️⃣ Requisitos Previos
Asegúrate de tener instalado:
- Node.js

### 2️⃣ Configurar el Frontend
En el root del proyecto FUERA DE LA CARPETA DE BACKEND correr:
```bash
npm install
```

### 3️⃣ Ejecutar el Frontend
```bash
npm run dev
```

Esto levantará el frontend en http://localhost:5173

## 🧪 Probar la Aplicación Completa
1. Asegúrate de que el backend (`mvn spring-boot:run`) y el frontend (`npm run dev`) están corriendo.
2. Abre el navegador y accede a:
   http://localhost:5173


# CORRER CON BASE DE DATOS

## 🛠 Backend (Spring Boot)

### 1️⃣ Requisitos Previos
Asegúrate de tener instalado:
- Java 17 o superior 
- Maven 
- SQL Server 
- SQL Server Management Studio (SSMS) 

### 2️⃣ Configurar la Base de Datos en SQL Server
1. Abre SQL Server Management Studio (SSMS) y conéctate a `localhost\SQLEXPRESS` o la instancia que se este usando par SQL SERVER.
2. Ejecuta el siguiente SQL para crear la base de datos y la tabla:

```sql
CREATE DATABASE LibraryDB;
USE LibraryDB;

CREATE TABLE books (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    genre NVARCHAR(100) NOT NULL,
    publishedDate DATE NOT NULL,
    isbn NVARCHAR(20) UNIQUE NOT NULL,
    availability BIT NOT NULL DEFAULT 1
);


INSERT INTO books (title, author, genre, publishedDate, isbn, availability)
VALUES 
('The Pragmatic Programmer', 'Andrew Hunt', 'Programming', '1999-10-30', '978-0201616224', 1),
('Clean Code', 'Robert C. Martin', 'Programming', '2008-08-01', '978-0132350884', 1),
('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 'Software Engineering', '1975-01-01', '978-0201835957', 0);
```

### 3️⃣ Configurar el Backend
Edita el archivo `src/main/resources/application.properties` y verifica que tenga la siguiente configuración:

```bash
# Configuración de la base de datos
spring.datasource.url=jdbc:sqlserver://localhost\SQLEXPRESS:1433;databaseName=LibraryDB;encrypt=false
spring.datasource.username=sa
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# Configuración de Hibernate (JPA)
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

📌 **Importante**:
- Reemplaza `TU_CONTRASEÑA` con la contraseña que configuraste para `sa`.
- Si `SQLEXPRESS` no es tu instancia, usa el nombre correcto.

### 4️⃣ Ejecutar el Backend
```bash
cd backend
mvn spring-boot:run
```

Si todo está bien, se mostrara en consola algo asi:

```bash
Tomcat started on port(s): 8080
```

Prueba que la API funciona accediendo a:
http://localhost:8080/api/books

## 🎨 Frontend (React con Vite)

### 1️⃣ Requisitos Previos
Asegúrate de tener instalado:
- Node.js

### 2️⃣ Configurar el Frontend
En el root del proyecto FUERA DE LA CARPETA DE BACKEND correr:
```bash
npm install
```

### 3️⃣ Ejecutar el Frontend
```bash
npm run dev
```

Esto levantará el frontend en http://localhost:5173

## 🧪 Probar la Aplicación Completa
1. Asegúrate de que el backend (`mvn spring-boot:run`) y el frontend (`npm run dev`) están corriendo.
2. Abre el navegador y accede a:
   http://localhost:5173

## 📌 Error que puede surgir

### ❌ Error de conexión a SQL Server
Si ves este error:

Connection refused: No se pudo realizar la conexión TCP/IP al host localhost, puerto 1433

✔ **Solución**:
- Asegúrate de que SQL Server está corriendo (SQL Server Configuration Manager → SQL Server (SQLEXPRESS) → Start).
- Habilita TCP/IP en SQL Server Configuration Manager → Protocols for SQLEXPRESS.
- Reinicia SQL Server después de los cambios.

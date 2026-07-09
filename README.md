# Cajero Automático - Tarea Grupal 2

Aplicación web desarrollada en **Java** utilizando **Jakarta Faces (JSF)** y **Maven**, cuyo propósito es simular las principales operaciones 
de un cajero automático. El sistema permite a los usuarios autenticarse y realizar transacciones bancarias básicas de manera sencilla.


# Descripción

Este proyecto fue desarrollado como parte de la asignatura **Programación Web II**.

El sistema implementa las funcionalidades principales de un cajero automático mediante una interfaz web, 
utilizando Managed Beans para la lógica de negocio y archivos de texto para almacenar la información de los clientes y sus transacciones.


# Funcionalidades

-  Inicio de sesión.
-  Consulta de saldo.
-  Depósito de dinero.
-  Retiro de efectivo.
-  Estado de cuenta.
-  Historial de transacciones.
-  Cierre de sesión.


# Tecnologías utilizadas

- Java
- Maven
- Jakarta Faces (JSF)
- CDI (Contexts and Dependency Injection)
- XHTML
- CSS
- Apache Tomcat
- IntelliJ IDEA


# Estructura del proyecto


src
│
├── main
│   ├── java
│   │   └── com.example.cajeroautomatico
│   │       ├── Bean
│   │       └── data
│   │
│   └── webapp
│       ├── resources
│       ├── WEB-INF
│       └── páginas XHTML
│
└── pom.xml



#Principales clases

#Beans

- LoginBean
- DepositoBean
- RetiroBean
- ConsultarSaldoBean
- EstadoCuentaBean

# Clases de datos

- Cliente
- Transaccion
- ArchivoClientes
- ArchivoEstadoCuenta
- ArchivoHistorial


# Requisitos

Antes de ejecutar el proyecto es necesario contar con:

- JDK 8 o superior
- Apache Maven
- Apache Tomcat 10 o compatible
- IntelliJ IDEA o NetBeans


# Instalación

1. Clonar el repositorio.

```bash
git clone https://github.com/GerardoArturoGalindoGarcia/Tarea_Grupal_2PW2_Grupo_5.git
```

2. Entrar al proyecto.

```bash
cd Tarea_Grupal_2PW2_Grupo_5
```

3. Compilar el proyecto.

```bash
mvn clean install
```

4. Desplegar el archivo `.war` en Apache Tomcat.

5. Abrir el navegador y acceder a:

```
http://localhost:8080/CajeroAutomatico
```

---

#Pantallas principales

- Login
- Consulta de saldo
- Depósito
- Retiro
- Estado de cuenta

---

# Archivos de datos

La información utilizada por la aplicación se almacena en:

```
WEB-INF/

cliente.txt
HISTORIAL.TXT
```

Estos archivos contienen la información de clientes y el historial de movimientos realizados.

---

# Integrantes

Agregar los nombres de los integrantes del grupo.

- William Eduardo Banegas
- Gerardo Arturo Galindo
- Ricardo Andres
- katherine Marisol Villanueva
- Nathaly Melissa Aguilar

---

#Objetivos del proyecto

- Aplicar los conceptos de Programación Web II.
- Implementar una aplicación utilizando JSF.
- Desarrollar la lógica de negocio mediante Beans.
- Gestionar operaciones bancarias simuladas.
- Comprender la interacción entre la interfaz web y la lógica de negocio.

---

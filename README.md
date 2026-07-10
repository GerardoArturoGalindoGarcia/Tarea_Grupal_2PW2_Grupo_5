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

- William Eduardo Banegas
- Gerardo Arturo Galindo
- Ricardo Andres
- katherine Marisol Villanueva
- Nathaly Melissa Aguilar

---

#Usuarios 

*Estos son los usuarios que estan registrados en el proyecto con su respectivo pin*

usuario1,1111,William Eduardo Banegas Cruz

usuario2,2222,Gerardo Arturo Galindo Garcia

usuario3,3333,Katherine Marisol Villanueva Alvarado

usuario4,4444,Mathaly Melissa Aguilar Gomez

usuario5,5555,Ricardo Andres Montes Jovel

---

#Objetivos del proyecto

- Aplicar los conceptos de Programación Web II.
- Implementar una aplicación utilizando JSF.
- Desarrollar la lógica de negocio mediante Beans.
- Gestionar operaciones bancarias simuladas.
- Comprender la interacción entre la interfaz web y la lógica de negocio.

---

#Imagenes

LOGIN

<img width="403" height="608" alt="image" src="https://github.com/user-attachments/assets/77488713-56a1-4e80-a337-2c87ed797c06" />

MENU PRINCIPAL

<img width="377" height="619" alt="image" src="https://github.com/user-attachments/assets/e6a5deca-3fca-49d2-ac09-a416b92c9bfd" />

RETIRO

<img width="442" height="556" alt="image" src="https://github.com/user-attachments/assets/86e31445-ac95-46cc-8cc3-6169e178deee" />

DEPOSITO

<img width="430" height="561" alt="image" src="https://github.com/user-attachments/assets/23bdbfec-3cd7-4f97-a73d-050f654f9d8f" />

CONSULTA DE SALDO

<img width="469" height="503" alt="image" src="https://github.com/user-attachments/assets/2df12f6b-1320-4e75-9c20-2882ada1a700" />

ESTADO DE CUENTA

<img width="397" height="645" alt="image" src="https://github.com/user-attachments/assets/e4610954-dd3d-4443-824c-49a261d66000" />











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

#Usuarios

<usuario1,1111,William Eduardo Banegas Cruz,100001,2500.50,2026-01-15 10:30:00>
usuario2,2222,Gerardo Arturo Galindo Garcia,100002,1850.75,2026-01-14 14:45:00
usuario3,3333,Katherine Marisol Villanueva Alvarado,100003,3200.00,2026-01-13 09:15:00
usuario4,4444,Mathaly Melissa Aguilar Gomez,100004,1500.25,2026-01-12 16:20:00
usuario5,5555,Ricardo Andres Montes Jovel,100005,4100.80,2026-01-11 11:50:00

---

#Objetivos del proyecto

- Aplicar los conceptos de Programación Web II.
- Implementar una aplicación utilizando JSF.
- Desarrollar la lógica de negocio mediante Beans.
- Gestionar operaciones bancarias simuladas.
- Comprender la interacción entre la interfaz web y la lógica de negocio.

---

#Imagenes

<img width="822" height="552" alt="image" src="https://github.com/user-attachments/assets/876d5ec0-38c0-4993-af22-96c7337380d1" />
<img width="1602" height="620" alt="image" src="https://github.com/user-attachments/assets/7768a115-d117-4827-82b2-a44c6fce1f80" />
<img width="1862" height="607" alt="image" src="https://github.com/user-attachments/assets/5407b342-7d95-4c2d-badd-1550ef8cfdce" />
<img width="1860" height="632" alt="image" src="https://github.com/user-attachments/assets/cdeb7ca6-cad6-4cd0-acc9-bca8bd0bc3ef" />
<img width="1852" height="497" alt="image" src="https://github.com/user-attachments/assets/f229ac8b-3d6c-4f36-a198-36010e404aa2" />
<img width="1862" height="772" alt="image" src="https://github.com/user-attachments/assets/d4a16e79-1e65-4881-a735-4351bb6c739d" />







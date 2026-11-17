# User DAO CRUD using HikariCP

A complete Java + JDBC + MySQL example that uses **HikariCP** for database connection pooling and a DAO class for Create, Read, Update and Delete operations.

## Requirements
- JDK 17 or later
- Maven 3.8+
- MySQL 8.x (or XAMPP MariaDB/MySQL)

## 1. Create the database

Open phpMyAdmin or MySQL command line and run:

```sql
CREATE DATABASE IF NOT EXISTS dao_demo;
USE dao_demo;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);
```

The same SQL is available in `src/main/resources/schema.sql`.

## 2. Configure the database

The application reads these environment variables:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/dao_demo?useSSL=false&serverTimezone=UTC` |
| `DB_USER` | `root` |
| `DB_PASSWORD` | empty |

For Windows PowerShell, set them for the current terminal if your MySQL credentials differ:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/dao_demo?useSSL=false&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="your_password"
```

If using XAMPP with the default root account and blank password, the defaults work.

## 3. Run in VS Code / IntelliJ

Open the `hikaricp-user-dao` folder as a Maven project. Ensure MySQL is running, then execute:

```bash
mvn clean compile
mvn exec:java
```

The main method demonstrates all DAO operations:
1. Insert a user
2. Read the inserted user by ID
3. Update the user's name
4. Display all users
5. Delete the inserted user

The program prints each result. Refresh the `users` table in phpMyAdmin to verify database changes. The demo deletes the sample row at the end; comment out the `delete()` demonstration in `Main.java` if you want to keep it in the table.

## Project structure

```text
hikaricp-user-dao/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/prajwal/daoapp/
        │   ├── Main.java
        │   ├── User.java
        │   ├── UserDAO.java
        │   └── DBConnection.java
        └── resources/
            └── schema.sql
```

## Concepts used
- **Model class:** `User` stores `id`, `name` and `email`.
- **DAO class:** `UserDAO` contains database CRUD methods.
- **HikariCP:** manages and reuses database connections from a connection pool.
- **PreparedStatement:** safely passes values to SQL statements and helps prevent SQL injection.
- **try-with-resources:** closes JDBC connections, statements and result sets automatically.

## Common errors
- `Communications link failure`: start MySQL in XAMPP or MySQL service and check port 3306.
- `Access denied for user`: set the correct `DB_USER` and `DB_PASSWORD`.
- `Unknown database 'dao_demo'`: execute the SQL in Step 1.
- `mvn is not recognized`: install Maven and add its `bin` directory to PATH, or run Maven from IntelliJ's Maven panel.

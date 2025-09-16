# Todo Application

A simple command-line Todo application built with Java and SQLite.

## Features

- Add a new task
- View all tasks
- Mark a task as Done or Pending
- Edit a task's title, description, or due date
- Delete a task

## How to run the project

1.  **Prerequisites:**
    *   Java Development Kit (JDK) installed
    *   SQLite JDBC driver (`sqlite-jdbc-3.50.3.0.jar` is included in the project)

2.  **Compilation:**
    Open a terminal in the project's root directory and run the following command to compile the Java source files:
    ```bash
    javac -cp sqlite-jdbc-3.50.3.0.jar src/*.java -d out
    ```

3.  **Execution:**
    After successful compilation, run the application with the following command:
    ```bash
    java -cp out:sqlite-jdbc-3.50.3.0.jar app
    ```

## Database Schema

The application uses a SQLite database named `TasksDB.sqlite` with a single table `tasks`.

**`tasks` table schema:**

| Column      | Type    | Description                |
|-------------|---------|----------------------------|
| `id`        | INTEGER | Primary Key, Auto-increment|
| `title`     | TEXT    | Title of the task          |
| `description`| TEXT    | Description of the task    |
| `duedate`   | TEXT    | Due date of the task       |
| `status`    | TEXT    | Status of the task (e.g., "Pending", "Done") |
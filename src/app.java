import java.util.ArrayList;
import java.util.Scanner;

public class app {
    static DatabaseOperation dbo = new DatabaseOperation();

    public static void addTask(Scanner input) {
        System.out.print("\nEnter title: ");
        String title = input.nextLine();
        System.out.print("\nEnter description: ");
        String description = input.nextLine();
        System.out.print("\nEnter due date: ");
        String dueDate = input.nextLine();
        Task task = new Task(title,description,dueDate);
        dbo.insertTask(task);
    }
    public static void displayAllTasks() {
        ArrayList<Task> displayedTasks = dbo.getTasks();
        if (displayedTasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println(" ID | Title | Description | Due Date | Status");
        for(Task t: displayedTasks) {
            System.out.printf(" %d | %s | %s |%s | %s \n", t.getId(), t.getTitle(), t.getDescription(), t.getDueDate(), t.getStatus());
        }
    }
    public static void deleteTask(Scanner input) {
        System.out.print("Enter task ID: ");
        int id = input.nextInt();
        dbo.DeleteTask(id);
    }
    public static void changeStatus(Scanner input) {
        System.out.print("\nEnter ID: ");
        int id = input.nextInt();
        Task task = dbo.ReadTask(id);
        if(task != null) {
            if(task.getStatus().equals("Pending")) {
                dbo.editTask(id,"status","Done");
            } else {
                dbo.editTask(id, "status", "Pending");
            }
        }else {
            System.out.println("No such task found.");
        }
    }

    public static void editTask(Scanner input) {
        System.out.print("\nEnter ID: ");
        int id = input.nextInt();
        Task task = dbo.ReadTask(id);
        if(task != null) {
            System.out.println("ID | Title | Description | Due Date | Status");
            System.out.printf(" %d | %s | %s | %s | %s \n", id, task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus());
        }
        else {
            System.out.println("ID NOT FOUND");
            return;
        }

        System.out.println("****************EDIT TASK****************");
        System.out.println("1. Edit Title");
        System.out.println("2. Edit Description");
        System.out.println("3. Edit Due Date");
        System.out.println("*****************************************");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch(choice) {
            case 1:
                System.out.println("Current Title: " + task.getTitle());
                System.out.print("\nEnter New Title: ");
                String newTitle = input.nextLine();
                dbo.editTask(id,"title",newTitle);
                break;
            case 2:
                System.out.println("Current Description: " + task.getDescription());
                System.out.print("\nEnter New Description: ");
                String newDescription = input.nextLine();
                dbo.editTask(id,"description",newDescription);
                break;
            case 3:
                System.out.println("Current Due Date: " + task.getDueDate());
                System.out.print("\nEnter New Due Date: ");
                String newDueDate = input.nextLine();
                dbo.editTask(id,"dueDate",newDueDate);
                break;
            default:
                System.out.println("Wrong choice");
        }
    }
    public static void main(String[] args)  {
        Scanner input = new Scanner(System.in);

        do{
            System.out.println("\n***************TODO***************");
            System.out.println("1. Add a task");
            System.out.println("2. Show all tasks");
            System.out.println("3. Mark as Done/Undone");
            System.out.println("4. Edit a task");
            System.out.println("5. Delete a task");
            System.out.println("6. Exit");
            System.out.println("***********************************");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    addTask(input);
                    break;
                case 2:
                    displayAllTasks();
                    break;
                case 3:
                    changeStatus(input);
                    break;
                case 4:
                    editTask(input);
                    break;
                case 5:
                    deleteTask(input);
                    break;
                case 6:
                    DatabaseConnection.closeConnection();
                    System.exit(0);
                default:
                    System.out.println("Wrong choice");
            }
        }while (true);
    }
}
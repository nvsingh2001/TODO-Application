import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class app {
    final private static ArrayList<Task> tasks;

    static {
        try {
            tasks = FileHandler.load();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int id;

    static {
        try {
            id = FileHandler.getID();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTask(Scanner input) {
        System.out.print("\nEnter title: ");
        String title = input.nextLine();
        System.out.print("\nEnter description: ");
        String description = input.nextLine();
        System.out.print("\nEnter due date: ");
        String dueDate = input.nextLine();
        Task task = new Task(id++,title,description,dueDate);
        tasks.add(task);
    }
    public static Task searchTask(int id) {
        if(id >= tasks.size() || id < 0) {
            return null;
        }
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    public static void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println(" ID | Title | Description | Due Date | Status");
        for(Task t: tasks) {
            System.out.printf(" %d | %s | %s |%s | %s \n", t.getId(), t.getTitle(), t.getDescription(), t.getDueDate(), t.getStatus());
        }
    }
    public static void deleteTask(Scanner input) {
        if(tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.print("Enter task ID: ");
        Task task = searchTask(input.nextInt());
        if(task != null) {
            tasks.remove(task);
        }
        else {
            System.out.println("ID NOT FOUND");
        }

    }
    public static void changeStatus(Scanner input) {
        if(tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.print("\nEnter ID: ");
        int id = input.nextInt();
        Task t = searchTask(id);
        if(t != null) {
            if(t.getStatus().equals("Pending")) {
                t.setStatus("Done");
            }else  {
                t.setStatus("Pending");
            }
            System.out.println("Changed status to " + t.getStatus());
        }else {
            System.out.println("ID NOT FOUND");
        }
    }

    public static void editTask(Scanner input) {
        if(tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.print("\nEnter ID: ");
        int id = input.nextInt();
        Task task = searchTask(id);
        if(task != null) {
            System.out.println("ID | Title | Description | Due Date | Status");
            System.out.printf(" %d | %s | %s | %s | %s ", id, task.getTitle(), task.getDescription(), task.getDueDate(), task.getStatus());
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
        int choice = input.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Current Title: " + task.getTitle());
                System.out.print("\nEnter New Title: ");
                String newTitle = input.nextLine();
                task.setTitle(newTitle);
                System.out.println("Title Changed Successfully");
                break;
            case 2:
                System.out.println("Current Description: " + task.getDescription());
                System.out.print("\nEnter New Description: ");
                String newDescription = input.nextLine();
                task.setDescription(newDescription);
                System.out.println("Description Changed Successfully");
                break;
            case 3:
                System.out.println("Current Due Date: " + task.getDueDate());
                System.out.print("\nEnter New Due Date: ");
                String newDueDate = input.nextLine();
                task.setDueDate(newDueDate);
                System.out.println("Due Date Changed Successfully");
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
                    FileHandler.save(tasks);
                    FileHandler.saveID(id);
                    System.exit(0);
                default:
                    System.out.println("Wrong choice");
            }
        }while (true);
    }
}
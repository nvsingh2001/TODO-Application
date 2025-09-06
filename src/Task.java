import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private final String title;
    private final String description;
    private final String DueDate;
    private final String Status;

    public Task(int id, String title, String description, String DueDate, String Status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.DueDate = DueDate;
        this.Status = Status;
    }

    public Task(String title, String description, String DueDate) {
        this.title = title;
        this.description = description;
        this.DueDate = DueDate;
        this.Status = "Pending";
    }

    public String getStatus() {
        return this.Status;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDueDate() {
        return this.DueDate;
    }
}

import java.io.Serializable;

public class Task implements Serializable {
    final private int id;
    private String title;
    private String description;
    private String DueDate;
    private String Status;

    public Task(int id, String title, String description, String DueDate) {
        this.id = id;
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

    public void setStatus(String status) {
        this.Status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }
}

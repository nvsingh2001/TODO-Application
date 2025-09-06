import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseOperation {

    public void insertTask(Task task){
        String insert = "INSERT INTO tasks (title, description, duedate, status) VALUES (?, ?, ?, ?)";

        try(PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(insert)){
            pst.setString(1,task.getTitle());
            pst.setString(2,task.getDescription());
            pst.setString(3,task.getDueDate());
            pst.setString(4,task.getStatus());
            pst.executeUpdate();

            System.out.println("Inserted successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getTasks(){
        String select = "SELECT * FROM tasks";
        try(Statement smt = DatabaseConnection.getConnection().createStatement()){
            ResultSet rs = smt.executeQuery(select);
            ArrayList<Task> tasks = new ArrayList<>();
            while(rs.next()){
                Task task = new Task (rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getString("duedate"),rs.getString("status"));
                tasks.add(task);
            }
            rs.close();
            return tasks;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void DeleteTask(int id){
        String delete = "DELETE FROM tasks WHERE id = ?";
        try(PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(delete)){
            pst.setInt(1,id);
            int row = pst.executeUpdate();
            if (row > 0){
                System.out.println("Deleted Successfully");
            } else  {
                System.out.println("ID doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task ReadTask(int id){
        String select = "SELECT * FROM tasks WHERE id = " + id;
        try(Statement smt = DatabaseConnection.getConnection().createStatement()){
            ResultSet rs = smt.executeQuery(select);
            Task t = new Task (rs.getInt("id"),rs.getString("title"),rs.getString("description"),rs.getString("duedate"),rs.getString("status"));
            rs.close();
            return t;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void editTask(int id, String attribute, String value){
        String update = "UPDATE tasks SET " + attribute + " = ? WHERE id = ?";
        try(PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(update)){
            pst.setString(1,value);
            pst.setInt(2,id);
            int row = pst.executeUpdate();
            if (row > 0){
                System.out.println("Updated successfully");
            }
            else {
                System.out.println("ID doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

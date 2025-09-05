import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final static String fileName = "Data.txt";
    private final static String idFileName = "id.txt";

    public static void save(ArrayList<Task> tasks) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(tasks);
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> load() throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if(file.exists() && file.length() > 0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
                return  (ArrayList<Task>) ois.readObject();
            } catch(IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    public static void saveID(int id){
        try(FileWriter writer = new FileWriter(idFileName)){
            writer.write(Integer.toString(id));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int getID(){
        File file = new File(idFileName);
        int id = 0;
        if(file.exists() && file.length() > 0){
            try{
                Scanner reader = new Scanner(file);
                while(reader.hasNextInt()){
                    id = Integer.parseInt(reader.nextLine());
                }
            }
            catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return id;
    }
}

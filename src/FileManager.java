import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileManager
{
    public static void Save(ToDo list)
    {
        String fileName = list.getTitle() + ".txt";
        StringBuilder content = new StringBuilder();
        for(Task t : list.getList())
        {
            content.append(t.title()).append(": ").append(t.description()).append("\n");
        }

        try (FileWriter writer = new FileWriter("ToDo List Saves/" + fileName)) {
            writer.write(content.toString());
            System.out.println("File saved successfully in the active directory: " + fileName);
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static ToDo Load(String fileName)
    {
        return null;
    }
}

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager
{
    public static void Save(ToDo list)
    {
        StringBuilder content = new StringBuilder();
        for(Task t : list.getList())
        {
            content.append(t.title()).append(" : ").append(t.description()).append("\n");
        }
        try (FileWriter writer = new FileWriter("ToDo List Saves/" + list.getTitle() + ".txt"))
        {
            writer.write(content.toString());
            System.out.println("To Do list saved successfully as a txt file");
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static void Save(ToDo list, String fileName)
    {
        StringBuilder content = new StringBuilder();
        for(Task t : list.getList())
        {
            content.append(t.title()).append(" : ").append(t.description()).append("\n");
        }
        try (FileWriter writer = new FileWriter("ToDo List Saves/" + fileName + ".txt"))
        {
            writer.write(content.toString());
            System.out.println("To Do list saved successfully as a txt file");
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static ToDo Load(String fileName)
    {
        String content;
        try(FileReader reader = new FileReader("ToDo List Saves/" + fileName + ".txt"))
        {
            StringBuilder contentBuild = new StringBuilder();
            int i = 0;
            while ((i = reader.read()) != -1)
            {
                contentBuild.append((char) i);
            }
            content = contentBuild.toString();
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            return null;
        }
        String[] Entries = content.split("\n");
        Task[] list = new Task[Entries.length];
        int i = 0;
        for(String Entry : Entries)
        {
            String[] item = Entry.split(" : ");
            Task task = new Task(item[0], item[1]);
            list[i] = task;
            i++;
        }
        return new QueueList(list);
    }
    public static void ClearSaves()
    {
        File directory = new File("ToDo List Saves");
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files)
        {
            if (!file.delete())
            {
                System.err.println("Failed to delete file: " + file.getName());
            }
        }

    }
}

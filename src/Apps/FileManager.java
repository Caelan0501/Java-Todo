package Apps;

import ToDo.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

public class FileManager
{
    public static String DefaultDestination = System.getProperty("user.dir") + "\\ToDo List Saves\\";


    public static boolean Save(ToDo list) { return Save(list, list.getTitle(), DefaultDestination); }
    public static boolean Save(ToDo list, String fileName) { return Save(list, fileName, DefaultDestination); }
    public static boolean Save(ToDo list, String fileName, String Destination)
    {
        try (FileWriter writer = new FileWriter(Destination + fileName + ".txt")) { writer.write(list.toString()); }
        catch (IOException e) { return false; }
        return true;
    }

    public static ToDo Load(String fileName)
    {
        String content;
        try(FileReader reader = new FileReader(DefaultDestination + fileName + ".txt"))
        {
            StringBuilder contentBuild = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) contentBuild.append((char) i);
            content = contentBuild.toString();
        }
        catch (IOException e) { return null; }

        String[] Entries = content.split("\n");
        Task[] list = new Task[Entries.length - 1];
        int i = 0;
        for(String Entry : Entries)
        {
            if (!Entry.contains(" : ")) continue;
            String[] item = Entry.split(" : ");
            Task task = new Task(item[0], item[1]);
            list[i] = task;
            i++;
        }
        String type = Entries[0];
        if (type.equals("QueueList")) return new QueueList(list, fileName);
        else if (type.equals("PriorityList")) return new PriorityList(list);
        else return null;
    }

    public static void RemoveSave(String fileName)
    {
        File directory = new File(DefaultDestination);
        File[] files = directory.listFiles();
        if (files != null)
            for (File file : files)
                if (file.getName().contentEquals(fileName + ".txt"))
                    if (!file.delete())
                        System.err.println("Failed to delete file: " + file.getName());
    }

    public static void ClearSaves()
    {
        File directory = new File(DefaultDestination);
        File[] files = directory.listFiles();
        if (files != null) for (File file : files) if (!file.delete()) System.err.println("Failed to delete file: " + file.getName());
    }
}
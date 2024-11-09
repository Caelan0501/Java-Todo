package Tests;

import Apps.FileManager;
import ToDo.QueueList;
import ToDo.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTests
{
    static String DefaultDestination = System.getProperty("user.dir") + "\\ToDo List Saves\\";

    @BeforeEach
    void ClearTestDirectory()
    {
        File Directory = new File(DefaultDestination);
        File[] files = Directory.listFiles();
        if (files != null) for (File file : files) if (!file.delete()) System.err.println("Failed to delete file: " + file.getName());
    }

    QueueList SetUpQueueList(int TestNumber)
    {
        QueueList list = new QueueList("Test" + TestNumber);
        list.AddTask(new Task("1", "1"));
        list.AddTask(new Task("2", "2"));
        list.AddTask(new Task("3", "3"));
        list.AddTask(new Task("4", "4"));
        list.AddTask(new Task("5", "5"));
        return list;
    }

    @Test
    @DisplayName("Save")
    void Save()
    {
        assertTrue(FileManager.Save(SetUpQueueList(1)));
        File file = new File(DefaultDestination + "Test1.txt");
        assertTrue(file.exists());
    }
    @Test
    void Load()
    {
        QueueList list = SetUpQueueList(1);
        assertTrue(FileManager.Save(list));
        QueueList result = (QueueList) FileManager.Load("Test1");
        assertNotNull(result);
        assertEquals(result.toString(), list.toString());
    }

    @Test
    void Remove()
    {
        assertTrue(FileManager.Save(SetUpQueueList(1)));
        assertTrue(FileManager.Save(SetUpQueueList(2)));
        assertTrue(FileManager.Save(SetUpQueueList(3)));

        FileManager.RemoveSave("Test2");

        QueueList result1 = (QueueList) FileManager.Load("Test1");
        QueueList result2 = (QueueList) FileManager.Load("Test2");
        QueueList result3 = (QueueList) FileManager.Load("Test3");

        assertNotNull(result1);
        assertNull(result2);
        assertNotNull(result3);
    }

    @Test
    void Clear()
    {
        assertTrue(FileManager.Save(SetUpQueueList(1)));
        assertTrue(FileManager.Save(SetUpQueueList(2)));
        assertTrue(FileManager.Save(SetUpQueueList(3)));

        FileManager.ClearSaves();

        QueueList result1 = (QueueList) FileManager.Load("Test1");
        QueueList result2 = (QueueList) FileManager.Load("Test2");
        QueueList result3 = (QueueList) FileManager.Load("Test3");

        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }
}

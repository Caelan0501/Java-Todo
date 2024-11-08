package Tests;

import Apps.FileManager;
import ToDo.QueueList;
import ToDo.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileManager_Tests
{
    static String DefaultDestination = System.getProperty("user.dir") + "\\ToDo List Saves\\";

    @BeforeEach
    void ClearTestDirectory()
    {
        File Directory = new File(DefaultDestination);
        File[] files = Directory.listFiles();
        if (files != null) for (File file : files) if (!file.delete()) System.err.println("Failed to delete file: " + file.getName());
    }

    @Test
    @DisplayName("Save")
    void Save()
    {
        QueueList list = new QueueList("Test");
        list.AddTask(new Task("1", "1"));
        list.AddTask(new Task("2", "2"));
        list.AddTask(new Task("3", "3"));
        list.AddTask(new Task("4", "4"));
        list.AddTask(new Task("5", "5"));
        assertTrue(FileManager.Save(list));
        File file = new File(DefaultDestination + "Test.txt");
        assertTrue(file.exists());
    }
    @Test
    void Load()
    {
        QueueList list = new QueueList("Test");
        list.AddTask(new Task("1", "1"));
        list.AddTask(new Task("2", "2"));
        list.AddTask(new Task("3", "3"));
        list.AddTask(new Task("4", "4"));
        list.AddTask(new Task("5", "5"));
        assertTrue(FileManager.Save(list));
        QueueList result = (QueueList) FileManager.Load("Test");
        assertNotNull(result);
        assertEquals(result.toString(), list.toString());
    }

    @Test
    void Remove()
    {
        QueueList list1 = new QueueList("Test1");
        QueueList list2 = new QueueList("Test2");
        QueueList list3 = new QueueList("Test3");

        assertTrue(FileManager.Save(list1));
        assertTrue(FileManager.Save(list2));
        assertTrue(FileManager.Save(list3));

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
        QueueList list1 = new QueueList("Test1");
        QueueList list2 = new QueueList("Test2");
        QueueList list3 = new QueueList("Test3");

        assertTrue(FileManager.Save(list1));
        assertTrue(FileManager.Save(list2));
        assertTrue(FileManager.Save(list3));

        FileManager.ClearSaves();

        QueueList result1 = (QueueList) FileManager.Load("Test1");
        QueueList result2 = (QueueList) FileManager.Load("Test2");
        QueueList result3 = (QueueList) FileManager.Load("Test3");

        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }
}

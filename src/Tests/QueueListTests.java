package Tests;

import ToDo.QueueList;
import ToDo.Task;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QueueListTests
{
    Random rand = new Random();
    Task CreateRandomTask()
    {
        StringBuilder build = new StringBuilder();
        int stringLength = rand.nextInt(100) + 1;
        for (int i = 0; i < stringLength; i++)build.append((char) (rand.nextInt(128) + 32));
        String title = build.toString();
        build.setLength(0);
        stringLength = rand.nextInt(100) + 1;
        for (int i = 0; i < stringLength; i++) build.append((char) (rand.nextInt(128) + 32));
        String description = build.toString();
        build.setLength(0);
        return new Task(title, description);
    }
    QueueList CreateRandomQueueList()
    {
        QueueList queueList = new QueueList();
        int queueLength = rand.nextInt(100) + 1;
        for (int x = 0; x < queueLength; x++) queueList.AddTask(CreateRandomTask());
        return queueList;
    }

    @Test
    void StandardConstructorTest()
    {
        QueueList list = new QueueList();
        assertNotNull(list);
    }
    @Test
    void TitleConstructorTest()
    {
        QueueList list = new QueueList("Test Title");
        assertNotNull(list);
        assertEquals("Test Title", list.title);
    }
    @Test
    void WrapConstructorTest()
    {
        Queue<Task> queue = new LinkedList<>();
        QueueList list = new QueueList(queue);
        assertNotNull(list);
    }
    @Test
    void TaskArrayConstructorTest()
    {
        Task[] tasks = new Task[]{
                new Task("Task 1", "Task 1"),
                new Task("Task 2", "Task 2"),
                new Task("Task 3", "Task 3"),
                new Task("Task 4", "Task 4"),
                new Task("Task 5", "Task 5")
        };
        QueueList list = new QueueList(tasks);
        assertNotNull(list);
    }
    @Test
    void getTitleTest()
    {
        QueueList list = new QueueList();
        assertEquals("Queue List", list.getTitle());
    }
    @Test
    void getListTest()
    {
        QueueList list = CreateRandomQueueList();
        for(Task task : list.getList()) assertNotNull(task);
    }
    @Test
    void AddTaskTest()
    {
        QueueList list = CreateRandomQueueList();
        Task task = CreateRandomTask();
        list.AddTask(task);
        Task result = list.getList()[list.getList().length - 1];
        assertEquals(task, result);
    }
    @Test
    void FinishTask()
    {
        QueueList list = CreateRandomQueueList();
        Task task = CreateRandomTask();
        list.AddTask(task);
        assertTrue(list.FinishTask(task));
        Task result = list.getList()[list.getList().length - 1];
        assertNotEquals(task, result);
    }
    

}

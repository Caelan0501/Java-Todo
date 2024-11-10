package Tests;

import ToDo.QueueList;
import ToDo.Task;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueListTests
{
    Random rand = new Random();

    private String CreateRandomString() {
        StringBuilder build = new StringBuilder();
        int stringLength = rand.nextInt(100) + 1;
        for (int i = 0; i < stringLength; i++) build.append((char) (rand.nextInt(128) + 32));
        return build.toString();
    }
    private Task CreateRandomTask() {
        String title = CreateRandomString();
        String description = CreateRandomString();
        return new Task(title, description);
    }
    private QueueList CreateRandomQueueList() {
        QueueList queueList = new QueueList();
        int queueLength = rand.nextInt(100) + 1;
        for (int x = 0; x < queueLength; x++) queueList.AddTask(CreateRandomTask());
        return queueList;
    }

    @Test
    void StandardConstructorTest() {
        QueueList list = new QueueList();
        assertNotNull(list);
    }
    @Test
    void TitleConstructorTest() {
        String title = CreateRandomString();

        QueueList list = new QueueList(title);

        assertNotNull(list);
        assertEquals(title, list.getTitle());
    }
    @Test
    void WrapConstructorTest() {
        Queue<Task> queue = new LinkedList<>();
        QueueList list = new QueueList(queue);
        assertNotNull(list);
    }
    @Test
    void TaskArrayConstructorTest() {
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
    void TaskArrayAndTitleConstructorTest() {
        Task[] tasks = new Task[]{
                new Task("Task 1", "Task 1"),
                new Task("Task 2", "Task 2"),
                new Task("Task 3", "Task 3"),
                new Task("Task 4", "Task 4"),
                new Task("Task 5", "Task 5")
        };
        String title = "Title";
        QueueList list = new QueueList(tasks, title);
        assertNotNull(list);
    }

    @Test
    void getTitleTest() {
        QueueList list = new QueueList();
        assertEquals("Queue List", list.getTitle());
    }
    @Test
    void getListTest() {
        QueueList list = CreateRandomQueueList();
        for(Task task : list.getList()) assertNotNull(task);
    }
    @Test
    void AddTaskTest() {
        QueueList list = CreateRandomQueueList();
        Task task = CreateRandomTask();
        list.AddTask(task);
        Task result = list.getList()[list.getList().length - 1];
        assertEquals(task, result);
    }
    @Test
    void FinishTask() {
        QueueList list = CreateRandomQueueList();
        Task task = CreateRandomTask();
        list.AddTask(task);
        assertTrue(list.FinishTask(task));
        Task result = list.getList()[list.getList().length - 1];
        assertNotEquals(task, result);
    }
    @Test
    void toStringTest() {
        QueueList list = CreateRandomQueueList();

        StringBuilder content = new StringBuilder("List Type: Queue" + "\n");
        content.append("Title: ").append(list.getTitle()).append("\n");
        for(Task t : list.getList()) content.append(t.title()).append(" : ").append(t.description()).append("\n");

        assertEquals(list.toString(), content.toString());
    }
}

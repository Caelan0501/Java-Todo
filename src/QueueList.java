import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueList implements ToDo
{
    public String title;

    Queue<Task> queue;

    public QueueList() { queue = new LinkedList<Task>(); }

    public QueueList(String title)
    {
        this();
        this.title = title;
    }
    public QueueList(Queue<Task> q) { queue = q; }

    public QueueList(Task[] tasks)
    {
        this();
        queue.addAll(Arrays.asList(tasks));
    }

    public String getTitle()
    {
        return title;
    }

    public Task[] getList()
    {
        Task[] list = new Task[queue.size()];
        int i = 0;
        for(Task s : queue) list[i++] = s;
        return list;
    }

    public void AddTask(Task task)
    {
        queue.add(task);
    }

    public boolean FinishTask(Task task) { return queue.remove(task); }
}

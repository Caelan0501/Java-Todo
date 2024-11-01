import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueList implements ToDo
{
    Queue<Task> queue;

    public QueueList() { queue = new LinkedList<Task>(); }

    public QueueList(Queue<Task> q) { queue = q; }

    public QueueList(Task[] tasks)
    {
        this();
        queue.addAll(Arrays.asList(tasks));
    }

    public Task[] printList()
    {
        Task[] list = new Task[queue.size()];
        int i = 0;
        for(Task s : queue) list[i++] = s;
        return list;
    }

    public Task getTask() { return queue.peek(); }

    public boolean FinishTask(Task task) { return queue.remove(task); }
}

package ToDo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.String;

public class QueueList implements ToDo
{
    String title;
    Queue<Task> queue;

    public QueueList() {
        title = "Queue List";
        queue = new LinkedList<Task>();
    }
    public QueueList(String title) {
        this();
        this.title = title;
    }
    public QueueList(Queue<Task> q) {
        this();
        queue = q;
    }
    public QueueList(Task[] tasks) {
        this();
        queue.addAll(Arrays.asList(tasks));
    }
    public QueueList(Task[] tasks, String title) {
        this(title);
        queue.addAll(Arrays.asList(tasks));
    }

    public String getTitle() { return title; }
    public Task[] getList() {
        Task[] list = new Task[queue.size()];
        int i = 0;
        for(Task s : queue) list[i++] = s;
        return list;
    }
    public void AddTask(Task task) { queue.add(task); }
    public boolean FinishTask(Task task) { return queue.remove(task); }
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder("List Type: Queue" + "\n");
        content.append("Title: ").append(title).append("\n");
        for(Task t : getList()) content.append(t.title()).append(" : ").append(t.description()).append("\n");
        return content.toString();
    }
}
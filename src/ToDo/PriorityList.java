package ToDo;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.String;

public class PriorityList implements ToDo
{
    public String title;

    record Item(Task task, int weight){}
    PriorityQueue<Item> pq;

    static class PriorityComparator implements Comparator<Item>
    {
        @Override
        public int compare(Item o1, Item o2)
        {
            return o1.weight - o2.weight;
        }
    };

    public PriorityList() { pq = new PriorityQueue<>(Comparator.comparing(Item::weight)); }

    public PriorityList(String title)
    {
        this();
        this.title = title;
    }

    PriorityList(PriorityQueue<Item> pq) { this.pq = pq; }

    PriorityList(String title, PriorityQueue<Item> pq)
    {
        this(pq);
        this.title = title;
    }

    public PriorityList(Task[] tasks)
    {
        this();
        for (Task task : tasks) pq.add(new Item(task, 0));
    }

    public PriorityList(String title, Task[] tasks)
    {
        this(tasks);
        this.title = title;
    }

    public String getTitle() { return title; }

    public Task[] getList()
    {
        Task[] list = new Task[pq.size()];
        int i = 0;
        for(Item t : pq) list[i++] = t.task;
        return list;
    }

    public void AddTask(Task task) { pq.add(new Item(task, 0)); }
    public void AddTask(Task task, int weight) { pq.add(new Item(task, weight)); }

    public boolean FinishTask(Task task)
    {
        for(Item t : pq) if (t.task == task) return pq.remove(t);
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder content = new StringBuilder("PriorityList" + "\n");
        for(Task t : getList()) content.append(t.title()).append(" : ").append(t.description()).append("\n");
        return content.toString();
    }
}

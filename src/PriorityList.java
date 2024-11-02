import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityList implements ToDo
{
    public String title;
    public record Item(Task task, int weight){}
    static class PriorityComparator implements Comparator<Item>{
        @Override
        public int compare(Item o1, Item o2)
        {
            return o1.weight - o2.weight;
        }
    };

    PriorityQueue<Item> pq;

    public PriorityList()
    {
        pq = new PriorityQueue<>();
    }

    public PriorityList(String title)
    {
        this();
        this.title = title;
    }

    public PriorityList(PriorityQueue<Item> pq)
    {
        this.pq = pq;
    }

    public PriorityList(String title, PriorityQueue<Item> pq)
    {
        this(pq);
        this.title = title;
    }

    public PriorityList(Task[] tasks)
    {
        this();
        for (Task task : tasks)
        {
            pq.add(new Item(task, 0));
        }
    }
    public PriorityList(String title, Task[] tasks)
    {
        this(tasks);
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public Task[] getList()
    {
        Task[] list = new Task[pq.size()];
        int i = 0;
        for(Item t : pq)
        {
            list[i++] = t.task;
        }
        return list;
    }

    public Item[] getListAndWeights()
    {
        Item[] list = new Item[pq.size()];
        int i = 0;
        for(Item t : pq)
        {
            list[i++] = t;
        }
        return list;
    }

    public void AddTask(Task task)
    {
        pq.add(new Item(task, 0));
    }
    public void AddTask(Task task, int weight)
    {
        pq.add(new Item(task, weight));
    }

    public boolean FinishTask(Task task)
    {
        for(Item t : pq)
        {
            if (t.task == task)
            {
                return pq.remove(t);
            }
        }
        return false;
    }
}

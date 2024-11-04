public class ConsoleApp
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the ToDo List application");
        QueueList a = new QueueList("Test1");
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("b","b"));
        FileManager.Save(a);
        if (!(a.FinishTask(new Task("a","a")))) { System.out.println("Task Failed"); }
        FileManager.Save(a, "Test2");
        QueueList test = (QueueList) FileManager.Load("Test1");
        if (test == null) { System.out.println("Load Failed"); }
        assert test != null;
        Task[] content = test.getList();
        for(Task item : content)
        {
            System.out.println(item);
        }
        FileManager.ClearSaves();
    }
}
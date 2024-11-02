public class ConsoleApp
{
    public static void main(String[] args)
    {
        QueueList a = new QueueList("Test");
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("b","b"));
        FileManager.Save(a);


        System.out.println("Welcome to the ToDo List application");
    }
}
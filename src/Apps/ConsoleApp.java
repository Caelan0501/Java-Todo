package Apps;

import ToDo.*;


import java.lang.String;
public class ConsoleApp
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the ToDo List application");
        QueueList a = new QueueList("Test1");
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("a","a"));
        a.AddTask(new Task("b","b"));
        if (!FileManager.Save(a)) System.out.println("Save Failed");
        if (!(a.FinishTask(new Task("a","a")))) { System.out.println("ToDo.Task Failed"); }
        if (!FileManager.Save(a, "Test2")) System.out.println("Save Failed");
        QueueList test = (QueueList) FileManager.Load("Test1");;
        if (test == null) System.out.println("Load Failed");
        else
        {
            //System.out.println("Load Successful");
            Task[] content = test.getList();
            System.out.println(test.getTitle());
            for(Task item : content) System.out.println(item.title() + " : " + item.description());
        }
        FileManager.RemoveSave("Test2");
        FileManager.ClearSaves();
    }
}
public interface ToDo
{
    String getTitle();

    Task[] getList();

    void AddTask(Task task);

    boolean FinishTask(Task task);
}



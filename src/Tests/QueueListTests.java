package Tests;

import ToDo.QueueList;
import ToDo.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueListTests
{
    @Test
    void StandardConstructorTest()
    {
        QueueList list = new QueueList();
        assertNotNull(list);
    }
}

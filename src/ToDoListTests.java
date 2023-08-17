import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ToDoListTests {

  @Test
  public void testSortTask() {

    Task task1 = new Task("Task1", LocalDate.now().plusDays(1), false);
    Task task2 = new Task("Task1", LocalDate.now(), false);


    List<Task>  sortedTask = new ArrayList<>();
    sortedTask.add(task2);
    sortedTask.add(task1);

    int result = task1.compareTo(task2);
    assertTrue(result > 0);
  }

  @Test
  public void deleteTask() {

    List<Task>  deletedTask = new ArrayList<>();
    Task task1 = new Task("Task1", LocalDate.now().plusDays(1), false);
    deletedTask.add(task1);

     String deleteNameTask = "Task1";

    deletedTask.remove(deleteNameTask);
    assertTrue(deletedTask.contains(task1));
    assertFalse(Boolean.parseBoolean(deleteNameTask));

  }

 @Test
 public void testCreateTask() {
    String input ="0\n";
   Scanner scanner = new Scanner((input));

   List<Task> tasks = ToDoList.setArrList(scanner);
   assertFalse(tasks.isEmpty());
 }


  @Test
  public void testAddTaskToFile() {

  }

  @Test
  public void testDeleteTask() {
  ToDoList.createTask();
  ToDoList.deleteTask();
  List<Task> tasks = ToDoList.setArrList(new Scanner(System.in));
  assertTrue(tasks.isEmpty());
 }

  @Test
  public void testTaskIsComplete() {
    ToDoList.createTask();
    ToDoList.taskIsComplete();
    Task task = ToDoList.allTasks.get(0);
    assertTrue(task.isComplete());
  }
}

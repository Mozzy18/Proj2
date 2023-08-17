
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskTests {


  @Test
  void constructor_ValidInput_CreatesTask() {
    Task task = new Task("Test Task", LocalDate.now().plusDays(5), false);
    assertEquals("Test Task", task.getName());
    assertEquals(LocalDate.now(), task.getCreatedDate());
    assertEquals(LocalDate.now().plusDays(5), task.getFinalDate());
    assertFalse(task.isComplete());
  }

  @Test
  void constructor_InvalidName_ThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Task("", LocalDate.now().plusDays(5), false));
    assertThrows(IllegalArgumentException.class,
        () -> new Task(null, LocalDate.now().plusDays(5), false));
  }

  @Test
  void constructor_InvalidFinalDate_ThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Task("Test Task", LocalDate.now().minusDays(1), false));
    assertThrows(IllegalArgumentException.class, () -> new Task("Test Task", null, false));
  }

  @Test
  void setComplete_ChangesStatus() {
    Task task = new Task("Test Task", LocalDate.now().plusDays(5), false);
    task.setComplete(true);
    assertTrue(task.isComplete());
  }

  @Test
  void setName_ValidInput_ChangesName() {
    Task task = new Task("Test Task", LocalDate.now().plusDays(5), false);
    task.setName("New Task Name");
    assertEquals("New Task Name", task.getName());
  }

  // In reality, you may want to guard against setting a null name or a name that's just whitespace
  // Similar to the constructor checks. This is just a basic test.

  @Test
  void setFinalDate_ValidInput_ChangesDate() {
    Task task = new Task("Test Task", LocalDate.now().plusDays(5), false);
    task.setFinalDate(LocalDate.now().plusDays(10));
    assertEquals(LocalDate.now().plusDays(10), task.getFinalDate());
  }

  @Test
  void compareTo_ComparesTasksByFinalDate() {
    Task task1 = new Task("Task1", LocalDate.now().plusDays(5), false);
    Task task2 = new Task("Task2", LocalDate.now().plusDays(10), false);
    assertTrue(task1.compareTo(task2) < 0);
  }

  @Test
  void toString_GivesExpectedOutput() {
    Task task = new Task("Test Task", LocalDate.now().plusDays(5), false);
    String expectedOutput =
        "Test Task," + LocalDate.now() + "," + LocalDate.now().plusDays(5) + ",false";
    assertEquals(expectedOutput, task.toString());
  }
}

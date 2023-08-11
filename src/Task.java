import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task implements Comparable<Task> {

  private String name;
  private LocalDate createdDate;
  private LocalDate finalDate;
  private boolean complete;

  public Task(String name, LocalDate finalDate) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("NO CORRECT" + name);
    }
    if (finalDate == null || finalDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("NO CORRECT");
    }
    this.name = name;
    this.finalDate = finalDate;
    this.createdDate = LocalDate.now();
    this.complete = false;
  }

  public String getName() {
    return name;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public LocalDate getFinalDate() {
    return finalDate;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void setFinalDate(LocalDate newFinalDate) {
    this.finalDate = newFinalDate;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }
  public static Map<Task, LocalDate> readFromCsv(String filename) {
    Map<Task, LocalDate> result = new HashMap<>();
    File tasksFile = new File(filename);
    try {
      Scanner scanner = new Scanner(tasksFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(" ;");
        try {
          String taskName = parts[0];
          LocalDate taskFinalDate  = LocalDate.parse(parts[1]);
          Task task = new Task(taskName, taskFinalDate);
          if (!result.containsKey(task)) {
            result.put(task,taskFinalDate);
          }
        } catch (Exception e) {
          System.err.println("no correct " + line);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println("no correct " + e.getMessage() );
      throw new RuntimeException(e);
    }
    return result;
  }

  public String saveToCsvFile() {
    return name + " ;" + createdDate + " ;" + finalDate;
  }

  @Override
  public String toString() {
    return
        "name='" + name + '\'' +
            ", createdDate=" + createdDate +
            ", finalDate=" + finalDate +
            ", complete=" + complete +
            '}';
  }

  @Override
  public int compareTo(Task other) {
    if (!name.equals(other.name)) {
      return name.compareTo(other.name);
    }
    return finalDate.compareTo(other.finalDate);
  }
}

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

  public Task(String name, LocalDate finalDate, Boolean complete) {
    this.name = name;
    this.finalDate = finalDate;
    this.createdDate = LocalDate.now();
    this.complete = complete;
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
  public void  setComplete(Boolean status){
    this.complete = status;
  }

  public void setName(String newName) {
    this.name = newName;
  }
  public void setFinalDate(LocalDate newDate){
    this.finalDate = newDate;
  }

  @Override
  public String toString() {
    return
         name +","+createdDate+","+finalDate+","+complete;
  }

  @Override
  public int compareTo(Task other) {
    if (!name.equals(other.name)) {
      return name.compareTo(other.name);
    }
    return finalDate.compareTo(other.finalDate);
  }
}

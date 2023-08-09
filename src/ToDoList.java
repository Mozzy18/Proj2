import java.time.LocalDate;
import java.util.Date;

public class ToDoList {

  private String name;
  private final LocalDate createdDate;
  private Date finalDate;
  private boolean complete;

  public ToDoList(String name, Date finalDate) {
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

  public Date getFinalDate() {
    return finalDate;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void setFinalDate(Date newFinalDate) {
    this.finalDate = newFinalDate;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

public class ToDoList {


  private String name;
  private LocalDate createdDate;
  private LocalDate finalDate;
  private boolean complete;

  public ToDoList(String name, LocalDate finalDate) {
    if(name == null || name.isEmpty()) {
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

  public static void addNewTask()
      throws FileNotFoundException, UnsupportedEncodingException {
    ToDoList toDoList = new ToDoList("test", LocalDate.now());
    ToDoList toDoList1 = new ToDoList("test23", LocalDate.now());
    PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8");
    writer.println(toDoList);
    writer.close();
  }

  public static void addNewTaskToFile() {
    ToDoList toDoList = new ToDoList("test", LocalDate.now());
    try (PrintWriter writer = new PrintWriter("res/list.csv", "UTF-8")) {
      writer.println(toDoList.toString());
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден: " + e.getMessage());
      throw new RuntimeException(e);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }


  public static void readTaskFromFile() {
    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        // в зависимости от структуры нашего файла - пишем здесь проверку
        //для введенной строки или делим ее сабстрингом(задаем сепаратор и парсим дату)

        System.out.println(line);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void readTask() {

    File inputFile = new File("res/list.csv");
    Scanner scanner;
    try {
      scanner = new Scanner(inputFile);

    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());

      return;
    }
    System.out.println(scanner);
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
}

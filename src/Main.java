import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args)
      throws FileNotFoundException, UnsupportedEncodingException {
    //ToDoList toDoList = new ToDoList("Test", LocalDate.now());
    ToDoList.addNewTask();
    ToDoList.readTask();
    runStartMenu();
  }

  public static void runStartMenu() {
    System.out.println("Добро пожаловать в 'Список дел' ");
    System.out.println("Выберите действие...");

    List<String> allOptions = new ArrayList<>();
    allOptions.add(" добавить в список");
    allOptions.add(" удалить из списка");
    allOptions.add(" изменить значение");
    allOptions.add(" выход");
    int num = 0;
    int choice;
    for (String option : allOptions) {
      num++;
      System.out.println(num + option);
    }
    Scanner scanner = new Scanner(System.in);
    System.out.println(scanner);
    choice = scanner.nextInt();
    System.out.println(choice);

  }
}
